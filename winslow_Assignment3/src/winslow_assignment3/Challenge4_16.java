package winslow_assignment3;

import java.util.Scanner;

public class Challenge4_16 extends Challenge {
    public Challenge4_16() {
        super("Budget Analysis");
    }
    
    /*
    Write a program that asks the user to enter the amount that he or she has
    budgeted for a month. A loop should then prompt the user to enter each of
    his or her expenses for the month, and keep a running total. When the loop
    finishes, the program should display the amount that the user is over or
    under budget.
    */
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        // Ask for budget
        System.out.print("Enter budget for month: $");
        double budget = in.nextDouble();
        
        double expenses = 0;
        double currentExpense;
        // Ask for expenses
        do {
            System.out.print("Enter expense (enter 0 if done): $");
            currentExpense = in.nextDouble();
            
            // If 0, end loop
            if (currentExpense == 0) {
                break;
            }
            
            // Add the new expense
            expenses += currentExpense;
        } while (true);
        
        if (budget == expenses) {
            System.out.println("You are exactly on budget.");
        } else {
            System.out.printf(
                    "You are $%.2f %s budget.\n.",
                    Math.abs(budget - expenses),
                    expenses < budget ? "under" : "over"
            );
        }
    }
}
