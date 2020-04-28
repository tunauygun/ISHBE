/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ishbe.server;

import java.util.ArrayList;

/**
 *
 * @author uygun
 */
public class Player {
    int score = 0;
    String name;
    ArrayList<String> submittedAnswers = new ArrayList<>();
    
    public Player(String n){
        this.name = n;
    }
    
    
    
}
