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
    public GameplayView(Scanner input) {
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

    public void showCardPlayed(Player currentPlayer, UNOCard selectedCard) {
        System.out.println(currentPlayer.getUsername() + " played: " + selectedCard);
    }

    public void showTopCard(UNOCard topCard) {
        System.out.println("\nTop card on the pile: " + topCard);
    }

    public void showPlayerTurn(Player currentPlayer) {
        System.out.println("It's " + currentPlayer.getUsername() + "'s turn.");
    }

    public void showShufflingDeck() {
        System.out.println("Shuffling Deck...");
    }

    public void showPlayerDrewCard(Player currentPlayer, UNOCard drawnCard) {
        System.out.println(currentPlayer.getUsername() + " chose to draw a card and drew " + drawnCard + ".");
    }

    public void showPlayerScore(Player currentPlayer, int cardsLeft) {
        System.out.println("\n" + currentPlayer.getUsername() + "'s score (cards left): " + cardsLeft);

        if (cardsLeft == 0) {
            System.out.println(currentPlayer.getUsername() + " has won the game! Congratulations!");
        }
    }

    public void showInvalidSelection(UNOCard topCard, CardColour validColour) {
        showTopCard(topCard);
        System.out.println("Current valid color: " + validColour);
        System.out.println("Invalid card selection! Please select a card matching the color or value of the top card, or a Wild card.");
    }

    // In GameplayView.java
    public void showSkipPlayed() {
        System.out.println("Skip played! Player skipped!");
    }

    // In GameplayView.java
    public void showReversePlayed() {
        System.out.println("Reverse played! (Skipping next player in 2-player mode)");
    }

    public void showPlayerDrewCards(Player player, int numberOfCards) {
        System.out.println(player.getUsername() + " drew " + numberOfCards + " cards!");
    }

    public void showColorChanged(Player player, CardColour chosenColour) {
        System.out.println(player.getUsername() + " changed color to " + chosenColour);
    }

    public void showPlayerDrewTwoCards(Player player) {
        System.out.println(player.getUsername() + " drew 2 cards!");
    }

}
