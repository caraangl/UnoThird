/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * A class that models each Player in the game. Players have an identifier,
 * which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Ryleigh Smith August 2025
 */
public class Player {

    private String userName; // The unique name for this player
    private Integer score; // How many cards the player has
    private String password; // The player password

    // Constructor 
    public Player() {
    }

    // Constructor that allows you to set the player name
    public Player(String userName) {
        this.userName = userName;
    }

    // Constructor that allows you to set player name and password 
    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Return the player name
    public String getUserName() {
        return userName;
    }

    // Set the player name
    public void setUserName(String userName) {
        this.userName = userName;
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

    // Method to register a player
    // Catches excpetions thrown in username validator 
    // and password validator classes
    public static Player registerPlayer() {
        Scanner input = new Scanner(System.in);
        String userName;
        String password;

        while (true) {
            System.out.println("Please enter a username: "
                    + "\nNOTE: Username cannot inlcude special characters.");
            userName = input.nextLine();

            System.out.println("Please enter a password: "
                    + "\nNOTE: Password cannot include special characters. "
                    + "\nNOTE: Password must be 6 or more characters.");
            password = input.nextLine();

            try {
                UsernameValidator.checkUserName(userName);
                PasswordValidator.checkPasword(password);
                System.out.println("Player Registered Successfully.");
                return new Player(userName, password);
            } catch (Exception e) {
                System.out.println("Player Registration Rejected."
                        + e.getMessage());
            }
        }
    }

}
