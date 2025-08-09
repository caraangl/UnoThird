package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Imports
import java.util.Scanner;
import model.PasswordValidator;
import model.Player;
import model.UsernameValidator;
import view.PlayerView;
import model.Game;

/**
 * GameController controls the main core Gameplay of UNO (Checking Hands, Verifying Cards)
 * @author Lian Asher Caraang August 2025
 */
public class GameController 
{
    //Controller Variables
    private PlayerController playerController;
    
    //Class Variables
    private Game game = new Game();
    
    //View Variables
    private PlayerView playerView;
    
    //GameController Class Constructor
    public GameController() 
    {
        //Scanner Input Variable
        Scanner input = new Scanner(System.in);
        
        //playerView View Variable
        playerView = new PlayerView(input);
        
        //playerController Controller Variable
        playerController = new PlayerController(playerView);
    }
    
    //startGame - Based on number players, Register the Players using playerController
    public void startGame(int numPlayers) 
    {
        //Call the playerController's registerPlayer() method and loop through the number of players, to iterate over
        for (int i = 0; i < numPlayers; i++) 
        {
            Player player = playerController.registerPlayer();
            game.getPlayers().add(player); //Adds players to the ArrayList in the Game Class
        }
        
        //Calls playerView's showRegisteredPlayers method to display the players
        playerView.showRegisteredPlayers(game); 
    }
}

