import java.util.Scanner;

public class UserInput {

    /**
     * Prompts the user until they enter 1 (start) or 2 (exit).
     * @return true if user chose 1 (start), false if user chose 2 (exit)
     */
    public static boolean promptStartOrExit(Scanner sc) {
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Start Game");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice == 1) {
                    return true;   // start
                } else if (choice == 2) {
                    return false;  // exit
                } else {
                    System.out.println("Wrong choice. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }
    }

    /**
     * Prompts the user until they enter a valid index in [0..7].
     * @return the valid index chosen by the user
     */
    public static int promptIndex0to7(Scanner sc) {
        while (true) {
            System.out.print("Enter an index (0–7): ");
            String input = sc.nextLine().trim();
            try {
                int idx = Integer.parseInt(input);
                if (idx >= 0 && idx <= 7) {
                    return idx;
                }
                System.out.println("Invalid index. Please enter a number between 0 and 7.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 7.");
            }
        }
    }
}
