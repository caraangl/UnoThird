/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UNOCard;
import model.UNODeck;
import model.CardValue;
import model.CardColour;
import java.util.ArrayList;
import java.util.Random;
import model.CardValueHelper;



/**
 *
 * @author LianL
 */
public class DeckController {
    
    private final UNODeck deck;
    
    public DeckController(UNODeck deck) {
        this.deck = deck;
    }
    
    public void reset(){
        CardColour[] colors = CardColour.values();
        deck.setCardsInDeck(0);
        
        for(int i = 0; i < colors.length - 1; i++){
            CardColour color= colors[i];
            
            // One zero per color
            deck.getCards()[deck.getCardsInDeck()] = new UNOCard(color, CardValueHelper.getValue(0));
            deck.setCardsInDeck(deck.getCardsInDeck() + 1);
            
            // Two of each card from 1 to 9 per color
            for(int u = 1; u < 10; u++){
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(color, CardValueHelper.getValue(u));
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(color, CardValueHelper.getValue(u));
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
            }
            
            // Two of each action card (Skip, Reverse, DrawTwo) per color
            CardValue[] values = new CardValue[]{CardValue.DRAW_TWO, 
                                                 CardValue.SKIP, 
                                                 CardValue.REVERSE};
            
            for(CardValue value : values){
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(color, value);
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(color, value);
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
            }                                             
        }
        
        // Add 4 Wild and 4 Wild Draw Four cards (color is Wild)
        CardValue[] wildValues = new CardValue[]{CardValue.WILD, 
                                                 CardValue.WILD_DRAW_FOUR};
        for(CardValue value : wildValues){
            for(int i = 0; i < 4; i++){
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(CardColour.WILD, value);
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
            }
        }
    }
    
    public void replaceDeckWith(ArrayList<UNOCard> cards){
        deck.setCards(cards.toArray(new UNOCard[cards.size()]));
        deck.setCardsInDeck(deck.getCards().length);
    }
    
    public boolean isEmpty(){
        return deck.getCardsInDeck() == 0;
    }
    
    public void shuffle(){
        int s = deck.getCards().length;
        Random random = new Random();
        
        for(int i = 0; i < deck.getCards().length; i++){
            int randomValue = i + random.nextInt(s - i);
            UNOCard randomCard = deck.getCards()[randomValue];
            deck.getCards()[randomValue] = deck.getCards()[i];
            deck.getCards()[i] = randomCard;
        }
    }
    
    public UNOCard drawCard() {
        if (isEmpty()) return null;
        UNOCard card = deck.getCards()[deck.getCardsInDeck() - 1];
        deck.setCardsInDeck(deck.getCardsInDeck() - 1);
        return card;
    }

    public UNOCard[] drawCard(int count) {
        UNOCard[] drawnCards = new UNOCard[count];
        for (int i = 0; i < count; i++) {
            drawnCards[i] = drawCard();
        }
        return drawnCards;
    }
}