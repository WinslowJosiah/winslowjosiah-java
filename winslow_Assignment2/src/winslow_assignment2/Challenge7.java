package winslow_assignment2;

import java.util.Scanner;

/*
7. Sales Tax

Write a program that will ask the user to enter the amount of a purchase. The
program should then compute the state and county sales tax. Assume the state
sales tax is 4 percent and the county sales tax is 2 percent. The program should
display the amount of the purchase, the state sales tax, the county sales tax,
the total sales tax, and the total of the sale (which is the sum of the amount
of purchase plus the total sales tax).

Hint: use the value 0.02 to represent 2 percent, and 0.04 to represent 4
percent.
*/

public class Challenge7 extends Challenge {
    final double stateSalesTaxPct = 0.04; // 4%
    final double countySalesTaxPct = 0.02; // 2%
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter amount of purchase: $");
        double purchaseAmt = in.nextDouble();
        
        System.out.format("Purchase amount:\t$%.2f%n", purchaseAmt);
        
        double stateSalesTaxAmt = purchaseAmt * stateSalesTaxPct;
        System.out.format("State sales tax:\t$%.2f%n", stateSalesTaxAmt);
        
        double countySalesTaxAmt = purchaseAmt * countySalesTaxPct;
        System.out.format("County sales tax:\t$%.2f%n", countySalesTaxAmt);
        
        double saleTotal = purchaseAmt + stateSalesTaxAmt + countySalesTaxAmt;
        System.out.format("Sale total:\t$%.2f%n", saleTotal);
    }
}
