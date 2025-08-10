/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 * StartView displays all startup messages as well as sends the user inputs for
 * Application Startup
 *
 * @author Ryleigh Smith August 2025, Some comments added by Lian Asher Caraang
 */
public class StartView {

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    public StartView() {
    }

    //showWelcomeMessage Method - Displays welcome message prompts the user for an action
    public void showWelcomeMessage() {
        System.out.println("Welcome to UNO! \nPlease select an option:");
        System.out.println("1. Play UNO!");
        System.out.println("2. Exit UNO!");
        System.out.println("Type '1' or '2': ");
    }

    //showExitMessage() Method - Displays exit message
    public void showExitMessage() {
        System.out.println("Thanks for playing. \nGoodbye!");
    }

    //showInvalidMessage() Method - Displays invalid selection message
    public void showInvalidMessage() {
        System.out.println("Invalid Selection.");
    }
}
