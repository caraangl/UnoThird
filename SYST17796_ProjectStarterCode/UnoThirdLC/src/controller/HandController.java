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

/* HandController the player's hands generation and card removal and addition
 * usernames and passwords
 *
 * @author Cuong Luong, Lian Asher Caraang August 2025
 */
public class HandController 
{
    
    //Controller Variables
    DeckController deckController;
    
    //View Variables
    PlayerView playerView;
    
    //Constructor
    public HandController(DeckController deckController, PlayerView playerView) 
    {
        this.deckController = deckController;
        this.playerView = playerView;
    }
    
    //Method to generate a new hand of 7 UNOCard objects for a player
    public void generatePlayerHand(Player player) 
    {
        UNOCard[] hand = deckController.drawCard(7);
        player.setPlayerHand(hand);
        playerView.showPlayerhand(player);
    }
    
    //Method to remove a card from a player's hand based on the UNOCard inputted
    public void removeCardFromHand(Player player, UNOCard card) 
    {
        //Creates a new hand of UNoCard and then converts it to a list to make addition and removal easier
        UNOCard[] hand = player.getPlayerHand();
        List<UNOCard> handList = new ArrayList<>(Arrays.asList(hand));
        
        if (handList.remove(card)) //After removal, it sets the new hand for the player
        {
            player.setPlayerHand(handList.toArray(new UNOCard[0])); 
        }
    }
    
    //Method to add a card from a player's hand based on the UNOCard inputted
    public void addCardToHand(Player player, UNOCard card) 
    {
        //Creates a new hand of UNoCard and then converts it to a list to make addition and removal easier
        List<UNOCard> hand = new ArrayList<>(Arrays.asList(player.getPlayerHand()));
        hand.add(card);
        player.setPlayerHand(hand.toArray(new UNOCard[0])); //Sets the new player hand after addition
    }
}
