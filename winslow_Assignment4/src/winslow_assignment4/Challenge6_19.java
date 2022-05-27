package winslow_assignment4;

import java.util.Scanner;
import static winslow_assignment4.Winslow_Assignment4.inputChoiceChar;

public class Challenge6_19 extends Challenge {
    public Challenge6_19() {
        super("Fishing Game Simulation");
    }
    
    /*
    For this assignment, you will write a program that simulates a fishing game.
    In this game, a six-sided die is rolled to determine what the user has
    caught. Each possible item is worth a certain number of fishing points.
    The points will remain hidden until the user is finished fishing, and then a
    message is displayed congratulating the user, depending on the number of
    fishing points gained.
    
    Here are some suggestions for the game's design:
    
    * Each round of the game is performed as an iteration of a loop that repeats
    as long as the player wants to fish for more items.
    * At the beginning of each round, the program will ask the user whether or
    not he or she wants to continue fishing.
    *  The program simulates the rolling of a six-sided die (use the Die class
    that was shown in Code Listing 6-14).
    * Each item that can be caught is represented by a number generated from the
    die; for example, 1 for "a huge fish", 2 for "an old shoe", 3 for "a little
    fish", and so on.
    * Each item the user catches is worth a different amount of points.
    * The loop keeps a running total of the user's fishing points.
    * After the loop has finished, the total number of fishing points is
    displayed, along with a message that varies depending on the number of
    points earned.
    */
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        Die fishDie = new Die(6); // "fishDie" is an unfortunate name...
        int fishPoints = 0;
        
        char keepFishing = inputChoiceChar(
                "yn", "Do you feel like fishing? (Y/N) ", "Enter Y or N."
        );
        
        while (keepFishing == 'y') {
            fishDie.roll();
            
            String fishType;
            int newPoints;
            switch (fishDie.getValue()) {
                case 1:
                    fishType = "a huge fish";
                    newPoints = 60;
                    break;
                case 2:
                    fishType = "an old shoe";
                    newPoints = 0;
                    break;
                case 3:
                    fishType = "a little fish";
                    newPoints = 10;
                    break;
                case 4:
                    fishType = "a whale";
                    newPoints = 100;
                    break;
                case 5:
                    fishType = "a gold fish";
                    newPoints = 35;
                    break;
                case 6:
                    fishType = "a snail";
                    newPoints = 5;
                    break;
                default:
                    fishType = "something I didn't expect";
                    newPoints = 999;
                    break;
            }
            
            System.out.printf("You caught %s.\n", fishType);
            fishPoints += newPoints;
            
            keepFishing = inputChoiceChar(
                    "yn", "Do you wanna keep fishing? (Y/N) ", "Enter Y or N."
            );
        }
        
        System.out.printf("Total fishing points: %d\n", fishPoints);
        
        if (fishPoints >= 200) {
            System.out.println(
                    "You are either very experienced, or very persistent. "
                            + "Good job!"
            );
        } else {
            System.out.println("You need practice. Better luck next time!");
        }
    }
}
