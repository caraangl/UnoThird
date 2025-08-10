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
 * GameplayView displays all gameplay messages as well as sends the user inputs for 
 * Gameplay Mechanics 
 *
 * @author Lian Asher Caraang August 2025
 */
public class GameplayView 
{
    //Input Variable
    Scanner input = new Scanner(System.in);

    //PlayerView Constructor
    public GameplayView(Scanner input) 
    {
        this.input = input;
    }
    
    //Displays a prompt for the user to input which card they want to play
    public UNOCard promptCardSelection(Player player) 
    {
        //Hand Variable - Calling the player's getPlayerHand method
        UNOCard[] hand = player.getPlayerHand();
        
        System.out.println("\n" + player.getUsername() + ", it's your turn.");
        System.out.println("Your hand:");

        //List the Player's Hand of Cards
        for (int i = 0; i < hand.length; i++) 
        {
            System.out.println(i + ": " + hand[i]);
        }
        
        //Display the choice to draw a card
        System.out.println(hand.length + ": Draw a card");
        
        //Asking for User Input
        System.out.print("Select a card index to play or draw a card: ");
        int choice = input.nextInt();
        input.nextLine();  
        
        //Return the User's Choice to the Controller
        if (choice == hand.length) 
        {
            //Signifying the action of drawing a card
            return null; 
        } 
        else if (choice >= 0 && choice < hand.length) 
        {
            return hand[choice];
        } 
        else 
        {
            //If the user inputs a number that doesn't match the hand 
            System.out.println("Invalid choice.");
            //Rerun the Method Until the Choice is Accepted
            return promptCardSelection(player); 
        }
    }
    
    //Asks the User to select a colour if they play a wild card
    public CardColour promptColourSelection(Player player) 
    {
        //Retrieves all of the available colour values
        CardColour[] colours = CardColour.values();  
        
        //Prompt
        System.out.println("Please select a colour:");

        //Display options for the player
        for (int i = 0; i < colours.length - 1; i++) {
            System.out.println(i + ": " + colours[i]);
        }
        
        int choice = -1;
        
        //Validating to check if the index inputted is valid, must be RED, YELLOW, GREEN, BLUE
        while (choice < 0 || choice >= colours.length - 1) 
        {  
            System.out.print("Enter the number corresponding to your color choice: ");
            
            try 
            {
                choice = Integer.parseInt(input.nextLine().trim());
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input, please enter a number.");
            }
        }

        return colours[choice];
    }
    
    //Displaying the card played 
    public void showCardPlayed(Player currentPlayer, UNOCard selectedCard) 
    {
        System.out.println(currentPlayer.getUsername() + " played: " + selectedCard);
    }
    
    //Displaying the top card in the deck, the most recently played card
    public void showTopCard(UNOCard topCard) 
    {
        System.out.println("\nTop card on the pile: " + topCard);
    }
    
    //Displaying which player's turn it is
    public void showPlayerTurn(Player currentPlayer) 
    {
        System.out.println("It's " + currentPlayer.getUsername() + "'s turn.");
    }
    
    //Displaying shuffling the deck to the players
    public void showShufflingDeck() {
        System.out.println("Shuffling Deck...");
    }
    
    //Displaying if the player drew a card and what card it is 
    public void showPlayerDrewCard(Player currentPlayer, UNOCard drawnCard) 
    {
        System.out.println(currentPlayer.getUsername() + " chose to draw a card and drew " + drawnCard + ".");
    }
    
    //Displaying current player's score based on the cards left
    public void showPlayerScore(Player currentPlayer, int cardsLeft) 
    {
        System.out.println("\n" + currentPlayer.getUsername() + "'s score (cards left): " + cardsLeft);
        
        //If the score is 0 then display a winner
        if (cardsLeft == 0) 
        {
            System.out.println(currentPlayer.getUsername() + " has won the game! Congratulations!");
        }
    }
    
    //Displaying invalid selection, display the most recent card and colour
    public void showInvalidSelection(UNOCard topCard, CardColour validColour) {
        showTopCard(topCard);
        System.out.println("Current valid color: " + validColour);
        System.out.println("Invalid card selection! Please select a card matching the color or value of the top card, or a Wild card.");
    }
    
    //Displaying if the skip card is used
    public void showSkipPlayed() 
    {
        System.out.println("Skip played! Player skipped!");
    }
    
    //Displaying if the reverse card is used
    public void showReversePlayed() 
    {
        System.out.println("Reverse played! (Skipping next player in 2-player mode)");
    }
    
    //Displaying if the player was forced to draw cards
    public void showPlayerDrewCards(Player player, int numberOfCards) 
    {
        System.out.println(player.getUsername() + " drew " + numberOfCards + " cards!");
    }
    
    //Displaying the new colour after a wild card was played
    public void showColorChanged(Player player, CardColour chosenColour) 
    {
        System.out.println(player.getUsername() + " changed color to " + chosenColour);
    }
    
    //Displaying player drew 2 cards
    public void showPlayerDrewTwoCards(Player player) {
        System.out.println(player.getUsername() + " drew 2 cards!");
    }   
    
    //Displaying invalid choice if the user inputs an invalid value
    public void showInvalidChoice() 
    {
        System.out.println("Invalid Choice");
    }
}
