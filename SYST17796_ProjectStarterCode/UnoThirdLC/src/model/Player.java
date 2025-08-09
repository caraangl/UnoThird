package model;
import java.util.Scanner;

/**
 * @author Ryleigh Smith August 2025
 */
public class Player {

    private String username;
    private Integer score;
    private String password;
    private UNOCard[] playerHand;

    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setPlayerHand(UNOCard[] playerHand) {
        this.playerHand = playerHand;
    }
    
    public UNOCard[] getPlayerHand()
    {
        return playerHand;
    }

}