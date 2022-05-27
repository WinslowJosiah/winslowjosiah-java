package winslow_assignment3;

import java.util.Scanner;

public class Challenge4_8 extends Challenge {
    public Challenge4_8() {
        super("Average Rainfall");
    }
    
    /*
    Write a program that uses nested loops to collect data and calculate the
    average rainfall over a period of years. The program should first ask for
    the number of years. The outer loop will iterate once for each year. The
    inner loop will iterate twelve times, once for each month. Each iteration
    of the inner loop will ask the user for the inches of rainfall for that
    month. After all iterations, the program should display the number of
    months, the total inches of rainfall, and the average rainfall per month
    for the entire period.
    
    Input Validation: Do not accept a number less than 1 for the number of
    years. Do not accept negative numbers for the monthly rainfall.
    */
    
    private final String[] monthNames = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        // Ask for number of years
        int years;
        do {
            System.out.print("Enter number of years: ");
            years = in.nextInt();
            
            // Input validation: positive integer
            if (years < 1) {
                System.out.println("Enter a positive number of years.");
            } else {
                break;
            }
        } while (true);
        
        double totalRainfall = 0;
        double currentRainfall;
        // Loop over each year
        for (int i = 1; i <= years; i++) {
            // Loop over each month
            for (String m : monthNames) {
                // Ask for this month's rainfall
                do {
                    System.out.printf(
                            "Enter inches of rainfall for %s of year %d: ", m, i
                    );
                    currentRainfall = in.nextDouble();

                    // Input validation:
                    if (currentRainfall < 0) {
                        System.out.println(
                                "Enter a non-negative rainfall amount."
                        );
                    } else {
                        break;
                    }
                } while (true);
                
                // Add to total rainfall
                totalRainfall += currentRainfall;
            }
        }
        
        int numOfMonths = years * monthNames.length;
        System.out.printf("Months:\t%d\n", numOfMonths);
        System.out.printf("Total rainfall:\t%f inches\n", totalRainfall);
        System.out.printf("Average rainfall:\t%f inches/month\n",
                totalRainfall / (double)(numOfMonths) // gotta cast to double
        );
    }
}
