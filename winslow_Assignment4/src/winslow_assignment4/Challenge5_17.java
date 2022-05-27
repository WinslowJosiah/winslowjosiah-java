package winslow_assignment4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static winslow_assignment4.Winslow_Assignment4.inputChoiceChar;

public class Challenge5_17 extends Challenge {
    private final LinkedHashMap<String, String[]> games;
    
    public Challenge5_17() {
        super("Rock, Paper, Scissors Game");
        
        // I would initialize this outside of the constructor,
        // but the ordering wouldn't be consistent otherwise
        this.games = new LinkedHashMap();
        this.games.put("Rock Paper Scissors", new String[] {
            "Rock", "Paper", "Scissors"
        });
        this.games.put("Rock Paper Scissors Lizard Spock", new String[] {
            "Rock", "Paper", "Scissors", "Spock", "Lizard"
        });
        this.games.put("RPS-7", new String[] {
            "Rock", "Paper", "Fire", "Air", "Scissors", "Water", "Sponge"
        });
        this.games.put("RPS-9", new String[] {
            "Rock", "Paper", "Fire", "Air", "Scissors", "Water", "Human", "Gun",
            "Sponge"
        });
        this.games.put("RPS-11", new String[] {
            "Rock", "Paper", "Fire", "Air", "Scissors", "Water", "Human",
            "Devil", "Wolf", "Gun", "Sponge"
        });
        this.games.put("RPS-15", new String[] {
            "Rock", "Paper", "Fire", "Air", "Scissors", "Water", "Snake",
            "Dragon", "Human", "Devil", "Tree", "Lightning", "Wolf", "Gun",
            "Sponge"
        });
        this.games.put("RPS-25", new String[] {
            "Rock", "Paper", "Sun", "Moon", "Fire", "Air", "Scissors", "Bowl",
            "Axe", "Water", "Snake", "Alien", "Monkey", "Dragon", "Woman",
            "Devil", "Man", "Lightning", "Tree", "Nuke", "Cockroach",
            "Dynamite", "Wolf", "Gun", "Sponge"
        });
        this.games.put("RPS-101", new String[] {
            "Rock", "Paper", "Death", "Cloud", "Wall", "Airplane", "Sun",
            "Moon", "Camera", "Grass", "Fire", "Film", "Chainsaw", "Toilet",
            "School", "Air", "Scissors", "Planet", "Poison", "Guitar", "Cage",
            "Bowl", "Axe", "Cup", "Peace", "Beer", "Computer", "Rain", "Castle",
            "Water", "Snake", "TV", "Blood", "Rainbow", "Porcupine", "UFO",
            "Vulture", "Alien", "Monkey", "Prayer", "King", "Mountain", "Queen",
            "Satan", "Prince", "Dragon", "Princess", "Diamond", "Police",
            "Platinum", "Woman", "Gold", "Baby", "Devil", "Man", "Fence",
            "Home", "Video Game", "Train", "Math", "Car", "Robot", "Noise",
            "Heart", "Bicycle", "Electricity", "Tree", "Lightning", "Turnip",
            "Medusa", "Duck", "Power", "Wolf", "Laser", "Cat", "Nuke", "Bird",
            "Sky", "Fish", "Tank", "Spider", "Helicopter", "Cockroach",
            "Dynamite", "Brain", "Tornado", "Community", "Quicksand", "Cross",
            "Pit", "Money", "Chain", "Vampire", "Gun", "Sponge", "Law",
            "Church", "Whip", "Butter", "Sword", "Book"
        });
    }
    
    /*
    Write a program that lets the user play the game of Rock, Paper, Scissors
    against the computer. The program should work as follows.
    1. When the program begins, a random number in the range of 1 through 3 is
    generated. If the number is 1, then the computer has chosen rock. If the
    number is 2, then the computer has chosen paper. If the number is 3, then
    the computer has chosen scissors. (Don’t display the computer’s choice yet.)
    2. The user enters his or her choice of "rock", "paper", or "scissors" at
    the keyboard. (You can use a menu if you prefer.)
    3. The computer's choice is displayed.
    4. A winner is selected according to the following rules:
     * If one player chooses rock and the other player chooses scissors, then
    rock wins. (The rock smashes the scissors.)
     * If one player chooses scissors and the other player chooses paper, then
    scissors wins. (Scissors cuts paper.)
     * If one player chooses paper and the other player chooses rock, then paper
    wins. (Paper wraps rock.)
     * If both players make the same choice, the game must be played again to
    determine the winner.
    Be sure to divide the program into methods that perform each major task.
    */
    
    private enum GameOutcome {
        TIE, P1_WINS, P2_WINS
    }
    
    private final int gesturesRowLen = 7;
    
