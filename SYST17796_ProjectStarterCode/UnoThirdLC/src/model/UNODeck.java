/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lian Asher Caraang - Main Actions and Attributes for the UNODeck needed for the Game
 */
public class UNODeck 
{
    //Variables
    private UNOCard[] cards; //Array to store all cards in the deck
    private int cardsInDeck; //Number of cards currently available in the deck
    
    //Constructor to create a deck that has 108 Cards
    public UNODeck() 
    {
        cards = new UNOCard[108]; 
    }
    
    //Getter Method to return the cards array
    public UNOCard[] getCards() {
        return cards;
    }
    
    //Getter Method to get cards currently populated 
    public int getCardsInDeck() {
        return cardsInDeck;
    }
    
    //Setter Method to set cards in the array
    public void setCards(UNOCard[] cards) 
    {
        this.cards = cards;
    }
    
    //Getter Method to set current number of cards in the deck
    public void setCardsInDeck(int count) 
    {
        this.cardsInDeck = count;
    }
    
    //Add card, puts the card from the calling function and then places it at the top of the array, at the top of the deck
    public void addCardToTop(UNOCard card) 
    {
        if (cardsInDeck < cards.length) 
        {
            cards[cardsInDeck] = card;  
            cardsInDeck++;
        } 
        else 
        {
            System.out.println("Deck is full! Cannot add card back.");
        }
    }

}
