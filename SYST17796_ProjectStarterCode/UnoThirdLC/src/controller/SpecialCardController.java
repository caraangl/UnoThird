/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import view.*;

/**
 *
 * @author LianL
 */
public class SpecialCardController {

    private Game game;
    private DeckController deckController;
    private HandController handController;
    private GameplayView gameplayView;
    private GameController gameController; // so we can call advanceTurn() and skipPlayer()

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

    public void run(UNOCard card, Player currentPlayer) 
    {
        switch (card.getValue()) {
            case WILD_DRAW_FOUR:
                handleWildDrawFour(currentPlayer);
                gameController.skipPlayer(); // skip next player
                break;

            case WILD:
                handleWild(currentPlayer);
                gameController.advanceTurn();
                break;

            case SKIP:
                gameplayView.showSkipPlayed();
                gameController.setValidColour(card.getColour());
                gameController.skipPlayer();
                break;

            case REVERSE:
                gameplayView.showReversePlayed();
                gameController.setValidColour(card.getColour());
                // In a 2-player game, reverse = skip
                gameController.skipPlayer();
                // If more than 2 players, you'd reverse play order instead
                break;

            case DRAW_TWO:
                handleDrawTwo(currentPlayer);
                gameController.skipPlayer();
                break;

            default:
                gameController.setValidColour(card.getColour());
                gameController.advanceTurn();
                break;
        }
    }

    private void handleWildDrawFour(Player currentPlayer) {
        int nextIndex = gameController.getNextPlayerIndex();
        Player nextPlayer = game.getPlayers().get(nextIndex);

        for (int i = 0; i < 4; i++) {
            handController.addCardToHand(nextPlayer, deckController.drawCard());
        }
        gameplayView.showPlayerDrewCards(nextPlayer, 4);
        handleWild(currentPlayer);
    }

    private void handleWild(Player currentPlayer) {
        CardColour chosenColour = gameplayView.promptColourSelection(currentPlayer);
        gameController.setValidColour(chosenColour);
        gameplayView.showColorChanged(currentPlayer, chosenColour);
    }

    private void handleDrawTwo(Player currentPlayer) {
        int nextIndex = gameController.getNextPlayerIndex();
        Player nextPlayer = game.getPlayers().get(nextIndex);

        for (int i = 0; i < 2; i++) {
            handController.addCardToHand(nextPlayer, deckController.drawCard());
        }
        gameplayView.showPlayerDrewTwoCards(nextPlayer);
    }
}
