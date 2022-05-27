package winslow_assignment6;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Challenge10_2 extends Challenge {
    public Challenge10_2() {
        super("ShiftSupervisor Class");
    }
    
    /*
    In a particular factory, a shift supervisor is a salaried employee who
    supervises a shift. In addition to a salary, the shift supervisor earns a
    yearly bonus when his or her shift meets production goals. Design a
    ShiftSupervisor class that extends the Employee class you created in
    Programming Challenge 1. The ShiftSupervisor class should have a field that
    holds the annual salary and a field that holds the annual production bonus
    that a shift supervisor has earned. Write one or more constructors and the
    appropriate accessor and mutator methods for the class. Demonstrate the
    class by writing a program that uses a ShiftSupervisor object.
    */
    
    public void execute() {
        try {
            ShiftSupervisor richardAybar = new ShiftSupervisor(
                    "Richard Aybar", "922-C",
                    (Calendar) new GregorianCalendar(
                            2021, Calendar.FEBRUARY, 9
                    )
            );
            richardAybar.setAnnualSalary(35000);
            richardAybar.setAnnualBonus(1500);

            System.out.println(richardAybar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
