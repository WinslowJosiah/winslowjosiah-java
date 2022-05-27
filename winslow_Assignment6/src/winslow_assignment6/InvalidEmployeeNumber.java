package winslow_assignment6;

public class InvalidEmployeeNumber extends Exception {
    public InvalidEmployeeNumber() {
        super("Invalid employee number");
    }
    
    public InvalidEmployeeNumber(String employeeNumber) {
        super(String.format("Invalid employee number: %s", employeeNumber));
    }
}
