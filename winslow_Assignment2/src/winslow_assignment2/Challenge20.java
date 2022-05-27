package winslow_assignment2;

import java.util.Scanner;

/*
20. Planting Grapevines

A vineyard owner is planting several new rows of grapevines, and needs to know
how many grapevines to plant in each row. She has determined that after
measuring the length of a future row, she can use the following formula to
calculate the number of vines that will fit in the row, along with the trellis
end-post assemblies that will need to be constructed at each end of the row:

V = (R – 2E) / S

The terms in the formula are:

V is the number of grapevines that will fit in the row.
R is the length of the row, in feet.
E is the amount of space, in feet, used by an end-post assembly.
S is the space between vines, in feet.

Write a program that makes the calculation for the vineyard owner. The program
should ask the user to input the following:

* The length of the row, in feet
* The amount of space used by an end-post assembly, in feet
* The amount of space between the vines, in feet

Once the input data has been entered, the program should calculate and display
the number of grapevines that will fit in the row.
*/

public class Challenge20 extends Challenge {
    final double tolerance = 0.15; // tolerance for approximate grapevines
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter length of row (in feet): ");
        double rowLength = in.nextDouble();
        
        System.out.print("Enter space used by end-post assembly (in feet): ");
        double endPostSpace = in.nextDouble();
        
        System.out.print("Enter space between vines (in feet): ");
        double vineSpace = in.nextDouble();
        
        // operator precedence FTW
        double numberOfGrapevines = (rowLength - 2 * endPostSpace) / vineSpace;
        
        // Grapevines are non-negative integers, not doubles.
        // So we have to figure out how to tell the user the result,
        // but in an "integer-y" sort of way.
        
        double roundedGrapevines = Math.rint(numberOfGrapevines);
        String correctness;
        // Grapevines are exactly an integer
        if (numberOfGrapevines == roundedGrapevines) {
            correctness = "exactly";
        // Grapevines are more than an integer
        } else if (numberOfGrapevines > roundedGrapevines) {
            if (Math.abs(numberOfGrapevines - roundedGrapevines) < tolerance) {
                // Slightly more
                correctness = "just about";
            } else {
                // More than slightly more
                correctness = "just over";
            }
        // Grapevines are less than an integer
        } else {
            if (Math.abs(numberOfGrapevines - roundedGrapevines) < tolerance) {
                // Slightly less
                correctness = "almost";
            } else {
                // More than slightly less
                correctness = "just under";
            }
        }
        
        // Looks nice, don't you think?
        System.out.format(
                "You will be able to fit %s %d grapevines.%n",
                correctness, (int)roundedGrapevines
        );
    }
}
