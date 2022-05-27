package winslow_assignment6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

public class ShiftSupervisor extends Employee {
    protected double annualSalary;
    protected double annualBonus;
    
    public ShiftSupervisor(
            String name, String number, Calendar hireDate, double annualSalary,
            double annualBonus
    ) throws InvalidEmployeeNumber {
        super(name, number, hireDate);
        setAnnualSalary(annualSalary);
        setAnnualBonus(annualBonus);
    }
    
    public ShiftSupervisor(String name, String number, Calendar hireDate)
            throws InvalidEmployeeNumber {
        super(name, number, hireDate);
    }
    
    public double getAnnualSalary() {
        return annualSalary;
    }
    
    public double getAnnualBonus() {
        return annualBonus;
    }
    
    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }
    
    public void setAnnualBonus(double annualBonus) {
        this.annualBonus = annualBonus;
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
                .add(String.format("Annual salary: $%.2f", annualSalary))
                .add(String.format("Annual bonus: $%.2f", annualBonus))
                .toString();
    }
}
