// Java implementation of  Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 

package ishbe.server;

import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
        
// Server class 
public class Server {
    
    public static String letter;
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static int submNum;
    public static int readyToStart;
    public static ArrayList<String> letterList = new ArrayList<>(Arrays.asList("A","B","C","Ç","D","E","F","G","H","I","İ","J","K","L","M","N","O","Ö","P","R","S","Ş","T","U","Ü","V","Y","Z"));

    
    public static void pickALetter(){
        Random rand = new Random();
        int n = rand.nextInt(letterList.size());        
        String newLetter = letterList.get(n);
        letterList.remove(n);
        letter = newLetter;
        System.out.println(newLetter);
        ServerMain.letterLabel.setText(newLetter);
        System.out.println("The new letter is: " + newLetter);
    }
    
    public static void main(String args[]) throws IOException {
        new ServerMain().setVisible(true);

        
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        pickALetter();
        // server is listening on port 5056 
        ServerSocket ss = new ServerSocket(5056);
        
        // running infinite loop for getting 
        // client request 
        while (true) { 
            Socket s = null; 
              
            try { 
                // socket object to receive incoming client requests 
                s = ss.accept(); 
                  
                System.out.println("A new client is connected : " + s); 
                  
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                  
                System.out.println("Assigning new thread for this client"); 
  
                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos); 
  
                // Invoking the start() method 
                t.start(); 
                  
            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
        } 
    } 
}