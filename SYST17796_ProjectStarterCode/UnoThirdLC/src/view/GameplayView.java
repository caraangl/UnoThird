/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.UNOCard;
import model.Player;

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
}
