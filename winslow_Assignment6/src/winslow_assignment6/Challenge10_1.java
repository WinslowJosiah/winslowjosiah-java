package winslow_assignment6;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Challenge10_1 extends Challenge {
    public Challenge10_1() {
        super("Employee and ProductionWorker Classes");
    }
    
    /*
    Design a class named Employee. The class should keep the following
    information in fields:
    
    * Employee name
    * Employee number in the format XXX-L, where each X is a digit within the
    range 0-9 and the L is a letter within the range A-M
    * Hire date
    
    Write one or more constructors and the appropriate accessor and mutator
    methods for the class.
    
    Next, write a class named ProductionWorker that extends the Employee class.
    The ProductionWorker class should have fields to hold the following
    information:
    
    * Shift (an integer)
    * Hourly pay rate (a double)
    
    The workday is divided into two shifts: day and night. The shift field will
    be an integer value representing the shift that the employee works. The day
    shift is shift 1 and the night shift is shift 2. Write one or more
    constructors and the appropriate accessor and mutator methods for the class.
    Demonstrate the classes by writing a program that uses a ProductionWorker
    object.
    */
    
    public void execute() {
        try {
            ProductionWorker leonHall = new ProductionWorker(
                    "Leon Hall", "489-J",
                    (Calendar) new GregorianCalendar(
                            2021, Calendar.JANUARY, 12
                    ),
                    2, 15.50
            );

            System.out.println(leonHall);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
