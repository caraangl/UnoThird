/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Random;


public class UnoDeck {
    
    private UnoCard[] cards; // Array to store all cards in the deck
    private int cardsInDeck; // Number of cards currently available in the deck
    
    // Constructor: Initializes the card array and fills it with cards
    public UnoDeck(){
        cards = new UnoCard[108]; // UNO decks have 108 cards
    }
    
    // Resets the deck to a full set of UNO cards (standard 108)
    public void reset(){
        UnoCard.Color[] colors = UnoCard.Color.values();
        cardsInDeck = 0;
        
         // Add number and action cards for Red, Yellow, Green, Blue
        for(int i = 0; i < colors.length-1; i++){
            UnoCard.Color color= colors[i];
            
            // One zero per color
            cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(0));
            
            // Two of each card from 1 to 9 per color
            for(int u = 1; u < 10; u++){
                cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(u));
                cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(u));
            }
            
            // Add two of each action card (Skip, Reverse, DrawTwo) per color
            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.DrawTwo, 
                                                        UnoCard.Value.Skip, 
                                                        UnoCard.Value.Reverse};
            
            for(UnoCard.Value value : values){
                cards[cardsInDeck++] = new UnoCard(color, value);
                cards[cardsInDeck++] = new UnoCard(color, value);
            }                                             
        }
        
        // Add 4 Wild and 4 Wild Draw Four cards (color is Wild)
        UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.Wild, 
                                                        UnoCard.Value.WildFour};
        for(UnoCard.Value value : values){
            for(int i = 0; i < 4; i++){
                cards[cardsInDeck++] = new UnoCard(UnoCard.Color.Wild, value);
            }
        }
        
    }
    
    // Replaces the current deck with stockpile
    public void replaceDeckWith(ArrayList<UnoCard> cards){
        this.cards = cards.toArray(new UnoCard[cards.size()]);
        this.cardsInDeck = this.cards.length;
    }
    
    public boolean isEmpty(){
        return cardsInDeck == 0;
    }
    
    // Shuffles the current deck of cards using Fisher-Yates algorithm
    public void shuffle(){
        int s = cards.length;
        Random random = new Random();
        
        for(int i = 0; i < cards.length; i++){
            int randomValue = i + random.nextInt(s - i);
            UnoCard randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }
    
    // Draws a single card from the top of the deck
    public UnoCard drawCard() {
        if (isEmpty()) return null;
        return cards[--cardsInDeck];
    }

    // Draws multiple cards and returns them as an array
    public UnoCard[] drawCard(int count) {
        UnoCard[] drawnCards = new UnoCard[count];
        for (int i = 0; i < count; i++) {
            drawnCards[i] = drawCard();
        }
        return drawnCards;
    }
    
    // getter

    public UnoCard[] getCards() {
        return cards;
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }
    
   
    
    
}