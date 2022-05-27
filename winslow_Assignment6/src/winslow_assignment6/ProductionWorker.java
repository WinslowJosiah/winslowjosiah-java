package winslow_assignment6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

public class ProductionWorker extends Employee {
    protected int shift;
    protected double hourlyRate;
    
    public ProductionWorker(
            String name, String number, Calendar hireDate, int shift,
            double hourlyRate
    ) throws InvalidEmployeeNumber, InvalidShift, InvalidPayRate {
        super(name, number, hireDate);
        setShift(shift);
        setHourlyRate(hourlyRate);
    }
    
    public ProductionWorker(String name, String number, Calendar hireDate)
            throws InvalidEmployeeNumber {
        super(name, number, hireDate);
    }
    
    public int getShift() {
        return shift;
    }
    
    public double getHourlyRate() {
        return hourlyRate;
    }
    
    public void setShift(int shift) throws InvalidShift {
        if (!(shift == 1 || shift == 2)) {
            throw new InvalidShift(shift);
        }
        this.shift = shift;
    }
    
    public void setHourlyRate(double hourlyRate) throws InvalidPayRate {
        if (hourlyRate < 0) {
            throw new InvalidPayRate(hourlyRate);
        }
        this.hourlyRate = hourlyRate;
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
                .toString();
    }
}
