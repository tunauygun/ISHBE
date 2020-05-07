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
import javax.swing.JOptionPane;

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
        Player p;
        System.out.println("Client Handler is running");
        try {
            // Ask username
            String username = dis.readUTF();
            p = new Player(username);
            Server.players.add(p);
            while (true){
                // Ask user what he wants 
                // dos.writeUTF("Listening for messages..."); 

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
                        dos.writeUTF(Server.letter); 
                        System.out.println("Sending the letter " + Server.letter);
                        break; 

                    case "score" : 
                        dos.writeUTF(String.valueOf(p.score)); 
                        break; 
                        
                    case "submit" :
                        String userAnswers = dis.readUTF();
                        p.submittedAnswers.add(userAnswers);
                        Server.submNum +=1;
                        if(Server.submNum == Server.players.size()){
                            System.out.println("Ready to display scores");
                            ServerMain.displaySubmissions();
                        }
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
