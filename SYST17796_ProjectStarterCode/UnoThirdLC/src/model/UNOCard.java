/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LianL
 */
public class UNOCard {

    private final CardColour cardColour;
    private final CardValue cardValue;

    public UNOCard(CardColour cardColour, CardValue cardValue) {
        this.cardColour = cardColour;
        this.cardValue = cardValue;
    }

    public CardColour getColour() {
        return this.cardColour;
    }

    public CardValue getValue() {
        return this.cardValue;
    }

    public String toString() {
        return cardColour + " " + cardValue;
    }
}