    public void execute() {
        Random rand = new Random();
        
        String rpsGame = askForGame(this.games);
        System.out.println("");
        printRules(this.games.get(rpsGame));
        System.out.println("");
        
        String[] gestures = this.games.get(rpsGame);
        
        char playAgain;
        do {
            // It's important that the computer make the choice first,
            // because otherwise, we could accuse it of cheating!
            int cpuChoice = rand.nextInt(gestures.length);
            int humanChoice = askForGesture(gestures);

            // Display choices
            System.out.printf("You chose %s.\n", gestures[humanChoice]);
            System.out.printf("The computer chose %s.\n", gestures[cpuChoice]);

            // Figure out who the winner is
            GameOutcome winner = getWinner(gestures, humanChoice, cpuChoice);

            // Display a message based on who won
            switch (winner) {
                case TIE:
                    System.out.println("Tie game.");
                    break;
                case P1_WINS:
                    System.out.println("You win!");
                    break;
                case P2_WINS:
                    System.out.println("You lose...");
                    break;
                default:
                    System.out.println("I don't know what happened...");
                    break;
            }
            
            // Ask to play again
            playAgain = inputChoiceChar(
                    "yn", "Do you want to play again? ", "Enter Y or N."
            );
        } while (playAgain == 'y');
    }
    
    // Decide who wins the RPS-like game, given the gestures and moves
    private GameOutcome getWinner(String[] gestures, int p1, int p2) {
        // If both moves are the same, it's a tie game
        if (p1 == p2) return GameOutcome.TIE;
        
        // Get the distance between P1 and P2's gestures
        int diff = (p2 - p1 + gestures.length) % gestures.length;
        
        if (diff % 2 == 0) {
            // If diff is even, P1 wins
            return GameOutcome.P1_WINS;
        } else {
            // If diff is odd, P2 wins
            return GameOutcome.P2_WINS;
        }
    }
    
    // Ask the user for the (1-based) game they want to play
    private String askForGame(LinkedHashMap<String, String[]> g) {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Games available to play:");
        
        // Display each game with a numerical index
        int i = 1;
        for (String game : g.keySet()) {
            System.out.printf("%d. %s\n", i, game);
            i++;
        }
        
        int gameI;
        do {
            System.out.printf("Enter game (1-%d): ", g.size());
            
            try {
                gameI = in.nextInt();
                
                // Validate bounds of input
                if (gameI >= 1 && gameI <= games.size()) break;
                
                System.out.printf(
                        "Please enter a number from 1 to %d.\n",
                        g.size()
                );
            // If our input was not an integer
            } catch (InputMismatchException e) {
                in.next(); // Advance the scanner so it doesn't get stuck
                System.out.println("Please enter a valid number.");
            }
        } while (true);
        
        // Get the right index into the keys
        // (we subtract 1 because arrays are 0-based, not 1-based)
        return (String)g.keySet().toArray()[gameI - 1];
    }
    
    // Ask for a gesture
    private int askForGesture(String[] gestures) {
        Scanner in = new Scanner(System.in);
        
        printGesturesWithIndices(gestures);
        
        int gest;
        do {
            System.out.printf("Enter move (1-%d): ", gestures.length);
            
            try {
                gest = in.nextInt();
                
                // Validate bounds of input
                if (gest >= 1 && gest <= gestures.length) break;
                
                System.out.printf(
                        "Please enter a number from 1 to %d.\n",
                        gestures.length
                );
            // If our input was not an integer
            } catch (InputMismatchException e) {
                in.next(); // Advance the scanner so it doesn't get stuck
                System.out.println("Please enter a valid number.");
            }
        } while (true);
        
        // (we subtract 1 because arrays are 0-based, not 1-based)
        return gest - 1;
    }
    
    // Print the complete rules of the game with these gestures
    private void printRules(String[] gestures) {
        System.out.println("Rules:");
        for (int i = 0; i < gestures.length; i++) {
            String gesture = gestures[i];
            
            List<String> beats = new ArrayList();
            for (int j = 2; j < gestures.length; j += 2) {
                beats.add(gestures[(i + j) % gestures.length]);
            }
            // If we have more than 1 gesture in the list
            if (beats.size() > 1) {
                // Remove the last gesture...
                String lastGesture = beats.remove(beats.size() - 1);
                // ...and combine it with the second-to-last gesture
                // with the word "and"
                beats.set(
                        beats.size() - 1,
                        beats.get(beats.size() - 1) + " and " + lastGesture
                );
            }
            
            // Print out the list of gestures that this gesture beats
            System.out.printf("%s beats %s.\n",
                    gesture, String.join(", ", beats)
            );
        }
    }
    
    // Print out a nicely-formatted list of all the gestures
    private void printGesturesWithIndices(String[] gestures) {
        String gestureFormat = "%d. %s";
        
        // Get the maximum length of a formatted string
        int maxLen = 0;
        for (int i = 0; i < gestures.length; i++) {
            String s = String.format(gestureFormat,
                    i + 1, gestures[i]
            );
            if (s.length() > maxLen) maxLen = s.length();
        }
        
        String currentOption;
        // For each row of gestures
        for (int i = 0; i < gestures.length; i += gesturesRowLen) {
            // For each gesture in this row
            for (int j = 0; j < gesturesRowLen; j++) {
                // If past the end of the gestures, break out
                if (i + j >= gestures.length) break;
                
                // Get the formatted string
                currentOption = String.format(gestureFormat,
                        i + j + 1, gestures[i + j]
                );
                
                // If not at the end of this row
                if (j + 1 < Math.min(gestures.length - i, gesturesRowLen)) {
                    // Right-pad with spaces, so the string is maxLen long
                    // (also, add a tab)
                    currentOption = String.format(
                            "%-" + maxLen + "s\t", 
                            currentOption
                    );
                }
                
                // Print the option
                System.out.print(currentOption);
            }
            System.out.println(""); // newline
        }
    }
}
