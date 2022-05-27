package winslow_assignment5;

import java.util.Scanner;

public class Challenge9_1 extends Challenge {
    public Challenge9_1() {
        super("Backwards String");
    }
    
    /*
    Write a method that accepts a String object as an argument and displays its
    contents backward. For instance, if the string argument is "gravity" the
    method should display -"ytivarg". Demonstrate the method in a program that
    asks the user to input a string and then passes it to the method.
    */
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter string: ");
        String str = in.nextLine();
        
        System.out.println(reverseString(str));
    }
    
    private String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(s);
        sb.reverse();
        
        return sb.toString();
    }
}
