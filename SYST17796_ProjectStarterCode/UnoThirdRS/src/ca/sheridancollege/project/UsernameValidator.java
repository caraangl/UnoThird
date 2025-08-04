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

    // Throws exception (if username invalid) to be caught in player 
    public static void checkUserName(String userName) throws Exception {
        if (!checkNull(userName)) {
            throw new Exception("Invalid Username. \nCannot be null.");
        }
        if (!hasNoSymbol(userName)) {
            throw new Exception("Invalid Username. "
                    + "\nCannot include special characters.");
        }
    }
}
