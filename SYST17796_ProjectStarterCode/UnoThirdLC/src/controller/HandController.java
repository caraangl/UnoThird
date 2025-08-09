/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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
}
