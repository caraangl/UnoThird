package model;

import java.util.Scanner;

/**
 * @author Ryleigh Smith August 2025, Some Comments added by Lian Asher Caraang - Player Class that has all of the attributes needed
 */
public class Player 
{
    //Variables
    private String username;
    private Integer score;
    private String password;
    private UNOCard[] playerHand;
    
    //Constructor
    public Player() 
    {
    }
    
    //Overloaded Constructor
    public Player(String username, String password) 
    {
        this.username = username;
        this.password = password;
    }
    
    //Returns player's username
    public String getUsername() 
    {
        return username;
    }
    
    //Returns player's score
    public int getScore() 
    {
        return score;
    }
    
    //Returns player's password
    public String getPassword() 
    {
        return password;
    }
    
    //Sets the player's username
    public void setUsername(String username) 
    {
        this.username = username;
    }
    
    //Sets the player's score
    public void setScore(int score) 
    {
        this.score = score;
    }
    
    //Sets the player's password
    public void setPassword(String password) 
    {
        this.password = password;
    }
    
    //Sets the player's Hand
    public void setPlayerHand(UNOCard[] playerHand) 
    {
        this.playerHand = playerHand;
    }
    
    //Gets the player's current Hand
    public UNOCard[] getPlayerHand() {
        return playerHand;
    }
}
