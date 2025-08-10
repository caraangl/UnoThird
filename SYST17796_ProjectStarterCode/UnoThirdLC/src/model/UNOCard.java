/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lian Asher Caraang - Main Actions and Attributes for a singular UNOCard needed for the Game
 */
public class UNOCard 
{
    //Variables
    private final CardColour cardColour;
    private final CardValue cardValue;
    
    //Constructor requiring cardColour and cardValue
    public UNOCard(CardColour cardColour, CardValue cardValue) 
    {
        this.cardColour = cardColour;
        this.cardValue = cardValue;
    }
    
    //Returns the card colour
    public CardColour getColour() 
    {
        return this.cardColour;
    }
    
    //Returns the card value
    public CardValue getValue() 
    {
        return this.cardValue;
    }
    
    //Returns the full string that has the full card colour and value
    @Override
    public String toString() {
        return cardColour + " " + cardValue;
    }
}
