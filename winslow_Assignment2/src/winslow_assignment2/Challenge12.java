package winslow_assignment2;

import java.util.Scanner;

/*
12. String Manipulator

Write a program that asks the user to enter the name of his or her favorite
city. Use a String variable to store the input. The program should display the
following:

* The number of characters in the city name
* The name of the city in all uppercase letters
* The name of the city in all lowercase letters
* The first character in the name of the city
*/

public class Challenge12 extends Challenge {
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter your favorite city: ");
        String city = in.nextLine();
        
        System.out.format("# of chars:\t%d%n", city.length());
        System.out.format("All-uppercase:\t%s%n", city.toUpperCase());
        System.out.format("All-lowercase:\t%s%n", city.toLowerCase());
        System.out.format("First char:\t%s%n", city.charAt(0));
    }
}
