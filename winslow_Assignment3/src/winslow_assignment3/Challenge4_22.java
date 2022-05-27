package winslow_assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static winslow_assignment3.Winslow_Assignment3.inputChoiceChar;

public class Challenge4_22 extends Challenge {
    public Challenge4_22() {
        super("Slot Machine Simulation");
    }
    
    /*
    A slot machine is a gambling device that the user inserts money into and
    then pulls a lever (or presses a button). The slot machine then displays a
    set of random images. If two or more of the images match, the user wins an
    amount of money that the slot machine dispenses back to the user.
    
    Create a program that simulates a slot machine. When the program runs, it
    should do the following:
    
    * Ask the user to enter the amount of money he or she wants to enter into
    the slot machine.
    * Instead of displaying images, the program will randomly select a word
    from the following list:
    Cherries, Oranges, Plums, Bells, Melons, Bars
    * To select a word, the program can generate a random number in the range of
    0 through 5. If the number is 0, the selected word is Cherries; if the
    number is 1, the selected word is Oranges; and so forth. The program should
    randomly select a word from this list three times and display all three of
    the words.
    * If none of the randomly selected words match, the program will inform the
    user that he or she has won $0. If two of the words match, the program will
    inform the user that he or she has won two times the amount entered. If
    three of the words match, the program will inform the user that he or she
    has won three times the amount entered.
    * The program will ask whether the user wants to play again. If so, these
    steps are repeated. If not, the program displays the total amount of money
    entered into the slot machine and the total amount won. Decision Structures
    
    For Extra Credit (Up to 20 points)
    
    * Assign the player $100 to start the game
    * Ensure the player does not bet more than they have
    * Decrement the bet amount from their total
    * Run the game
    * Add any winnings
    * Start again with new total
    
    */
    
    // Yes, I know some of these aren't fruits, but I like the name "fruits"
    private final String[] fruits = {
        "Cherries", "Oranges", "Plums", "Bells", "Melons", "Bars"
    };
    private final int numSymbols = 3;
    private final double startingMoney = 100;
    
    public void execute() {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        
        double totalSpent = 0;
        double netMoney = startingMoney;
        
        char playAgain;
        do {
            System.out.printf("Your money so far: $%.2f\n", netMoney);
            
            // Ask for money
            double money;
            do {
                System.out.print("How much money? $");
                money = in.nextDouble();
                
                if (money <= 0) {
                    System.out.println("Enter a positive amount of money.");
                } else if (money > netMoney) {
                    System.out.printf("You only have $%.2f.\n", netMoney);
                } else {
                    break;
                }
            } while (true);
            totalSpent += money;
            netMoney -= money;
            
            System.out.println(""); // newline
            
            // Generate random symbols (indices into "fruits")
            // while figuring out the maximum repeats
            List<String> symbols = new ArrayList<String>();
            int[] symbolCounts = new int[fruits.length];
            // This array is already all zeroes,
            // according to Section 4.12.5 of the Java specs,
            // so we don't need to uncomment this line (for now)
            //Arrays.fill(symbolCounts, 0);
            int maxCount = 0;
            for (int i = 0; i < numSymbols; i++) {
                // Generate the symbol
                int s = rand.nextInt(fruits.length);
                symbols.add(String.format("[%s]", fruits[s]));
                
                // Add to the count of that symbol
                symbolCounts[s]++;
                // Update the maximum count
                if (symbolCounts[s] > maxCount) {
                    maxCount = symbolCounts[s];
                }
            }
            
            // Print the symbols
            System.out.println(String.join(" ", symbols));
            
            System.out.println(""); // newline
            
            // Decide winnings
            double winnings; // double or nothing? haha
            switch (maxCount) {
                // No symbols matched (each element occurs 1 time)
                case 1:
                    winnings = 0;
                    System.out.println("You matched no symbols...");
                    break;
                // 2 or more symbols matched
                default:
                    winnings = maxCount * money;
                    System.out.printf("You matched %d symbols!\n", maxCount);
                    break;
            }
            System.out.printf("You have won $%.2f.\n", winnings);
            netMoney += winnings;
            
            // If we have no money
            if (netMoney <= 0) {
                System.out.println("You have lost all of your money...");
                // Quit early
                break;
            } else {
                // Otherwise, tell us our total
                System.out.printf("You now have $%.2f.\n", netMoney);
            }
            
            // I knew this function would come in handy
            playAgain = inputChoiceChar(
                    "yn",
                    "Do you want to play again? ",
                    "Enter Y or N."
            );
        } while (playAgain != 'n');
        
        System.out.printf("Total spent:\t$%.2f\n", totalSpent);
        System.out.printf("Total money:\t$%.2f\n", netMoney);
    }
}
