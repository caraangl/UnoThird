package ca.sheridancollege.project;
import java.util.ArrayList;

/**
 * @author Ryleigh Smith August 2025
 */

public class Game {

    private ArrayList<Player> players; 

    public Game() {
        players = new ArrayList();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    // Placeholder method to start the game. 
    // Only prints a list of players right now.
    public void playUno() {
        System.out.println("Game Start! "
                + "\nCurrent Players:");
        for(Player p : getPlayers()) {
            System.out.println(p.getUserName());
        }
 
    }
}
