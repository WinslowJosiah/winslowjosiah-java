package winslow_assignment2;

/*
5. Sales Prediction

The East Coast sales division of a company generates 62 percent of total sales.
Based on that percentage, write a program that will predict how much the East
Coast division will generate if the company has $4.6 million in sales this year.

Hint: Use the value 0.62 to represent 62 percent.
*/

public class Challenge5 extends Challenge {
    final double totalSalesPercent = 0.62; // 62%
    final double totalSales = 4600000; // $4.6 million
    
    public void execute() {
        System.out.format(
                "The East Coast division will generate $%.2f in sales.%n",
                totalSales * totalSalesPercent
        );
    }
}
