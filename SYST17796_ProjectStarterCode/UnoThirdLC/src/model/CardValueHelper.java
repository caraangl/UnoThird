/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author Lian Asher Caraang - CardValueHelper - Helper Class is in conjunction with the CardValue enumeration, for attributes and main functions 
 */
public class CardValueHelper 
{
    //Creating an array of the available card values
    private static final CardValue[] cardValues = CardValue.values();
    
    //Constructor
    public CardValueHelper() 
    {
    }
    
    //Returns card value based on the input
    public static CardValue getValue(int i) 
    {
        return cardValues[i];
    }
    
    //As the starting card to start the stock pile, WILD cards and ACTION cards aren't allowed to be the starter card 
    public static boolean isAllowedAsStartingCard(CardValue value) 
    {
        //Check the incoming Card Value and if they are one of the action or wild cards, return false, if they aren't, return true
        switch (value) 
        {
            case WILD:
            case WILD_DRAW_FOUR:
            case DRAW_TWO:
            case SKIP:
            case REVERSE:
                return false;
            default:
                return true;
        }
    }
    
    //Checking between the stockPile's top card value and the player's placed card value, if they match, return true;
    public static boolean isMatchingValue(CardValue v1, CardValue v2) 
    {
        return v1 == v2;
    }
    
    //Checking the value of the card if it is a SKIP, REVERSE or DRAW_TWO action card, if so, return true 
    public static boolean isSpecialCard(CardValue value) 
    {
        return value == CardValue.SKIP || value == CardValue.REVERSE || value == CardValue.DRAW_TWO;
    }
    
    //Checking the value of the card if it is a WILD or a WILD_DRAW_FOUR card, if so, return true 
    public static boolean isWild(CardValue value) 
    {
        return value == CardValue.WILD || value == CardValue.WILD_DRAW_FOUR;
    }
}
