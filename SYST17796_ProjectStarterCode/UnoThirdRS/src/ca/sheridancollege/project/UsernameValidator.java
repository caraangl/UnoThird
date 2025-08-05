package ca.sheridancollege.project;

/**
 * @author Ryleigh Smith August 2025
 */

public class UsernameValidator {
    
    // Method returns true if the username is NOT null. 
    public static boolean checkNull(String userName) {
        if (userName == null) {
            return false;
        }
        return true;
    }

    // Method returns true if the username contains NO special characters.
    public static boolean hasNoSymbol(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Throws exception if username is invalid (to be caught in player class).
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
