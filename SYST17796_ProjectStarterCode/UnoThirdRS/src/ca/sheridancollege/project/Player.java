package ca.sheridancollege.project;
import java.util.Scanner;

/**
 * @author Ryleigh Smith August 2025
 */
public class Player {

    private String userName;
    private Integer score;
    private String password;

    public Player() {
    }

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to register a player.
    // Catches excpetions thrown in username & password validator classes.
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
