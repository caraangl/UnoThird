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
import model.CardColour;
import model.CardValue;
import model.UserInput;

/**
 * GameController controls the main core Gameplay of UNO (Checking Hands, deck generation and player turns
 * Verifying Cards)
 *
 * @author Lian Asher Caraang August 2025
 */
public class GameController 
{

    //Controller Variables
    private PlayerController playerController;
    private DeckController deckController;
    private HandController handController;
    private SpecialCardController specialCardController;
    
    //Class Variables
    private Game game = new Game();
    private final UNODeck deckOfCards;
    private StockPile stockPile;
    private Scanner input;
    private CardColour validColour;
    private Player currentPlayer;
    private int currentPlayerIndex = 0;

    //View Variables
    private PlayerView playerView;
    private GameplayView gameplayView;
    
    //GameController Class Constructor
    public GameController() 
    {
        //Scanner Input Variable
        input = new Scanner(System.in);

        //playerView View Variable
        playerView = new PlayerView(input);
        gameplayView = new GameplayView(input);

        //playerController Controller Variable
        playerController = new PlayerController(playerView);

        //deckController, handController and deckOfCards Initialization 
        deckOfCards = new UNODeck();
        deckController = new DeckController(deckOfCards);
        handController = new HandController(deckController, playerView);
        
        //stockPile Variable
        stockPile = new StockPile();
        
        //specialCardController
        specialCardController = new SpecialCardController(game, deckController, handController, gameplayView, this); //This refers to this gameController
    }

    //startGame - Based on number players, Register the Players using playerController
    public void startGame(int numPlayers) {
        //Call the playerController's registerPlayer() method and loop through the number of players, to iterate over
        for (int i = 0; i < numPlayers; i++) {
            Player player = playerController.registerPlayer();
            game.getPlayers().add(player); //Adds players to the ArrayList in the Game Class
        }

        //Calls playerView's showRegisteredPlayers method to display the players
        playerView.showRegisteredPlayers(game);

        //After Player Registration, the following methods are called
        generateDeck(); //Generates the UNO Deck of 108 Cards
        dealInitialHands(); //Generates the initial hands for the players
        startStockPile(); //Takes the top card of the deck and then starts the stock pile
        gameplay(); //Players can now play the game, this method is used for card checking and actions
    }

    //Generate Deck of UNO Cards
    public void generateDeck() 
    {
        gameplayView.showShufflingDeck(); //Display shuffling deck
        deckController.reset(); //Resetting, creating a new deck
        deckController.shuffle(); //Shuffle and randomize the cards in the deck
        //Debug to Display Deck
        /*for (UNOCard card : deckOfCards.getCards()) {
            System.out.println(card);
        } */
    }

    //Depending on the number of players, call the generatePlayerHand to create the player hands
    public void dealInitialHands() 
    {
        for (Player player : game.getPlayers()) 
        {
            handController.generatePlayerHand(player);
        }
    }

    //Draws one card from the deck and then checks to see if it is allowed as a starting card
    public void startStockPile() 
    {
        while (true) 
        {
            UNOCard card = deckController.drawCard();
            
            //If card is not allowed to be a starting card then return the card to the deck and then reshuffle the deck
            if (!CardValueHelper.isAllowedAsStartingCard(card.getValue())) 
            {
                //System.out.println("Returning Card: " + card); //Displays the Card Returning to the Deck
                deckController.returnCardToDeck(card);

                deckController.shuffle();
                continue;
            }
            
            //If card is allowed, then add the card to the stock pile and get the colour of the card
            stockPile.addCard(card);
            validColour = card.getColour();
            //System.out.println("Starting card on stock pile: " + card); //Displays card added to the stock pile
            break;
        }
        //UNOCard currentCard = stockPile.getTopCard();
        //System.out.println("Current card in play: " + currentCard);
    }

