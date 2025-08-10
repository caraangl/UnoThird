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

/**
 * GameController controls the main core Gameplay of UNO (Checking Hands,
 * Verifying Cards)
 *
 * @author Lian Asher Caraang August 2025
 */
public class GameController {

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
    private CardColour validColour;
    private SpecialCardController specialCardController;

    //GameController Class Constructor
    public GameController() {
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
        handController = new HandController(deckController, playerView);

        stockPile = new StockPile();
        this.specialCardController = new SpecialCardController(game, deckController, handController, gameplayView, this);
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

        /////////////////////
        generateDeck();
        dealInitialHands();
        startStockPile();
        gameplay();
    }

    ////////////////////
    public void generateDeck() {
        gameplayView.showShufflingDeck();
        deckController.reset();    // Build the deck
        deckController.shuffle();  // Shuffle the deck
        // for testing purposes to make sure deck was working
        /*for (UNOCard card : deckOfCards.getCards()) {
            System.out.println(card);
        } */
    }

    // Method to generate initial hand 
    public void dealInitialHands() {
        for (Player player : game.getPlayers()) {
            handController.generatePlayerHand(player);
        }
    }

    public void startStockPile() {
        while (true) {
            UNOCard card = deckController.drawCard();

            if (!CardValueHelper.isAllowedAsStartingCard(card.getValue())) {
                //System.out.println("Returning Card: " + card);
                deckController.returnCardToDeck(card);

                deckController.shuffle();
                continue;
            }

            stockPile.addCard(card);
            validColour = card.getColour();
            //System.out.println("Starting card on stock pile: " + card);
            break;
        }

        //UNOCard currentCard = stockPile.getTopCard();
        //System.out.println("Current card in play: " + currentCard);
    }

    public void gameplay() {
        while (true) {

            currentPlayer = game.getPlayers().get(currentPlayerIndex);
            UNOCard topCard = stockPile.getTopCard();

            gameplayView.showTopCard(topCard);
            gameplayView.showPlayerTurn(currentPlayer);
            UNOCard selectedCard = gameplayView.promptCardSelection(currentPlayer);

            if (selectedCard == null) {
                // Implement draw card logic here
                // Draw one card from the deck
                UNOCard drawnCard = deckController.drawCard();
                gameplayView.showPlayerDrewCard(currentPlayer, drawnCard);
                // Add the drawn card to the player's hand
                handController.addCardToHand(currentPlayer, drawnCard);

                //playerView.showPlayerhand(currentPlayer);

                advanceTurn();
            } else if (isValidPlay(selectedCard, topCard)) {
                gameplayView.showCardPlayed(currentPlayer, selectedCard);
                stockPile.addCard(selectedCard);
                handController.removeCardFromHand(currentPlayer, selectedCard);
                //playerView.showPlayerhand(currentPlayer);

                int cardsLeft = currentPlayer.getPlayerHand().length;
                currentPlayer.setScore(cardsLeft);
                gameplayView.showPlayerScore(currentPlayer, cardsLeft);
                if (cardsLeft == 0) {
                    break;  // Exit gameplay loop
                }

                if (CardValueHelper.isSpecialCard(selectedCard.getValue())
                        || CardValueHelper.isWild(selectedCard.getValue())) {
                    specialCardController.run(selectedCard, currentPlayer);
                } else {
                    setValidColour(selectedCard.getColour());
                    advanceTurn();
                }
            } else {
                gameplayView.showInvalidSelection(topCard, validColour);
            }
        }
    }

    private Player getCurrentPlayer() {

        return game.getPlayers().get(0);
    }

    private boolean isValidPlay(UNOCard playedCard, UNOCard topCard) {
        /*
        System.out.println("Checking play validity:");
        System.out.println("Played Card: " + playedCard);
        System.out.println("Top Card: " + topCard);
        System.out.println("Valid Colour: " + validColour); */

        if (CardColourHelper.isWild(playedCard.getColour())) {
            return true;
        }

        if (playedCard.getColour() == validColour) {
            return true;
        }

        if (playedCard.getValue() == topCard.getValue()) {
            return true;
        }

        return false;
    }

    public void advanceTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
    }
    
     public int getNextPlayerIndex() 
     {
        return (currentPlayerIndex + 1) % game.getPlayers().size();
    }


    public void skipPlayer() {
        advanceTurn();
        advanceTurn();
    }

    // Getter for valid colour
    public CardColour getValidColour() {
        return validColour;
    }

// Setter for valid colour
    public void setValidColour(CardColour validColour) {
        this.validColour = validColour;
    }

// Getter for current player index
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}
