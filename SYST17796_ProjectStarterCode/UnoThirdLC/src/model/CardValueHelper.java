/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LianL
 */
public class CardValueHelper 
{
    private static final CardValue[] cardValues = CardValue.values();
    
        public static CardValue getValue(int i) 
        {
            return cardValues[i];
        }

    public static boolean isAllowedAsStartingCard(CardValue value) {
        switch(value) 
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
}

