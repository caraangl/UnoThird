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
 * DeckController controls the deck building, drawing and addition of cards 
 *
 * @author Cuong Luong August 2025
 */
public class DeckController 
{
    //Class Variables
    private final UNODeck deck;
    
    //Constructor
    public DeckController(UNODeck deck) 
    {
        this.deck = deck;
    }
    
    //Resets and Builds a new Deck
    public void reset() 
    {
        //Set the Colours
        CardColour[] colours = CardColour.values();
        deck.setCardsInDeck(0); //Current deck has 0 cards
        
        //Loop through all of the available colours
        for (int i = 0; i < colours.length - 1; i++) 
        {
            CardColour colour = colours[i];
            
            //One Zero Per Colour
            deck.getCards()[deck.getCardsInDeck()] = new UNOCard(colour, CardValueHelper.getValue(0));
            deck.setCardsInDeck(deck.getCardsInDeck() + 1); 

            //Two of each card from 1 to 9 for every colour
            for (int u = 1; u < 10; u++) 
            {
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(colour, CardValueHelper.getValue(u));
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(colour, CardValueHelper.getValue(u));
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
            }

            //Two of each action card (Skip, Reverse, DrawTwo) per colour
            CardValue[] values = new CardValue[]{CardValue.DRAW_TWO,
                CardValue.SKIP,
                CardValue.REVERSE};

            for (CardValue value : values) 
            {
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(colour, value);
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(colour, value);
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
            }
        }

        //Add 4 Wild and 4 Wild Draw Four cards (colour is Wild)
        CardValue[] wildValues = new CardValue[]{CardValue.WILD,
            CardValue.WILD_DRAW_FOUR};
        
        for (CardValue value : wildValues) 
        {
            for (int i = 0; i < 4; i++) {
                deck.getCards()[deck.getCardsInDeck()] = new UNOCard(CardColour.WILD, value);
                deck.setCardsInDeck(deck.getCardsInDeck() + 1);
            }
        }
    }
    
    //Check for the deck if it is empty
    public boolean isEmpty() 
    {
        return deck.getCardsInDeck() == 0;
    }
    
    //Shuffle and randomize the cards
    public void shuffle() {
        int s = deck.getCards().length;
        Random random = new Random();

        for (int i = 0; i < deck.getCards().length; i++) 
        {
            int randomValue = i + random.nextInt(s - i);
            UNOCard randomCard = deck.getCards()[randomValue];
            deck.getCards()[randomValue] = deck.getCards()[i];
            deck.getCards()[i] = randomCard;
        }
    }
    
    //Draw a card from the top of the deck
    public UNOCard drawCard() 
    {
        if (isEmpty()) 
        {
            return null;
        }
        
        UNOCard card = deck.getCards()[deck.getCardsInDeck() - 1];
        deck.setCardsInDeck(deck.getCardsInDeck() - 1);
        return card;
    }
    
    //Draw a number of cards based on the action card
    public UNOCard[] drawCard(int count) 
    {
        UNOCard[] drawnCards = new UNOCard[count];
        for (int i = 0; i < count; i++) 
        {
            drawnCards[i] = drawCard();
        }
        return drawnCards;
    }
    
    //Return a card to the top of the deck
    public void returnCardToDeck(UNOCard card) {
        deck.addCardToTop(card);
    }
}
