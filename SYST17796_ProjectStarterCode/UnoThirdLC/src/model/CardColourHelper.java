/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lian Asher Caraang, Cuong Luong - CardColourHelper 
 * Helper Class is in conjunction with the CardColour enumeration, for attributes and main functions 
 */
public class CardColourHelper 
{
    //Array of available card colours
    private static final CardColour[] cardColours = CardColour.values();
    
    //Constructor
    public CardColourHelper() 
    {
    }
    
    //Returns the colour of a card based on the input
    public static CardColour getColours(int i) 
    {
        return cardColours[i];
    }
    
    //Returns true if the card colour is a wild card
    public static boolean isWild(CardColour colour) 
    {
        return colour == CardColour.WILD;
    }
    
    //Returns true if the card colour matches the stockPile card colour
    public static boolean isMatchingColour(CardColour c1, CardColour c2) 
    {
        return c1 == c2;
    }
}
