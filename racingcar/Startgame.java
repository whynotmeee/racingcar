/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racingcar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Trieu Van Dung
 */
public class Startgame extends JFrame{
    
    private Double x,y,z;
    private String address;
    private boolean server;
    private DataInputStream in;
    private DataOutputStream out;
    private Car car1;
    private Car car2;
    private boolean keypressed;
    
    public Startgame(String a,boolean b){
        setSize(1000,800);
        add(new map(a,b));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        
    }
    
    
    
    /* public static void main(String[] args){
    new Startgame();
    }*/
}
