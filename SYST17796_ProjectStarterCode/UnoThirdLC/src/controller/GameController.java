package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Imports
import java.util.Scanner;
import model.PasswordValidator;
import model.Player;
import model.UsernameValidator;
import view.PlayerView;
import model.Game;
import model.UNOCard;
import model.UNODeck;
import model.StockPile;
import model.CardValueHelper;
import view.GameplayView;
import model.CardColourHelper;
import model.CardValueHelper;


/**
 * GameController controls the main core Gameplay of UNO (Checking Hands, Verifying Cards)
 * @author Lian Asher Caraang August 2025
 */
public class GameController 
{
    //Controller Variables
    private PlayerController playerController;
    
    //Class Variables
    private Game game = new Game();
    
    //View Variables
    private PlayerView playerView;
    private GameplayView gameplayView;
    
    ////////////////////////
    private final UNODeck deckOfCards;
    private final DeckController deckController;
    private HandController handController;
     private Player currentPlayer;
    
    private StockPile stockPile;
    private int currentPlayerIndex = 0;
    
    //GameController Class Constructor
    public GameController() 
    {
        //Scanner Input Variable
        Scanner input = new Scanner(System.in);
        
        //playerView View Variable
        playerView = new PlayerView(input);
        gameplayView = new GameplayView(input);
        
        //playerController Controller Variable
        playerController = new PlayerController(playerView);
        
        ///////////////////////
        deckOfCards = new UNODeck();
        deckController = new DeckController(deckOfCards);
        handController = new HandController (deckController, playerView);
        
        stockPile = new StockPile();
        
        
    }
        
    //startGame - Based on number players, Register the Players using playerController
    public void startGame(int numPlayers) 
    {
        //Call the playerController's registerPlayer() method and loop through the number of players, to iterate over
        for (int i = 0; i < numPlayers; i++) 
        {
            Player player = playerController.registerPlayer();
            game.getPlayers().add(player); //Adds players to the ArrayList in the Game Class
        }
        
        //Calls playerView's showRegisteredPlayers method to display the players
        playerView.showRegisteredPlayers(game); 
        
        /////////////////////
        generateDeck();
        dealInitialHands();
        startStockPile();
        gameplay();
    }
    
    ////////////////////
    public void generateDeck() 
    {
        System.out.println("Shuffling Deck...");
        deckController.reset();    // Build the deck
        deckController.shuffle();  // Shuffle the deck
          // for testing purposes to make sure deck was working
         for (UNOCard card : deckOfCards.getCards()) 
        {
            System.out.println(card);
        }
    }
    
        // Method to generate initial hand 
    public void dealInitialHands() 
    {
        for (Player player : game.getPlayers())
        {
            handController.generatePlayerHand(player);
        }
    } 
    
    public void startStockPile() 
    {
        while (true) 
        {
        UNOCard card = deckController.drawCard();

        if (!CardValueHelper.isAllowedAsStartingCard(card.getValue())) {
            System.out.println("Returnign Card: " + card);
            deckController.returnCardToDeck(card);
            
            deckController.shuffle();
            continue;
        }

        stockPile.addCard(card);
        System.out.println("Starting card on stock pile: " + card);
        break;
        }
        
        //UNOCard currentCard = stockPile.getTopCard();
        //System.out.println("Current card in play: " + currentCard);
    }
    
    public void gameplay()
    {
        // Get current player (implement your logic to get this)
        while (true) {
            
        currentPlayer =  game.getPlayers().get(currentPlayerIndex);
        UNOCard topCard = stockPile.getTopCard();
        
        System.out.println("Top card on the pile: " + topCard);
        System.out.println("It's " + currentPlayer.getUsername() + "'s turn.");
        UNOCard selectedCard = gameplayView.promptCardSelection(currentPlayer);

        if (selectedCard == null) {
            System.out.println(currentPlayer.getUsername() + " chose to draw a card.");
            // Implement draw card logic here
             // Draw one card from the deck
            UNOCard drawnCard = deckController.drawCard();
            System.out.println(currentPlayer.getUsername() + " drew " + drawnCard);
            // Add the drawn card to the player's hand
            handController.addCardToHand(currentPlayer, drawnCard);
            
            playerView.showPlayerhand(currentPlayer);
            
            advanceTurn();
        } else if (isValidPlay(selectedCard, topCard)) {
            System.out.println(currentPlayer.getUsername() + " played: " + selectedCard);
            // Implement playing the card: update stock pile, remove from hand, etc.
            stockPile.addCard(selectedCard);
            handController.removeCardFromHand(currentPlayer, selectedCard);
            playerView.showPlayerhand(currentPlayer);
            advanceTurn();
        } 
        else 
        {
            System.out.println("Top card on the pile: " + topCard);
            System.out.println("Invalid card selection! Please select a card matching the color or value of the top card, or a Wild card.");
        }
        }
    }
    
    private Player getCurrentPlayer() 
    {
        // implement your current player retrieval logic
        return game.getPlayers().get(0); // example: always first player for now
    }
     
    private boolean isValidPlay(UNOCard playedCard, UNOCard topCard) 
    {
        if (CardColourHelper.isWild(playedCard.getColour())) {
            return true;
        }

        if (CardColourHelper.isMatchingColour(playedCard.getColour(), topCard.getColour())) {
            return true;
        }

        if (CardValueHelper.isMatchingValue(playedCard.getValue(), topCard.getValue())) {
            return true;
        }

        return false;
    }
    private void advanceTurn() 
    {
        currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
    }
}



