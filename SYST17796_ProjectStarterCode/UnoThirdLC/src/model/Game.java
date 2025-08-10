package model;

import java.util.ArrayList;

/**
 * @author Ryleigh Smith August 2025 Some comments added by Lian Asher Caraang
 * || //Game Class houses the players
 */
public class Game {

    //players ArrayList Variable
    private ArrayList<Player> players;

    //Game Constructor
    public Game() {
        players = new ArrayList();
    }

    //getPlayers - Get the Players that are stored in the array
    public ArrayList<Player> getPlayers() {
        return players;
    }

    //getPlayersString - Display the Players as a String
    public String getPlayersString() {
        String result = "";

        for (Player p : players) {
            result += p.getUsername() + "\n";
        }

        return result;
    }

    //setPlayers - Sets the current arraylist with another arraylist of players
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
