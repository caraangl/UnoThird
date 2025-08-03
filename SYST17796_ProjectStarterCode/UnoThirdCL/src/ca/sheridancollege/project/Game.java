/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game {

    private int currentPlayer;
    private String[] playerIds;

    private UnoDeck deck;
    private ArrayList<ArrayList<UnoCard>> playerHand;
    private ArrayList<UnoCard> stockPile;

    private UnoCard.Color validColor;
    private UnoCard.Value validValue;

    private boolean gameDirection;

    //Constructor: start game with player IDs
    public Game(String[] p_ids) {
        deck = new UnoDeck();
        deck.reset();
        deck.shuffle();

        stockPile = new ArrayList<>();
        playerIds = p_ids;
        currentPlayer = 0;
        gameDirection = false;// start game clockwise

        playerHand = new ArrayList<>();

        //deal 7 cards to each players
        for (String p_id : p_ids) {
            ArrayList<UnoCard> hand = new ArrayList<>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }
    }

    public void start(Game game) {
        UnoCard card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        // Redraw for special cards not allowed to start
        if (card.getValue() == UnoCard.Value.Wild ||
            card.getValue() == UnoCard.Value.WildFour ||
            card.getValue() == UnoCard.Value.DrawTwo) {
            start(game);
            return;
        }

        if (card.getValue() == UnoCard.Value.Skip) {
            JOptionPane.showMessageDialog(null, createMessage(playerIds[currentPlayer] + " was skipped"));
            advanceTurn();
        }

        if (card.getValue() == UnoCard.Value.Reverse) {
            JOptionPane.showMessageDialog(null, createMessage(playerIds[currentPlayer] + " changed direction!"));
            gameDirection ^= true;
            currentPlayer = playerIds.length - 1;
        }

        stockPile.add(card);
    }

    public UnoCard getTopCard() {
        return new UnoCard(validColor, validValue);
    }

    // check for winner
    public boolean isGameOver() {
        for (String player : playerIds) {
            if (hasEmptyHand(player)) 
                return true;
        }
        return false;
    }

    public String getCurrentPlayer() {
        return playerIds[currentPlayer];
    }

    public String getPreviousPlayer(int i) {
        int index = (currentPlayer - i + playerIds.length) % playerIds.length;
        return playerIds[index];
    }
    
    //List of player
    public String[] getPlayer() {
        return playerIds;
    }

    public ArrayList<UnoCard> getPlayerHand(String p_id) {
        int index = Arrays.asList(playerIds).indexOf(p_id);
        return playerHand.get(index);
    }

    //Get the nummber of cards on player's hand
    public int getPlayerHandSize(String p_id) {
        return getPlayerHand(p_id).size();
    }
    
    //Get a player specific card
    public UnoCard getPlayerCard(String p_id, int choice) {
        return getPlayerHand(p_id).get(choice);
    }
    
    //Check if correct card is played
    public boolean validCardPlay(UnoCard card) {
        return card.getColor() == validColor || card.getValue() == validValue || card.getColor() == UnoCard.Color.Wild;
    }

    public void checkPlayerTurn(String p_id) throws InvalidPlayerTurnException {
        if (!playerIds[currentPlayer].equals(p_id)) {
            throw new InvalidPlayerTurnException("It is not " + p_id + "'s turn", p_id);
        }
    }

   // draw card insted of play
    public void submitDraw(String p_id) throws InvalidPlayerTurnException {
        checkPlayerTurn(p_id);
        
        //Refill deck from stockpile when empty
        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        getPlayerHand(p_id).add(deck.drawCard());
        advanceTurn();
    }

    // Player playing their card
    public void submitPlayerCard(String p_id, UnoCard card, UnoCard.Color declaredColor)
            throws InvalidPlayerTurnException, InvalidColorSubmissionException, InvalidValueSubmissionException {

        checkPlayerTurn(p_id);
        ArrayList<UnoCard> p_hand = getPlayerHand(p_id);

        if (!p_hand.contains(card)) return;  // Prevent cheating

        if (!validCardPlay(card)) {
            if (card.getColor() == UnoCard.Color.Wild) {
                // Allow setting wilds
                validColor = declaredColor;
                validValue = card.getValue();
            } else {
                throw new InvalidColorSubmissionException("Invalid color submission", card.getColor(), validColor);
            }
        }

        if (card.getColor() != UnoCard.Color.Wild && card.getValue() != validValue && card.getColor() != validColor) {
            throw new InvalidValueSubmissionException("Invalid value submission", card.getValue(), validValue);
        }

        p_hand.remove(card);

        if (hasEmptyHand(p_id)) {
            JOptionPane.showMessageDialog(null, createMessage(p_id + " WINS!"));
            System.exit(0);
        }

        stockPile.add(card);
        validColor = card.getColor();
        validValue = card.getValue();

        handleSpecialCard(card, declaredColor);
        advanceTurn();
    }
    
   //Handle special cards effects
    private void handleSpecialCard(UnoCard card, UnoCard.Color declaredColor) {
        if (card.getColor() == UnoCard.Color.Wild) {
            validColor = declaredColor;
        }

        String targetPlayer = playerIds[currentPlayer];

        if (card.getValue() == UnoCard.Value.DrawTwo) {
            getPlayerHand(targetPlayer).add(deck.drawCard());
            getPlayerHand(targetPlayer).add(deck.drawCard());
            JOptionPane.showMessageDialog(null, createMessage(targetPlayer + " drew 2 cards"));
        }

        if (card.getValue() == UnoCard.Value.WildFour) {
            for (int i = 0; i < 4; i++) {
                getPlayerHand(targetPlayer).add(deck.drawCard());
            }
            JOptionPane.showMessageDialog(null, createMessage(targetPlayer + " drew 4 cards"));
        }

        if (card.getValue() == UnoCard.Value.Skip) {
            JOptionPane.showMessageDialog(null, createMessage(targetPlayer + " was skipped"));
            advanceTurn(); // Skip next player
        }

        if (card.getValue() == UnoCard.Value.Reverse) {
            JOptionPane.showMessageDialog(null, createMessage(targetPlayer + " reversed the game direction!"));
            gameDirection ^= true;
        }
    }

    // check if a player hand is empty
    private boolean hasEmptyHand(String p_id) {
        return getPlayerHand(p_id).isEmpty();
    }

    private void advanceTurn() {
        if (!gameDirection) {
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        } else {
            currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;
        }
    }

    private JLabel createMessage(String text) {
        JLabel message = new JLabel(text);
        message.setFont(new Font("Arial", Font.BOLD, 48));
        return message;
    }

    // --- Exception classes ---

    // thrown if it's now player's turn
    public static class InvalidPlayerTurnException extends Exception {
        private final String playerId;

        public InvalidPlayerTurnException(String message, String p_id) {
            super(message);
            this.playerId = p_id;
        }

        public String getPlayerId() {
            return playerId;
        }
    }

    // Thrown if a card of the wrong color is played
    public static class InvalidColorSubmissionException extends Exception {
        private final UnoCard.Color expected, actual;

        public InvalidColorSubmissionException(String message, UnoCard.Color actual, UnoCard.Color expected) {
            super(message);
            this.actual = actual;
            this.expected = expected;
        }
    }
    
    // Thrown if a card of the wrong value is played
    public static class InvalidValueSubmissionException extends Exception {
        private final UnoCard.Value expected, actual;

        public InvalidValueSubmissionException(String message, UnoCard.Value actual, UnoCard.Value expected) {
            super(message);
            this.actual = actual;
            this.expected = expected;
        }
    }
}

