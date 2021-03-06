/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe_server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Abanoub Kamal
 */
public class ClientHandler extends Thread{
  

    DataInputStream  inS;
    PrintStream outS;
    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();
    String clientStatus;
    
    public ClientHandler(Socket s) {
        try{
            inS = new DataInputStream(s.getInputStream());
            outS = new PrintStream(s.getOutputStream());
            clientStatus = "Online";
            clientsVector.add(this); 
            start();
        }catch(Exception ex){
             System.out.println("server.ChatHandler.<init>()");
        }
    }

    public void run(){
        try{
            /////////////////////////////////////////////////////////////////////
//            String username = inS.readLine();
//            String password = inS.readLine();
//            
//            ifUserExist(username);
            
            ////////////////////////////////////////////////////////////////////
            while(true){
                clientStatus = inS.readLine(); // for client status // give an exception error if there is no client
                outS.println("Online"); // for server status // give an exception error if there is no client
                
                if(clientStatus == null) // done at the client fallen
                {
                    System.out.println(clientStatus);
                    System.out.println("tic.tac.toe_server.ClientHandler.run() from outside exception");
                    inS.close();
                    outS.close();
                    clientsVector.removeElement(this);
                    this.currentThread().stop();
                }
                
                this.currentThread().sleep(50);
            }
        }catch(Exception ex){       // i don't know why this exception is not fire at the client is fallen
            try{
                System.out.println(clientStatus);
                System.out.println("tic.tac.toe_server.ClientHandler.run() from iside exception");
                inS.close();
                outS.close();
                clientsVector.removeElement(this);
                this.currentThread().stop();
            }catch(IOException e){}
        }
        
    }

    
    public static Vector<ClientHandler> getClientsVector()
    {
        return clientsVector;
    }
    
    /* handling the user existness */
    
    
    
    
    
                        
    
}

