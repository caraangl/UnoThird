/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LianL
 */
public class UNODeck {

    private UNOCard[] cards; // Array to store all cards in the deck
    private int cardsInDeck; // Number of cards currently available in the deck

    public UNODeck() {
        cards = new UNOCard[108]; // UNO decks have 108 cards
    }

    public UNOCard[] getCards() {
        return cards;
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCards(UNOCard[] cards) {
        this.cards = cards;
    }

    public void setCardsInDeck(int count) {
        this.cardsInDeck = count;
    }

    public void addCardToTop(UNOCard card) {
        if (cardsInDeck < cards.length) {
            cards[cardsInDeck] = card;  // add card at the end (top)
            cardsInDeck++;
        } else {
            System.out.println("Deck is full! Cannot add card back.");
        }
    }

}
