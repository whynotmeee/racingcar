/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racingcar;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

/**
 *
 * @author Trieu Van Dung
 */
public class Car {
    
    private int a,b;
    private Image image;
    private int pos_x;
    private int pos_y;
    private double speed=0;
    private double radius=90;
    public int bound[]={35,18};
    
    private AffineTransform at = new AffineTransform();
    
    public Car(String a){
        ImageIcon ii=new ImageIcon("src/images/"+a+".png");
        image=ii.getImage();
        
    }
    
    public double getSpeed(){
        return speed;
    }
    
    public void setSpeed(double speed){
        this.speed=speed;
    }
    
    public double getRadius(){
        return radius;
    }
    
    public void setRadius(double a){
        radius=a;
    }
    
    public void setPos_x(int x){
        pos_x=x;
}
    
    public void setPos_y(int y){
        pos_y=y;
    }
    
    public int getPos_x(){
        return pos_x;
    }
    
    public int getPos_y(){
        return pos_y;
    }
    
    public Image getImage(){
        return image;
    }
    
  
    public void move(){
        double x=speed*Math.cos(Math.toRadians(radius));
        double y=speed*Math.sin(Math.toRadians(radius));
        pos_x-=x;
        pos_y-=y;
    }
    
    public void rotate(int x){
      
        
        at.rotate(x*Math.PI/10); 
        
    }
     public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        
        if (key == KeyEvent.VK_UP) {
            if(speed>=0){
                speed+=0.5;
            }else{
                speed=0;
            }
            move();
        }
        if (key == KeyEvent.VK_DOWN) {
            if(speed>0){
                speed=0;
            }else{
                speed-=0.5;
            }
            move();
        }
    }
     
     /* public Rectangle getBounds(){
     return new Rectangle(pos_x, pos_y, image.getWidth(null), image.getHeight(null));
     }*/

    
    
}
