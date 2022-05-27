package winslow_assignment2;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Winslow_Assignment2 {

    public static void main(String[] args) {
        // This needs to be a list of Integers
        // Otherwise, it's slightly harder to check membership
        List<Integer> chalIndices = Arrays.asList(5, 7, 9, 12, 20, 21);
        // This is a parallel list of Challenges
        // We will run their execute() methods once we choose one
        List<Challenge> chalClasses = Arrays.asList(
                new Challenge5(),
                new Challenge7(),
                new Challenge9(),
                new Challenge12(),
                new Challenge20(),
                new Challenge21()
        );
        
        System.out.println("5. Sales Prediction");
        System.out.println("7. Sales Tax");
        System.out.println("9. Miles-per-Gallon");
        System.out.println("12. String Manipulator");
        System.out.println("20. Planting Grapevines");
        System.out.println("21. Compound Interest");
        
        int chal = inputChoiceInt(chalIndices);
        
        int chalI = chalIndices.indexOf(chal);
        // This is where the magic happens
        chalClasses.get(chalI).execute();
    }
    
    public static int inputChoiceInt(List<Integer> choices) {
        Scanner in = new Scanner(System.in);
        
        int choice;
        do {
            // Ask for a choice (or at least try)
            System.out.print("Enter choice: ");
            try {
                choice = in.nextInt();

                // If our choice was in the list of choices
                if (choices.contains(choice)) {
                    // Return the choice value (and escape the loop)
                    return choice;
                } else {
                    // Cry
                    System.out.println("Invalid choice!");
                }
            // Uh-oh, our "int" was not an int!
            } catch (InputMismatchException e) {
                // Advance the scanner so it doesn't get stuck
                in.next();
                // Cry (but in French)
                System.out.println("Not a number!");
            }
        // Repeat forever, until we make a valid choice
        // (which will happen somewhere in the loop, I promise)
        } while (true);
    }
}
