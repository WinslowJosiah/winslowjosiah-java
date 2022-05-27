package winslow_assignment5;

import java.util.Scanner;

public class Challenge9_9 extends Challenge {
    public Challenge9_9() {
        super("Sum of Digits in a String");
    }
    
    /*
    Write a program that asks the user to enter a series of single digit numbers
    with nothing separating them. The program should display the sum of all the
    single digit numbers in the string. For example, if the user enters 2514,
    the method should return 12, which is the sum of 2, 5, 1, and 4. The program
    should also display the highest and lowest digits in the string.
    (Hint: Convert the string to an array.)
    */
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter a series of digits: ");
        String digits = in.nextLine();
        
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            sum += digits.charAt(i) - '0'; // Chars are integers in disguise!
        }
        
        System.out.printf("Sum of digits: %d\n", sum);
    }
}
