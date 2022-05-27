package winslow_assignment6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

public class TeamLeader extends ProductionWorker {
    protected double monthlyBonus;
    protected int requiredTrainingHours;
    protected int attendedTrainingHours;
    
    public TeamLeader(
            String name, String number, Calendar hireDate, int shift,
            double hourlyRate, double monthlyBonus, int requiredTrainingHours,
            int attendedTrainingHours
    ) throws InvalidEmployeeNumber, InvalidShift, InvalidPayRate {
        super(name, number, hireDate, shift, hourlyRate);
        setMonthlyBonus(monthlyBonus);
        setRequiredTrainingHours(requiredTrainingHours);
        setAttendedTrainingHours(attendedTrainingHours);
    }
    
    public TeamLeader(
            String name, String number, Calendar hireDate, int shift,
            double hourlyRate
    ) throws InvalidEmployeeNumber, InvalidShift, InvalidPayRate {
        super(name, number, hireDate, shift, hourlyRate);
    }
    
    public TeamLeader(String name, String number, Calendar hireDate)
            throws InvalidEmployeeNumber {
        super(name, number, hireDate);
    }
    
    public double getMonthlyBonus() {
        return monthlyBonus;
    }

    public double getRequiredTrainingHours() {
        return requiredTrainingHours;
    }

    public double getAttendedTrainingHours() {
        return attendedTrainingHours;
    }

    public void setMonthlyBonus(double monthlyBonus) {
        this.monthlyBonus = monthlyBonus;
    }

    public void setRequiredTrainingHours(int requiredTrainingHours) {
        this.requiredTrainingHours = requiredTrainingHours;
    }

    public void setAttendedTrainingHours(int attendedTrainingHours) {
        this.attendedTrainingHours = attendedTrainingHours;
    }
    
    @Override
    public String toString() {
        return new StringJoiner("\n")
                .add(String.format("Name: %s", name))
                .add(String.format("Employee number: %s", number))
                .add(String.format("Hire date: %s",
                        new SimpleDateFormat("MMMM d, yyyy")
                                .format(hireDate.getTime()))
                )
                .add(String.format("Shift: %s",
                        shift == 1 ? "day" : "night"
                ))
                .add(String.format("Hourly rate: $%.2f", hourlyRate))
                .add(String.format("Monthly bonus: $%.2f", monthlyBonus))
                .add(String.format("Required training: %d hours",
                        requiredTrainingHours
                ))
                .add(String.format("Attended training: %d hours",
                        attendedTrainingHours
                ))
                .toString();
    }
}
