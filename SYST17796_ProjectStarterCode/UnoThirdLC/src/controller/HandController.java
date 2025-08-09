/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Player;
import model.UNOCard;
import view.PlayerView;
/**
 *
 * @author LianL
 */
public class HandController {
        
    DeckController deckController; 
    PlayerView playerView;
    
    public HandController(DeckController deckController, PlayerView playerView)
    {
        this.deckController = deckController; 
        this.playerView = playerView;
    }
    
    public void generatePlayerHand(Player player) 
    {
        UNOCard[] hand = deckController.drawCard(7);
        player.setPlayerHand(hand);
        playerView.showPlayerhand(player);
    } 
    
    public void removeCardFromHand(Player player, UNOCard card) {
        UNOCard[] hand = player.getPlayerHand();
        List<UNOCard> handList = new ArrayList<>(Arrays.asList(hand));
        if (handList.remove(card)) {
            player.setPlayerHand(handList.toArray(new UNOCard[0]));
        }
    }
    
    public void addCardToHand(Player player, UNOCard card) {
    // Assuming Player class has getPlayerHand() returning a List or array
    // If playerHand is an array, you might want to convert to List or manage resizing
    List<UNOCard> hand = new ArrayList<>(Arrays.asList(player.getPlayerHand()));
    hand.add(card);
    player.setPlayerHand(hand.toArray(new UNOCard[0]));
}

}
