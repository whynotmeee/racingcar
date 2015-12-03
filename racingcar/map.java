/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racingcar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Trieu Van Dung
 */
public class map extends JPanel implements ActionListener{
    
    private int x,y,z;
    private Image map;
    private Car car1;
    private Car car2;
    private network network;
    private Timer timer;
    private final int DELAY = 100;
    
    
    public map(String a,boolean b){
        
        network=new network(a,b);
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        ImageIcon ii=new ImageIcon("src/images/map.png");
        map=ii.getImage();
        car1=new Car("car1");
        car2=new Car("car2");
        setposition();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void  setposition(){
        car1.setPos_x(73);
        car1.setPos_y(376);
        car2.setPos_x(24);
        car2.setPos_y(379);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    
    private void doDrawing(Graphics g) {  
        
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform origXform = g2d.getTransform();
            g2d.drawImage(map, 0,0, this);
            
             if(network.getServer()){
               //g2d.drawImage(car2.getImage(),car2.getPos_x(), car2.getPos_y(), this);
               int x=car1.getImage().getWidth(this);
               int y=car1.getImage().getHeight(this);
               rotate(g,car1);
               g2d.drawImage(car1.getImage(), car1.getPos_x(), car1.getPos_y(), this);
            
               g2d.setTransform(origXform);
               rotate(g,car2);
            g2d.drawImage(car2.getImage(),car2.getPos_x(), car2.getPos_y(), this);
                g2d.dispose();
            }else{
               
               int x=car2.getImage().getWidth(this);
               int y=car2.getImage().getHeight(this);
               rotate(g,car2);
               g2d.drawImage(car2.getImage(), car2.getPos_x(), car2.getPos_y(), this);
            
               g2d.setTransform(origXform);
               rotate(g,car1);
               g2d.drawImage(car1.getImage(), car1.getPos_x(), car1.getPos_y(), this);
               g2d.dispose();
            }
    }
    
    public void rotate(Graphics g,Car car){
        Graphics2D g2d = (Graphics2D) g;
        int x=car.getImage().getWidth(this);
         int y=car.getImage().getHeight(this);
           AffineTransform origXform = g2d.getTransform();
          AffineTransform newXform = (AffineTransform)(origXform.clone());
          newXform.rotate(Math.toRadians(car.getRadius()),car.getPos_x()+x/2, car.getPos_y()+y/2);
          g2d.setTransform(newXform);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        connect();
        if(checkcollision()==0){
            if(network.getServer()){
            car1.move();
           }else{
            car2.move();
        }
        }
        check(car1,car2);
        
        repaint();
    }
    
    public void check(Car car1,Car car2){
        Rectangle r = new Rectangle(car1.getPos_x(),car1.getPos_y(),car1.getImage().getWidth(this)/2,car1.getImage().getHeight(this)/2);
        Rectangle p = new Rectangle(car2.getPos_x(),car2.getPos_y(),car2.getImage().getWidth(this)/2,car2.getImage().getHeight(this)/2);
        if(r.intersects(p)){
            if(car1.getSpeed()>car2.getSpeed()){
                double x=car1.getSpeed();
                double y=car2.getSpeed();
                car1.setSpeed(car1.getSpeed()+y/2);
                car2.setSpeed(car2.getSpeed()-x/2);
            }else{
                double x=car1.getSpeed();
                double y=car2.getSpeed();
                car1.setSpeed(car1.getSpeed()-y/2);
                car2.setSpeed(car2.getSpeed()+x/2);
            }
        }
        
    }
    
    public int checkcollision(){
        try {
            BufferedImage img = ImageIO.read(new File("src/images/map.png"));
            if(network.getServer()){
                if(img.getRGB(car1.getPos_x()-(int)(car1.bound[0]*Math.cos(car1.getRadius())/2), car1.getPos_y()-(int)(car1.bound[1]*Math.cos(car1.getRadius())/2))!=-13553872){
                    setspeed(car1);
                    return 1;
                }
            }else{
                if(img.getRGB(car2.getPos_x(), car2.getPos_y())!=-13553872){
                    setspeed(car2);
                    return 1;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void setspeed(Car car){
        car.setSpeed(0);
      
    }
    
    public void connect(){
        if(network.getServer()){
            if(network.connect){
                network.senddata(car1.getPos_x(), car1.getPos_y(), car1.getRadius());
                network.readdata();
                car2.setPos_x(network.getX());
                car2.setPos_y(network.getY());
                car2.setRadius(network.getZ());
            }
                
        
        }else{
            if(network.connect){
                network.senddata(car2.getPos_x(), car2.getPos_y(), car2.getRadius());
                network.readdata();
                car1.setPos_x(network.getX());
                car1.setPos_y(network.getY());
                car1.setRadius(network.getZ());
            }
        }
    }
    
    public class TAdapter extends KeyAdapter{
        
        @Override
        public void keyReleased(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            if(network.getServer()){
                if (key == KeyEvent.VK_LEFT) {
                 car1.setRadius(car1.getRadius()-11.25);
                  }
                if (key == KeyEvent.VK_RIGHT) {
                     car1.setRadius(car1.getRadius()+11.25);
                   }
        
                   car1.keyPressed(e);
            }else{
                if (key == KeyEvent.VK_LEFT) {
                 car2.setRadius(car2.getRadius()-11.25);
              }
               if (key == KeyEvent.VK_RIGHT) {
                 car2.setRadius(car2.getRadius()+11.25);
           }
        
              car2.keyPressed(e);
            }

        }
        
    }
}
