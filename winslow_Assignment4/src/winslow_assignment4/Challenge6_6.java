package winslow_assignment4;

import java.util.Scanner;

public class Challenge6_6 extends Challenge {
    public Challenge6_6() {
        super("TestScores Class");
    }
    
    /*
    Design a TestScores class that has fields to hold three test scores. The
    class should have a constructor, accessor and mutator methods for the test
    score fields, and a method that returns the average of the test scores.
    Demonstrate the class by writing a separate program that creates an instance
    of the class. The program should ask the user to enter three test scores,
    which are stored in the TestScores object. Then the program should display
    the average of the scores, as reported by the TestScores object.
    */
    
    private final int numScores = 3;
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        TestScores ts = new TestScores();
        
        double nextScore;
        for (int i = 1; i <= numScores; i++) {
            System.out.printf("Enter score %d: ", i);
            nextScore = in.nextDouble();
            
            ts.addScore(nextScore);
        }
        
        System.out.printf("Average of test scores: %f\n", ts.getAverage());
    }
}
