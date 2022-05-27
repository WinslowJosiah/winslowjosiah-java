package winslow_assignment5;

import java.util.ArrayList;
import java.util.List;

public class GradeBook {
    private List<String> names;
    private List<List<Double>> scores;
    
    private static final char[] letterGrades = {'A', 'B', 'C', 'D', 'F'};
    private static final double[] minScores = {90, 80, 70, 60, 0};
    
    private static final double MIN_SCORE = 0;
    private static final double MAX_SCORE = 100;
    
    public GradeBook() {
        names = new ArrayList<>();
        scores = new ArrayList<>();
    }
    
    public String getName(int studentIndex) {
        return names.get(studentIndex);
    }
    
    public List<Double> getScores(int studentIndex) {
        return scores.get(studentIndex);
    }
    
    public int getStudentCount() {
        return names.size();
    }
    
    public int addName(String name) {
        names.add(name);
        scores.add(new ArrayList<>());
        
        // Return index of name into ArrayLists
        return names.size() - 1;
    }
    
    public void addScore(int studentIndex, double score) {
        scores.get(studentIndex).add(score);
    }
    
    public static boolean scoreIsValid(double score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
    
    public static char scoreToLetter(double score) {
        char letter = '?';
        
        // I like this loop better than a long if-else statement
        for (int i = 0; i < letterGrades.length; i++) {
            if (score >= minScores[i]) {
                letter = letterGrades[i];
                break;
            }
        }
        
        return letter;
    }
    
    public double average(int studentIndex, boolean excludeLowest) {
        // Usually I wouldn't actually initialize this, but here,
        // I can guarantee this is the "maximum minimum", so to speak
        double minScore = MAX_SCORE;
        
        double average = 0;
        double scoresLength = scores.get(studentIndex).size();
        for (int i = 0; i < scoresLength; i++) {
            double currentScore = scores.get(studentIndex).get(i);
            average += currentScore;
            
            // Update max element
            if (currentScore < minScore) {
                minScore = currentScore;
            }
        }
        
        if (excludeLowest) {
            average -= minScore;
            scoresLength--;
        }
        
        average /= scoresLength;
        return average;
    }
    
    public double average(int studentIndex) {
        return average(studentIndex, false);
    }
}
