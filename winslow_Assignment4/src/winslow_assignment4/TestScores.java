package winslow_assignment4;

import java.util.ArrayList;
import java.util.List;

public class TestScores {
    private List<Double> scores;
    
    public TestScores(double ...scores) {
        this.scores = new ArrayList<Double>();
        
        for (double s : scores) {
            this.scores.add(s);
        }
    }
    
    public double getScore(int i) {
        return this.scores.get(i);
    }
    
    public List<Double> getScores() {
        return this.scores;
    }
    
    public void addScore(double s) {
        this.scores.add(s);
    }
    
    public void removeScoreAt(int i) {
        this.scores.remove(i);
    }
    
    public double getAverage() {
        double sum = 0;
        for (double s : this.scores) {
            sum += s;
        }
        return sum / (double)this.scores.size();
    }
}
