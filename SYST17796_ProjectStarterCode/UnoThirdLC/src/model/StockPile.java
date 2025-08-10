/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//Imports
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lian Asher Caraang - Main Actions and Attributes for a StockPile where the cards are matched and played 
 */
public class StockPile 
{
    //Attributes, using a List of UNOCard as the stockPile
    private final List<UNOCard> stockPile;
    
    //Constructor
    public StockPile() 
    {
        stockPile = new ArrayList<>();
    }
    
    //Adds a card to the stockPile ArrayList
    public void addCard(UNOCard card) 
    {
        stockPile.add(card);
    }
    //Gets the card at the top of the stockPile ArrayList
    public UNOCard getTopCard() 
    {
        //If stock pile is currently empty, return null 
        if (stockPile.isEmpty()) 
        {
            return null;
        }
        
        //Return the top card of the ArrayList
        return stockPile.get(stockPile.size() - 1);
    }
    
    //Returns all of the cards of the stockPile ArrayList
    public List<UNOCard> getAllCards() 
    {
        return new ArrayList<>(stockPile);
    }
}
