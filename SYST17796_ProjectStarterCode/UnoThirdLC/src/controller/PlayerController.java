package controller;

//Imports
import java.util.Scanner;
import model.PasswordValidator;
import model.Player;
import model.UsernameValidator;
import view.PlayerView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Player controller controls the player registration, checking for valid
 * usernames and passwords
 *
 * @author Lian Asher Caraang August 2025
 */
public class PlayerController 
{
    //View Variables
    private PlayerView playerView;

    //PlayerController Class Constructor
    public PlayerController(PlayerView view) 
    {
        this.playerView = view;
    }

    //registerPlayer Method - Registers the player after checking for valid Username and Password
    public Player registerPlayer() 
    {
        //Loop until valid inputs are inputted
        while (true) 
        {
            //Call the playerView's getUsername Method for the prompt and the input
            String username = playerView.getUsername();

            try 
            {
                //Call the UsernameValidator's checkUsername Method to check if the username is valid
                UsernameValidator.checkUsername(username);

                //Loop until the Password inputs are Valid
                while (true) 
                {
                    //Call the playerView's getPassword Method for the prompt and the input
                    String password = playerView.getPassword();

                    try 
                    {
                        //Call the PasswordValidator's checkPassword Method to check if the username is valid
                        PasswordValidator.checkPassword(password);

                        //Call playerView's showRegistrationSuccess Method
                        playerView.showRegistrationSuccess();

                        return new Player(username, password);
                    } 
                    catch (Exception e) //Call playerView's showRegistrationFailure Method
                    {
                        playerView.showRegistrationFailure(e.getMessage());
                    }
                }
            } 
            catch (Exception e) //Call playerView's showRegistrationFailure Method
            {
                playerView.showRegistrationFailure(e.getMessage());
            }
        }
    }
}
