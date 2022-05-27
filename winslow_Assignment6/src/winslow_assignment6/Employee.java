package winslow_assignment6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

public class Employee {
    protected String name;
    protected String number;
    protected Calendar hireDate;
    
    public Employee(String name, String number, Calendar hireDate)
            throws InvalidEmployeeNumber {
        setName(name);
        setNumber(number);
        setHireDate(hireDate);
    }
    
    public String getName() {
        return name;
    }
    
    public String getNumber() {
        return number;
    }
    
    public Calendar getHireDate() {
        return hireDate;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setNumber(String number) throws InvalidEmployeeNumber {
        if (!number.matches("^\\d{3}-[A-Z]$")) {
            throw new InvalidEmployeeNumber(number);
        }
        
        this.number = number;
    }
    
    public void setHireDate(Calendar hireDate) {
        this.hireDate = hireDate;
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
                .toString();
    }
}
