package winslow_assignment2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

/*
9. Miles-per-Gallon

A car's miles-per-gallon (MPG) can be calculated with the following formula:

MPG = Miles driven/Gallons of gas used

Write a program that asks the user for the number of miles driven and the
gallons of gas used. It should calculate the car's miles-per-gallon and display
the result on the screen.
*/

public class Challenge9 extends Challenge {
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter miles driven: ");
        double milesDriven = in.nextDouble();
        
        System.out.print("Enter gallons of gas used: ");
        double gallonsOfGasUsed = in.nextDouble();
        
        double milesPerGallon = milesDriven / gallonsOfGasUsed;
        
        // I don't like how %f displays the number, so this is my solution
        DecimalFormat df = new DecimalFormat(
                "0",
                DecimalFormatSymbols.getInstance(Locale.ENGLISH)
        );
        df.setMaximumFractionDigits(340); // this is the maximum of this value
        // Looks nice now
        System.out.format("Gas mileage: %s MPG%n", df.format(milesPerGallon));
    }
}
