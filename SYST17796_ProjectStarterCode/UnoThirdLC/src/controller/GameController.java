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
    
    ////////////////////////
    private final UNODeck deckOfCards;
    private final DeckController deckController;
    private HandController handController;
    
    private StockPile stockPile;
    //GameController Class Constructor
    public GameController() 
    {
        //Scanner Input Variable
        Scanner input = new Scanner(System.in);
        
        //playerView View Variable
        playerView = new PlayerView(input);
        
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
        while (true) {
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


}

