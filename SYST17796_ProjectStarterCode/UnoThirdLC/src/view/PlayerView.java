/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

//Imports
import java.util.Scanner;
import model.Player;
import model.Game;
import model.UNOCard;

/**
 * PlayerView displays all output's and messages as well as sends the user
 * inputs for Player Registration
 *
 * @author Lian Asher Caraang & Ryleigh Smith August 2025
 */
public class PlayerView 
{
    //Input Variable
    Scanner input = new Scanner(System.in);

    //PlayerView Constructor
    public PlayerView(Scanner input) 
    {
        this.input = input;
    }

    //getUsername Method - Prompts the user for a username and then sends the input
    public String getUsername() 
    {
        System.out.println("\nPlease enter a username: "
                + "\nNOTE: Username cannot include special characters.");

        return input.nextLine().trim(); //Trims the input to make sure that there are no spaces
    }

    //getPassword Method - Prompts the user for a password and then sends the input
    public String getPassword() 
    {
        System.out.println("\nPlease enter a password: "
                + "\nNOTE: Password cannot include special characters."
                + "\nNOTE: Password must be 6 or more characters.");
        return input.nextLine().trim(); //Trims the input to make sure that there are no spaces
    }

    //showRegistrationSuccess Method - Displays successful registration message
    public void showRegistrationSuccess() 
    {
        System.out.println("\nPlayer Registered Successfully.");
    }

    //showRegistrationFailure Method - Displays failed registration message
    public void showRegistrationFailure(String message) 
    {
        System.out.println("\nPlayer Registration Rejected." + message);
    }

    //showRegisteredPlayers Method - Displays all of the currently registered players
    public void showRegisteredPlayers(Game game) 
    {
        System.out.println("\nCurrent Players:");
        System.out.println(game.getPlayersString());
    }
    
    //showPlayerHand Method - Displays the current player's hand
    public void showPlayerhand(Player player) 
    {
        System.out.println("\n" + player.getUsername() + "'s hand:");
        for (UNOCard card : player.getPlayerHand()) 
        {
            System.out.println(card); // relies on UNOCard.toString()
        }
    }
}
