/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author LianL, Cuong Luong
 */
public class CardColourHelper {
   
    private static final CardColour[] cardColours = CardColour.values();

    public static CardColour getColours(int i) 
    {
        return cardColours[i];
    }
}