/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Ryleigh Smith August 2025
 */

public class Game {

    private ArrayList<Player> players; // Players of the game

    public Game() {
        players = new ArrayList();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    public void playUno() {
        System.out.println("Game Start! "
                + "\nCurrent Players:");
        for(Player p : getPlayers()) {
            System.out.println(p.getUserName());
        }
 
    }
}
