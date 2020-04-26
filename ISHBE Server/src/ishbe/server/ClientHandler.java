/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ishbe.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author uygun
 */

public class ClientHandler extends Thread {
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
    } 
  
    @Override
    public void run(){ 
        String received; 
        String toreturn;
        Player p;
        
        try {
            // Ask username
            dos.writeUTF("Username?");
            String username = dis.readUTF();
            p = new Player(username);
            Server.players.add(p);

            while (true){
                // Ask user what he wants 
                dos.writeUTF("What do you want?[Date | Time]..\n"+ 
                            "Type Exit to terminate connection."); 

                // receive the answer from client 
                received = dis.readUTF(); 

                if(received.equals("Exit")) {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                }


                switch (received) { 
                    case "letter" : 
                        toreturn = "test"; 
                        dos.writeUTF(toreturn); 
                        break; 

                    case "score" : 
                        toreturn = "test"; 
                        dos.writeUTF(toreturn); 
                        break; 

                    default:  
                        break; 
                } 

            }
        }catch(IOException e){ 
            e.printStackTrace(); 
        }
          
        try { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    }             
} 
