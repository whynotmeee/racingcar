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

/**
 *
 * @author Trieu Van Dung
 */
public class network {
    
    private int x,y;
    private double z;  
    private int a,b;
    private double c;
    private boolean server=false;
    private DataInputStream in;
    private DataOutputStream out;
    private Car car;
    private Socket socket;
    boolean connect;
    private String address;
    
    
    
    public network(String a,boolean server){
        setAddress(a);
        setServer(server);
        new Thread(new Runnable() {

            @Override
            public void run() {
                networks();
            }
        }).start();
    }
    
    public void setAddress(String address){
        this.address=address;
    }
    
    public void setServer(boolean server){
        this.server=server;
    }
    
    public void networks(){
    
                if(server){
                    try {
                        ServerSocket serversocket=new ServerSocket(1111);
                        socket=serversocket.accept();
                        connect=true;
                        System.out.println("connect");
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Startgame.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }else{
                    try {
                        socket=new Socket(address, 1111);
                        connect=true;
                           System.out.println("connect");
                    } catch (IOException ex) {
                        Logger.getLogger(Startgame.class.getName()).log(Level.SEVERE, null, ex);
                    }
    
}
                
}

    public boolean getServer(){
        return server;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public double getZ(){
        return z;
    }
    
    public synchronized void senddata(int a,int b,double c){
        try {
            in=new DataInputStream(socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());
            out.writeInt(a);
            out.writeInt(b);
            out.writeDouble(c);
        } catch (IOException ex) {
            Logger.getLogger(network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    public synchronized void readdata(){
        try {
            in=new DataInputStream(socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());
            x = in.readInt();
            y = in.readInt();
            z = in.readDouble();
        } catch (IOException ex) {
            Logger.getLogger(network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
