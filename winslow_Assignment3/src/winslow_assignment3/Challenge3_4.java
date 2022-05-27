package winslow_assignment3;

import java.util.Scanner;

public class Challenge3_4 extends Challenge {
    public Challenge3_4() {
        super("Test Scores and Grade");
    }
    
    /*
    Write a program that has variables to hold three test scores. The program
    should ask the user to enter three test scores and then assign the values
    entered to the variables. The program should display the average of the test
    scores and the letter grade that is assigned for the test score average.
    
    Use the grading scheme in the following table:
    
      Test Score Average  |  Letter Grade
    ----------------------+----------------
            90-100        |        A
             80-89        |        B
             70-79        |        C
             60-69        |        D
           Below 60       |        F
    */
    
    private final char[] letterGrades = {'A', 'B', 'C', 'D', 'F'};
    private final double[] minScores = {90, 80, 70, 60, 0};
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter score 1: ");
        double score1 = in.nextDouble();
        System.out.print("Enter score 2: ");
        double score2 = in.nextDouble();
        System.out.print("Enter score 3: ");
        double score3 = in.nextDouble();
        
        // I'd have much rather put these scores in some list or array
        double avgScore = (score1 + score2 + score3) / 3.0;
        
        // A switch statement is probably what the book intends,
        // but I'm doing this looping method instead
        char avgScoreLetter = '?'; // just in case
        for (int i = 0; i < letterGrades.length; i++) {
            if (avgScore >= minScores[i]) {
                avgScoreLetter = letterGrades[i];
                break;
            }
        }
        
        System.out.printf("Average score: %f\n", avgScore);
        System.out.printf("Letter grade: %s\n", avgScoreLetter);
    }
}
