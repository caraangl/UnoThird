/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;

/**
 *
 * @author Mohamed Hamed - Checks for valid User Input for the Start of the Application
 */
public class UserInput 
{

    /**
     * Prompts the user until they enter 1 (start) or 2 (exit).
     *
     * @return true if user chose 1 (start), false if user chose 2 (exit)
     */
    public static Boolean promptStartOrExit(Scanner sc) 
    {
        String input = sc.nextLine().trim();
        while (true) 
        {
            try 
            {
                int choice = Integer.parseInt(input);
                
                if (choice == 1) 
                {
                    return true;   // start
                } 
                else if (choice == 2) 
                {
                    return false;  // exit
                } 
                else 
                {
                    return null;
                }
            } catch (NumberFormatException e) 
            {
                return null;
            }
        }
    }

    /** || CODE NOT IMPLEMENTED - Lian Asher Caraang
     * Prompts the user until they enter a valid index in [0..7].
     *
     * @return the valid index chosen by the user
     */
    /*public static int promptValidIndex(String input, int maxIndex) {
        try {
            int idx = Integer.parseInt(input);
            if (idx >= 0 && idx <= maxIndex) {
                return idx;
            }
        } catch (NumberFormatException e) {
            // Not a number
        }
        return -1;  // invalid input
    } */
}
