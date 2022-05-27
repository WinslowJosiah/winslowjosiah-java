package winslow_assignment6;

public class InvalidPayRate extends Exception {
    public InvalidPayRate() {
        super("Invalid pay rate");
    }
    
    public InvalidPayRate(double payRate) {
        super(String.format("Invalid pay rate: %s", payRate));
    }
}
