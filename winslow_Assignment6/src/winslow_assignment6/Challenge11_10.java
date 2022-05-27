package winslow_assignment6;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Challenge11_10 extends Challenge {
    public Challenge11_10() {
        super("Exception Project");
    }
    
    /*
    This assignment assumes you have completed Programming Challenge 1 of
    Chapter 10 (Employee and ProductionWorker Classes). Modify the Employee and
    ProductionWorker classes so they throw exceptions when the following errors
    occur:
    
    * The Employee class should throw an exception named InvalidEmployeeNumber
    when it receives an invalid employee number.
    * The ProductionWorker class should throw an exception named InvalidShift
    when it receives an invalid shift.
    * The ProductionWorker class should throw an exception named InvalidPayRate
    when it receives a negative number for the hourly pay rate.
    
    Write a test program that demonstrates how each of these exception
    conditions works.
    */
    
    public void execute() {
        try {
            System.out.println(
                    "Attempting to construct ShiftSupervisor Richard Lewis..."
            );
            ShiftSupervisor richardLewis = new ShiftSupervisor(
                    "Richard Lewis", "296-m", // lowercase letter!
                    (Calendar) new GregorianCalendar(
                            2021, Calendar.MARCH, 23
                    )
            );
            
            System.out.println(richardLewis); // should never run
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            System.out.println(
                    "Attempting to construct ProductionWorker Adam Allen..."
            );
            ProductionWorker adamAllen = new ProductionWorker(
                    "Adam Allen", "473-V",
                    (Calendar) new GregorianCalendar(
                            2021, Calendar.AUGUST, 9
                    ),
                    3, 14.92 // shift == 3!
            );
            
            System.out.println(adamAllen); // should never run
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            System.out.println(
                    "Attempting to construct TeamLeader David Shaw..."
            );
            TeamLeader davidShaw = new TeamLeader(
                    "David Shaw", "977-H",
                    (Calendar) new GregorianCalendar(
                            2021, Calendar.NOVEMBER, 19
                    ),
                    1, -15.51 // negative payrate!
            );
            
            System.out.println(davidShaw); // should never run
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
