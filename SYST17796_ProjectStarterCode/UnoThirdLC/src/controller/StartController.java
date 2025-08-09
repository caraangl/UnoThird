/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

//Imports
import model.Player;
import model.Game;
import view.StartView;
import java.util.Scanner;
import view.PlayerView;

/**
 * StartController controls the application startup, and gives the user a choice to play or exit 
 * @author Lian Asher Caraang
 */
public class StartController {
    
    //View Variables
    private StartView startView;
    private PlayerView playerView;
    
    //Controller Variables
    private PlayerController playerController;
    private GameController gameController;
    
    //Class Variables
    private Game unoGame;
    
    //numPlayers Variable for how many players will be playing
    int numPlayers = 2;
    
    //StartController Class Constructor
    public StartController() 
    {
    }
    
    //startApp Method to call the Initial Startup Messages
    public void startApp()
    {
        //startView View variable
        startView = new StartView();
        
        //Call startView's showWelcomeMessage method 
        startView.showWelcomeMessage();
        
        //Input Variable
        Scanner input = new Scanner(System.in);
        
        //startChoice Input Variable
        int startChoice = input.nextInt();
        input.nextLine(); 
        
        //If user chooses option 1, game will start, otherwise option 2 will exit the application. 
        switch (startChoice)
        {
            case 1:
                //unoGame Variable to create a new Game Instance
                unoGame= new Game();
                
                //playerView Variable to create a new PlayerView View
                playerView = new PlayerView(input);
                
                //playerController Variable to create a new PlayerController Controller to Register Players
                playerController = new PlayerController(playerView);
                
                //gameController Variable to create a new GameController to handle gameplay
                gameController = new GameController();
                
                //Calls gameController's startGame method with the number of players that are playing 
                gameController.startGame(numPlayers);
                break;
                
            case 2:
                //Call startView's showExitMessage() Method when user selects option 2
                startView.showExitMessage();
                System.exit(0); //Shut down the application
                break;
                
            default:
                //If none of the options are selected, call the startView's showInvalidMessage() method to display an error. 
                startView.showInvalidMessage();
                startApp();  //Run the startApp method again after the invalid message
        }
    }
}

