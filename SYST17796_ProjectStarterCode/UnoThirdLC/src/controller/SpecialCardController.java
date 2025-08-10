/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import view.*;

/**
 *SpecialCardController controls all of the effects of wild and action cards 
 * @author Lian Asher Caraang
 */
public class SpecialCardController 
{
    //Controller Variables
    private GameController gameController;
    private DeckController deckController;
    private HandController handController;
    
    //View Variables
    private GameplayView gameplayView;
    
    //Class Variables
    private Game game;
    
    //Constructor
    public SpecialCardController(Game game,
            DeckController deckController,
            HandController handController,
            GameplayView gameplayView,
            GameController gameController) 
    {
        this.game = game;
        this.deckController = deckController;
        this.handController = handController;
        this.gameplayView = gameplayView;
        this.gameController = gameController;
    }
    
    //SpecialCardController main Run Method, checking for action or wild cards
    public void run(UNOCard card, Player currentPlayer) 
    {
        switch (card.getValue()) 
        {
            //If it is a WILD_DRAW_FOUR card, call the handleWildDrawFour method and then Skip the next Player
            case WILD_DRAW_FOUR: 
                handleWildDrawFour(currentPlayer);
                gameController.skipPlayer(); 
                break;
            //If it is a WILD card, call the handleWild method
            case WILD:
                handleWild(currentPlayer);
                gameController.advanceTurn(); //Goes to the next player's turn
                break;
            //If it is SKIP card, call the skip method
            case SKIP:
                gameplayView.showSkipPlayed(); //Display Skip Played
                gameController.setValidColour(card.getColour()); //Set's the top colour to the latest colour on the skip card
                gameController.skipPlayer(); //Skip the Player, Goes Back to Player who played the Card
                break;
            //If it is a REVERSE card, call the skip method (In 2 player mode, REVERSE esentially will act as a skip)
            case REVERSE:
                gameplayView.showReversePlayed(); //Display Reverse Played
                gameController.setValidColour(card.getColour()); ////Set's the top colour to the latest colour on the reverse card
                gameController.skipPlayer(); //Skip the Player, Goes Back to Player who played the Card
                break;
            //If it is a DRAW_TWO card, call the handleDrawTwo Method, and then Skip the Player
            case DRAW_TWO:
                handleDrawTwo(currentPlayer);
                gameController.skipPlayer();
                break;
            //If not any of the action or wild cards, then set the top colour to the latest colour on the most recent card, and then go to the next player's turn
            default:
                gameController.setValidColour(card.getColour());
                gameController.advanceTurn();
                break;
        }
    }
    
    //When a player plays a WILD_DRAW_FOUR, the next player receives 4 cards, and then gets skipped in the switch case
    private void handleWildDrawFour(Player currentPlayer) 
    {
        //Calls getsNextPlayerIndex() to get the index of the next player
        int nextIndex = gameController.getNextPlayerIndex();
        Player nextPlayer = game.getPlayers().get(nextIndex);
        
        //The next player draws 4 cards
        for (int i = 0; i < 4; i++) 
        {
            handController.addCardToHand(nextPlayer, deckController.drawCard());
        }
        
        //Display showPlayerDrewCards method from gameplayView
        gameplayView.showPlayerDrewCards(nextPlayer, 4);
        
        //After the player draws 4 cards, the handleWild method is called to choose a colour
        handleWild(currentPlayer);
    }
    
    //Method calls a method prompt from the gameplayView and then requests the user for a colour
    private void handleWild(Player currentPlayer) 
    {
        CardColour chosenColour = gameplayView.promptColourSelection(currentPlayer); //Prompting player
        gameController.setValidColour(chosenColour); //Sets the top card's colour to the chosen colour
        gameplayView.showColorChanged(currentPlayer, chosenColour); //Display the newly changed colour
    }
    
    //When a draw two card is played, the next player draws two cards 
    private void handleDrawTwo(Player currentPlayer) 
    {
        //Check for the next player
        int nextIndex = gameController.getNextPlayerIndex();
        Player nextPlayer = game.getPlayers().get(nextIndex);
        
        //The next player draws 2 cards
        for (int i = 0; i < 2; i++) 
        {
            handController.addCardToHand(nextPlayer, deckController.drawCard());
        }
        
        //Display player drawing 2 cards
        gameplayView.showPlayerDrewTwoCards(nextPlayer);
    }
}
