/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Ryleigh Smith August 2025
 */
public class UsernameValidator {
    
    // Returns true if the username is not null
    public static boolean checkNull(String userName) {
        if (userName == null) {
            return false;
        }
        return true;
    }

    // Returns true when username contains NO special characters
    public static boolean hasNoSymbol(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void checkUserName(String userName) {
        try {
            checkNull(userName);
            hasNoSymbol(userName);
        } catch (Exception e) {
            System.out.println("Player registration rejected. "
                    + "\nUsername Invalid.");
        }
    }
}
