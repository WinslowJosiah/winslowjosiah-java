package winslow_assignment5;

import java.util.Scanner;

public class Challenge7_8 extends Challenge {
    public Challenge7_8() {
        super("Grade Book");
    }
    
    /*
    A teacher has five students who have taken four tests. The teacher uses the
    following grading scale to assign a letter grade to a student, based on the
    average of his or her four test scores:
    
     Test Score | Letter Grade
    ------------+--------------
       90-100   |      A
        80-89   |      B
        70-79   |      C
        60-69   |      D
         0-59   |      F
    
    Write a class that uses a String array or an ArrayList object to hold the
    five students' names, an array of five characters to hold the five students'
    letter grades, and five arrays of four doubles each to hold each student's
    set of test scores. The class should have methods that return a specific
    student's name, the average test score, and a letter grade based on the
    average.
    
    Demonstrate the class in a program that allows the user to enter each
    student's name and his or her four test scores. It should then display each
    student’s average test score and letter grade.
    
    Input Validation: Do not accept test scores less than zero or greater than
    100.
    
    9. Grade Book Modification
    
    Modify the grade book application in Programming Challenge 8 so that it
    drops each student's lowest score when determining the test score averages
    and letter grades.
    */
    
    private final int numberOfStudents = 5;
    private final int numberOfScores = 4;
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        GradeBook gb = new GradeBook();
        
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.printf("Enter student %d's name: ", i);
            String name = in.nextLine();
            
            // Because of the way I went about things,
            // this function returns the index of the name
            int currentIndex = gb.addName(name);
            
            for (int j = 1; j <= numberOfScores; j++) {
                do {
                    double currentScore;
                    String currentScoreString;
                    System.out.printf("Enter score %d (0-100): ", j);
                    currentScoreString = in.nextLine();
                    try {
                        currentScore = Double.parseDouble(currentScoreString);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Please enter a number.");
                        continue;
                    }
                    
                    if (GradeBook.scoreIsValid(currentScore)) {
                        gb.addScore(currentIndex, currentScore);
                        break;
                    }
                    
                    System.out.println("Please enter a score from 0 to 100.");
                } while (true);
            }
        }
        
        for (int i = 0; i < gb.getStudentCount(); i++) {
            System.out.printf("Student: %s\n", gb.getName(i));
            
            double avg = gb.average(i);
            System.out.printf("\tAverage score: %.3f (%s)\n",
                    avg, GradeBook.scoreToLetter(avg)
            );
            
            double avgNoMin = gb.average(i, true);
            System.out.printf("\tAverage score without min: %.3f (%s)\n",
                    avgNoMin, GradeBook.scoreToLetter(avgNoMin)
            );
        }
    }
}
