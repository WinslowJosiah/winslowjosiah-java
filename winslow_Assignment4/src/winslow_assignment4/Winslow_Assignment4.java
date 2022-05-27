package winslow_assignment4;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Winslow_Assignment4 {

    public static void main(String[] args) {
        List<Integer> chapterIndices = Arrays.asList(5, 6);
        List<Chapter> chapterClasses = Arrays.asList(
                new Chapter5(),
                new Chapter6()
        );
        
        for (int i = 0; i < chapterIndices.size(); i++ ) {
            Integer j = chapterIndices.get(i);
            Chapter c = chapterClasses.get(i);
            System.out.printf("Chapter %d: %s\n", j, c.getName());
        }
        
        int chap = inputChoiceInt(
                chapterIndices, "Enter chapter: ", "Invalid chapter!"
        );
        int chapI = chapterIndices.indexOf(chap);
        Chapter chapClass = chapterClasses.get(chapI);
        
        List<Integer> chalIndices = chapClass.getChalIndices();
        List<Challenge> chalClasses = chapClass.getChallenges();
        
        for (int i = 0; i < chalIndices.size(); i++ ) {
            Integer j = chalIndices.get(i);
            Challenge c = chalClasses.get(i);
            System.out.printf("%d. %s\n", j, c.getName());
        }
                
        int chal = inputChoiceInt(
                chalIndices, "Enter challenge: ", "Invalid challenge!"
        );
        int chalI = chalIndices.indexOf(chal);
        // This is where the magic happens
        chalClasses.get(chalI).execute();
    }
    
    // I've implemented this before
    public static int inputChoiceInt(
            List<Integer> choices, String prompt, String error
    ) {
        Scanner in = new Scanner(System.in);
        
        int choice;
        do {
            System.out.print(prompt);
            try {
                choice = in.nextInt();

                if (choices.contains(choice)) {
                    return choice;
                }
                
                System.out.println(error);
            } catch (InputMismatchException e) {
                // Advance the scanner so it doesn't get stuck
                in.next();
                System.out.println(error);
            }
        } while (true);
    }
    
    // Because of type erasure, I have to implement this AGAIN for strings,
    // and with a different name than my other function
    public static String inputChoiceString(
            List<String> choices, String prompt, String error
    ) {
        Scanner in = new Scanner(System.in);
        
        String choice;
        do {
            System.out.print(prompt);
            choice = in.nextLine();

            if (choices.contains(choice)) {
                return choice;
            }
            
            System.out.println(error);
        } while (true);
    }
    
    // And again for chars (but with a String, not a List<Character>)
    public static char inputChoiceChar(
            String choices, String prompt, String error,
            boolean caseSensitive
    ) {
        Scanner in = new Scanner(System.in);
        
        // For case-sensitivity reasons
        if (!caseSensitive) choices = choices.toLowerCase();
        
        String choice;
        do {
            System.out.print(prompt);
            // There is no nextChar(), which saddens me.
            // Also, we have to convert the character to a string
            // to make contains() work right with everything.
            choice = Character.toString(in.next().charAt(0));
            
            // For case-sensitivity reasons
            if (!caseSensitive) choice = choice.toLowerCase();
            
            if (choices.contains(choice)) {
                return choice.charAt(0); // back to char
            }
            
            System.out.println(error);
        } while (true);
    }
    
    // And just in case I don't wanna use the caseSensitive argument
    public static char inputChoiceChar(
            String choices, String prompt, String error
    ) {
        return inputChoiceChar(choices, prompt, error, false);
    }
}
