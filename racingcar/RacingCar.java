/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racingcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Trieu Van Dung
 */
public class RacingCar extends JFrame{

    private JButton host;
    private JButton play;
    private JButton exit;
    private JButton about;
    private JTextField address;
    private boolean server;
    private JLabel name;
    private JLabel addr;
    
    public RacingCar(){
        setSize(400, 550);
        getContentPane().setBackground(Color.YELLOW);
        name=new JLabel("RACING CAR");
        name.setBounds(100, 20, 200, 30);
        name.setPreferredSize(new Dimension(200, 200));
        name.setForeground(Color.red);
        name.setFont(new Font(name.getFont().getName(), name.getFont().getStyle(), 24));
        addr=new JLabel("Address");
        addr.setBounds(100, 70, 100, 30);
        address=new JTextField();
        address.setBounds(100, 100, 150, 20);
        host=new JButton("Host");
        host.setBounds(130, 150, 100, 30);
        play=new JButton("Play");
        play.setBounds(130, 200, 100, 30);
        about=new JButton("About");
        about.setBounds(130, 250, 100, 30);
        exit=new JButton("Exit");
        exit.setBounds(130, 300, 100, 30);
        add(addr);
        add(name);
        add(play);
        add(about);
        add(exit);
        add(address);
        add(host);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        listener();
    }
    
    public void listener(){
        host.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String a=address.getText().toString();
                server=true;
                if(a.equals(""))
                    a="localhost";
                new Startgame(a,server);
            }
        });
        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String a=address.getText().toString();
                server=false;
                if(a.equals(""))
                    a="localhost";
                new Startgame(a,server);
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "author: Trieu Van Dung ");
            }
        });
        
    }
    
    public static void main(String[] args) {
       new RacingCar();
    }
    
}
