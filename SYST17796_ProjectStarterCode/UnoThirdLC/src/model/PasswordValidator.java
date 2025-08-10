package model;

/**
 * @author Ryleigh Smith August 2025 - Validates the incoming password input
 */
public class PasswordValidator 
{
    // Method returns true if the password length is greater or equal to 6.
    public static boolean checkPassLength(String password) 
    {
        if (password.length() >= 6) 
        {
            return true;
        }
        return false;
    }

    // Method returns true when the password contains NO special characters.
    public static boolean hasNoSymbol(String password) 
    {
        for (int i = 0; i < password.length(); i++) 
        {
            char c = password.charAt(i);
            
            if (!Character.isLetter(c) && !Character.isDigit(c)) 
            {
                return false;
            }
        }
        
        return true;
    }

    // Throws exception if password is invalid (to be caught in player class).
    public static void checkPassword(String password) throws Exception 
    {
        if (!checkPassLength(password)) 
        {
            throw new Exception("Invalid Password. "
                    + "\nMust be at least 6 characters.");
        }
        if (!hasNoSymbol(password)) 
        {
            throw new Exception("Invalid Password. "
                    + "\nCannot include special characters.");
        }
    }
}
