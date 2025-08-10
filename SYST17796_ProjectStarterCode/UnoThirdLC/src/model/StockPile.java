/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LianL
 */
public class StockPile {

    private final List<UNOCard> stockPile;

    public StockPile() {
        stockPile = new ArrayList<>();
    }

    public void addCard(UNOCard card) {
        stockPile.add(card);
    }

    public UNOCard getTopCard() {
        if (stockPile.isEmpty()) {
            return null;
        }
        return stockPile.get(stockPile.size() - 1);
    }

    public void clear() {
        stockPile.clear();
    }

    // Get all cards (optional)
    public List<UNOCard> getAllCards() {
        return new ArrayList<>(stockPile);
    }
}
