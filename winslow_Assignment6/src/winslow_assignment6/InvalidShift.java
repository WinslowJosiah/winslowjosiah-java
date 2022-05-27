package winslow_assignment6;

public class InvalidShift extends Exception {
    public InvalidShift() {
        super("Invalid shift");
    }
    
    public InvalidShift(int shift) {
        super(String.format("Invalid shift: %s", shift));
    }
}
