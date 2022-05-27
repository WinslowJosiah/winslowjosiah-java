package winslow_assignment6;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Challenge10_3 extends Challenge {
    public Challenge10_3() {
        super("TeamLeader Class");
    }
    
    /*
    In a particular factory, a team leader is an hourly paid production worker
    that leads a small team. In addition to hourly pay, team leaders earn a
    fixed monthly bonus. Team leaders are required to attend a minimum number of
    hours of training per year. Design a TeamLeader class that extends the
    ProductionWorker class you designed in Programming Challenge 1. The
    TeamLeader class should have fields for the monthly bonus amount, the
    required number of training hours, and the number of training hours that the
    team leader has attended. Write one or more constructors and the appropriate
    accessor and mutator methods for the class. Demonstrate the class by writing
    a program that uses a TeamLeader object.
    */
    
    public void execute() {
        try {
            TeamLeader marilynThorne = new TeamLeader(
                    "Marilyn Thorne", "225-X",
                    (Calendar) new GregorianCalendar(
                            2021, Calendar.APRIL, 16
                    ),
                    1, 12.40,
                    1200, 40, 56
            );

            System.out.println(marilynThorne);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
