/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.UNOCard;
import model.Player;
import model.CardColour;
import model.CardColourHelper;

/**
 *
 * @author LianL
 */
public class GameplayView {
    Scanner input = new Scanner(System.in);
    
    //PlayerView Constructor
    public GameplayView(Scanner input) 
    {
        this.input = input;
    }
    
    public UNOCard promptCardSelection(Player player) {
        UNOCard[] hand = player.getPlayerHand();

        System.out.println("\n" + player.getUsername() + ", it's your turn.");
        System.out.println("Your hand:");

        // List cards with indices
        for (int i = 0; i < hand.length; i++) {
            System.out.println(i + ": " + hand[i]);
        }
        System.out.println(hand.length + ": Draw a card");

        System.out.print("Select a card index to play or draw a card: ");
        int choice = input.nextInt();
        input.nextLine();  // consume newline

        if (choice == hand.length) {
            return null; // signifies draw card
        } else if (choice >= 0 && choice < hand.length) {
            return hand[choice];
        } else {
            System.out.println("Invalid choice.");
            return promptCardSelection(player); // re-prompt until valid
        }
    }
    
public CardColour promptColourSelection(Player player) {
    CardColour[] colors = CardColour.values();  // get all colors

    System.out.println("Please select a color:");

    // Print options for the user, excluding WILD (assumed last)
    for (int i = 0; i < colors.length - 1; i++) {
        System.out.println(i + ": " + colors[i]);
    }

    int choice = -1;
    while (choice < 0 || choice >= colors.length - 1) {  // validate only within RED, YELLOW, GREEN, BLUE
        System.out.print("Enter the number corresponding to your color choice: ");
        try {
            choice = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please enter a number.");
        }
    }

    return colors[choice];
}

}

