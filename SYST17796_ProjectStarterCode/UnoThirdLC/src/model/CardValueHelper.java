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
    
     public static boolean isMatchingValue(CardValue v1, CardValue v2) {
        return v1 == v2;
    }
    
    public static boolean isSpecialCard(CardValue value) {
        return value == CardValue.SKIP || value == CardValue.REVERSE || value == CardValue.DRAW_TWO;
    }
    
    public static boolean isWild(CardValue value) {
        return value == CardValue.WILD || value == CardValue.WILD_DRAW_FOUR;
    }
}

