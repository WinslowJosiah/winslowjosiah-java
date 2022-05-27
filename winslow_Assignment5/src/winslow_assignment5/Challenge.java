package winslow_assignment5;

// Abstract "Challenge" class to hold each solution
abstract class Challenge {
    private final String name;
    public abstract void execute();
    
    public Challenge(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
