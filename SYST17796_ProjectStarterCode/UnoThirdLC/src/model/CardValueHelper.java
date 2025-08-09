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
}
