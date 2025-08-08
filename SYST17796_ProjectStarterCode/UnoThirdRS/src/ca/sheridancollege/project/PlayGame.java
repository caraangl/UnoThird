package ca.sheridancollege.project;

import java.util.Scanner;

/**
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
        System.out.println("Type '1' or '2': ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                Game game = new Game();
                Player player1 = Player.registerPlayer();
                Player player2 = Player.registerPlayer();
                game.getPlayers().add(player1);
                game.getPlayers().add(player2);
                game.playUno(); // Currently this just prints a list of players
                GameStart gameStart = new GameStart();
                gameStart.generateDeck();
                System.out.println("Handing Out Cards...");
                gameStart.generatePlayerHand(player1);
                gameStart.generatePlayerHand(player1);
                System.out.println("Player One Hand:");
                player1.printHand();
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
