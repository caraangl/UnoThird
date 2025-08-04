/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Ryleigh Smith August 2025
 */
public class PasswordValidator {

    // Returns true if the password length is greater or equal to 6
    public static boolean checkPassLength(String password) {
        if (password.length() >= 6) {
            return true;
        }
        return false;
    }

    // Returns true when password contains NO special characters
    public static boolean hasNoSymbol(String password) {
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void checkPasword(String password) {
        try {
            checkPassLength(password);
            hasNoSymbol(password);
        } catch (Exception e) {
            System.out.println("Player registration rejected. "
                    + "\nPassword Invalid.");
        }
    }
}
