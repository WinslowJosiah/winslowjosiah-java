package winslow_assignment3;

import java.util.List;

// Abstract "Chapter" class to hold each chapter's challenges
abstract class Chapter {
    private final String name;
    private final List<Integer> chalIndices;
    private final List<Challenge> challenges;
    
    public Chapter(
            String name, List<Integer> chalIndices, List<Challenge> challenges
    ) {
        assert chalIndices.size() == challenges.size();
        
        this.name = name;
        this.chalIndices = chalIndices;
        this.challenges = challenges;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Challenge> getChallenges() {
        return challenges;
    }
    
    public List<Integer> getChalIndices() {
        return chalIndices;
    }
}
