package winslow_assignment4;

import java.util.Scanner;

public class Challenge5_10 extends Challenge {
    public Challenge5_10() {
        super("Stock Profit");
    }
    
    /*
    The profit from the sale of a stock can be calculated as follows:
    
        Profit = ((NS * SP) - SC) - ((NS * PP) + PC)
    
    where NS is the number of shares, PP is the purchase price per share, PC is
    the purchase commission paid, SP is the sale price per share, and SC is the
    sale commission paid. If the calculation yields a positive value, then the
    sale of the stock resulted in a profit. If the calculation yields a negative
    number, then the sale resulted in a loss.
    
    Write a method that accepts as arguments the number of shares, the purchase
    price per share, the purchase commission paid, the sale price per share, and
    the sale commission paid. The method should return the profit (or loss) from
    the sale of stock. Demonstrate the method in a program that asks the user to
    enter the necessary data and displays the amount of the profit or loss.
    */
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter number of shares: ");
        int numShares = in.nextInt();
        
        System.out.print("Enter purchase price per share: $");
        double purchasePrice = in.nextDouble();
        
        System.out.print("Enter purchase commission paid: $");
        double purchaseCommission = in.nextDouble();
        
        System.out.print("Enter sale price per share: $");
        double salePrice = in.nextDouble();
        
        System.out.print("Enter sale commission paid: $");
        double saleCommission = in.nextDouble();
        
        double profit = getProfit(
                numShares, purchasePrice, purchaseCommission,
                salePrice, saleCommission
        );
        
        if (profit == 0) {
            System.out.println("You have no profit or loss.");
        } else {
            System.out.printf("You have a $%.2f %s.\n", profit,
                    profit > 0 ? "profit" : "loss"
            );
        }
    }
    
    public static double getProfit(
            int numShares, double purchasePrice, double purchaseCommission,
            double salePrice, double saleCommission
    ) {
        double saleFactor = numShares * salePrice - saleCommission;
        double purchaseFactor = numShares * purchasePrice + purchaseCommission;
        
        return saleFactor - purchaseFactor;
    }
}
