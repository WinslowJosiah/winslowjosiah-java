package winslow_assignment4;

import java.util.Scanner;

public class Challenge5_16 extends Challenge {
    public Challenge5_16() {
        super("Present Value");
    }
    
    /*
    Suppose you want to deposit a certain amount of money into a savings
    account, and then leave it alone to draw interest for the next 10 years. At
    the end of 10 years, you would like to have $10,000 in the account. How much
    do you need to deposit today to make that happen? You can use the following
    formula, which is known as the present value formula, to find out:
    
        P = F / ((1 + r)^n)
    
    The terms in the formula are as follows:
    
    * P is the present value, or the amount that you need to deposit today.
    * F is the future value that you want in the account. (In this case, F is
    $10,000.)
    * r is the annual interest rate.
    * n is the number of years that you plan to let the money sit in the
    account.
    
    Write a method named presentValue that performs this calculation. The method
    should accept the future value, annual interest rate, and number of years as
    arguments. It should return the present value, which is the amount that you
    need to deposit today. Demonstrate the method in a program that lets the
    user experiment with different values for the formula’s terms.
    */
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter future value: $");
        double futureValue = in.nextDouble();
        
        System.out.print("Enter annual interest rate (0%-100%): ");
        double interestRate = in.nextDouble() / 100.0;
        
        System.out.print("Enter number of years: ");
        int years = in.nextInt();
        
        System.out.printf("Present value: $%.2f\n",
                presentValue(futureValue, interestRate, years)
        );
    }
    
    private double presentValue(
            double futureValue, double interestRate, int years
    ) {
        return futureValue / (Math.pow(1 + interestRate, years));
    }
}
