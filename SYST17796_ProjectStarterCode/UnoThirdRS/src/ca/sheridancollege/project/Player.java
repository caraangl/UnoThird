/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Ryleigh Smith August 2025
 */
public abstract class Player {

    private String name; // The unique name for this player
    private Integer score; // How many cards the player has
    private String password;
    
    // Constructor 
    public Player() {
    }

    // Constructor that allows you to set the player name
    public Player(String name) {
        this.name = name;
    }

    // Return the player name
    public String getName() {
        return name;
    }

    // Set the player name
    public void setName(String name) {
        this.name = name;
    }
    
    // Return the player score
    public int getScore() {
        return score;
    }
    
    // Set the player score
    public void setScore(int score) {
        this.score = score;
    }
    
    // Get the player password
    public String getPassword() {
        return password;
    }
    
    // Set the player password
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
     * with logic to play your game.
     */
    public abstract void play();

}