    //Main Gameplay to Check Current Players, Current Cards and Checking Card Colours and Action
    public void gameplay() 
    {
        while (true) 
        {
            //Gets the current player
            currentPlayer = game.getPlayers().get(currentPlayerIndex);
            //Gets the current top card of the stock pile
            UNOCard topCard = stockPile.getTopCard();
            
            //Displaying whose turn it is as well as the current top card
            gameplayView.showTopCard(topCard);
            gameplayView.showPlayerTurn(currentPlayer);
            
            //Call a cardSelection prompt for the player to enter what card they want to play
            UNOCard selectedCard = gameplayView.promptCardSelection(currentPlayer);
            
            //If user selects draw card, which is using null as the placeholder, the player draws a card from the deck
            if (selectedCard == null) 
            {
                //Draw one card and display it
                UNOCard drawnCard = deckController.drawCard();
                gameplayView.showPlayerDrewCard(currentPlayer, drawnCard);
                
                //Adds the drawn card to the player's hand
                handController.addCardToHand(currentPlayer, drawnCard);

                //playerView.showPlayerhand(currentPlayer);
                
                advanceTurn(); //Go to the next player's turn
            } 
            //If the user selects any of the other options, this calls the isValidPlay method to check if its allowed to be played
            else if (isValidPlay(selectedCard, topCard)) 
            {
                //If it can be played, it will be added to the stockPile and then removed from the player's hand
                gameplayView.showCardPlayed(currentPlayer, selectedCard);
                stockPile.addCard(selectedCard);
                handController.removeCardFromHand(currentPlayer, selectedCard);
                //playerView.showPlayerhand(currentPlayer); //Debug
                
                //Checking for the number of cards left and then setting that as the player's score
                int cardsLeft = currentPlayer.getPlayerHand().length;
                currentPlayer.setScore(cardsLeft);
                gameplayView.showPlayerScore(currentPlayer, currentPlayer.getScore());
                
                //If the cards left in the hand is 0 then exit out of the loop
                if (cardsLeft == 0) 
                {
                    break; 
                }
                
                //Check to see if the card played is a special card, whether it is an action or a wild card
                if (CardValueHelper.isSpecialCard(selectedCard.getValue())
                        || CardValueHelper.isWild(selectedCard.getValue())) 
                {
                    //If the card is a special card then it runs through the special card controller to apply the effects 
                    specialCardController.run(selectedCard, currentPlayer);
                } 
                else 
                {
                    //If it is a regular card, then set the colour to the card that was played and then advance to the next player's turn
                    setValidColour(selectedCard.getColour());
                    advanceTurn();
                }
            } 
            else //If the user inputs an invalid number that doesn't match any of the available options, display invalid selection
            {
                gameplayView.showInvalidSelection(topCard, validColour);
            }
        }
    }
    
    //Gets the current player that is playing
    private Player getCurrentPlayer() {

        return game.getPlayers().get(0);
    }
    
    //Checking to see if the card played matches  the stock pile's card or if it is a wild card
    private boolean isValidPlay(UNOCard playedCard, UNOCard topCard) {
        //Debugging to Check Valid Plays
        /*
        System.out.println("Checking play validity:");
        System.out.println("Played Card: " + playedCard);
        System.out.println("Top Card: " + topCard);
        System.out.println("Valid Colour: " + validColour); */
        
        //Check if the card is WILD, then the play is valid, returning true
        if (CardColourHelper.isWild(playedCard.getColour())) 
        {
            return true;
        }
        
        //Check if the card matches the current colour, then return true
        if (playedCard.getColour() == validColour) {
            return true;
        }
        
        //Check if the card matches the current value, then return true
        if (playedCard.getValue() == topCard.getValue()) 
        {
            return true;
        }
        
        //If none of these matches, then return false, marking it as an invalid play
        return false;
    }
    
    //Goes to the next player's turn
    public void advanceTurn() 
    {
        currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
    }
    
    //Gets the index for the next player
    public int getNextPlayerIndex() 
    {
        return (currentPlayerIndex + 1) % game.getPlayers().size();
    }
    
    //Skips the next player entirely, by advancing the turn twice
    public void skipPlayer() 
    {
        advanceTurn();
        advanceTurn();
    }

    //Gets the current top card's colour
    public CardColour getValidColour() {
        return validColour;
    }

    //Sets the valid colour after a wild is played
    public void setValidColour(CardColour validColour) 
    {
        this.validColour = validColour;
    }

    //Gets the current player's index
    public int getCurrentPlayerIndex() 
    {
        return currentPlayerIndex;
    }
}
