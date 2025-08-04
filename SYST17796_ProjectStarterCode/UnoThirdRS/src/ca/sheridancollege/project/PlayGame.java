/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.Scanner;

/**
 *
 * @author Ryleigh Smith August 2025
 */
public class PlayGame {
    
    public static void main(String[] args) {
        gameStart();
    }
    
    // Method to start the game, lets user choose whether to play or exit.
    public static void gameStart() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to UNO! \nPlease select an option:");
        System.out.println("1. Play UNO!");
        System.out.println("2. Exit UNO!");
        System.out.println("Type '1' or '2'.");
        
        int choice = input.nextInt();
        
        switch (choice){
            case 1:
                // register players and start game
                break;
            case 2:
                System.out.println("Thanks for playing. \nGoodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Selection.");
                gameStart();
        }
    }
}


