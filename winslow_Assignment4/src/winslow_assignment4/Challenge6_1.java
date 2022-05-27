package winslow_assignment4;

import java.util.ArrayList;
import java.util.List;

public class Challenge6_1 extends Challenge {
    public Challenge6_1() {
        super("Employee Class");
    }
    
    /*
    Write a class named Employee that has the following fields:
    
    * name. The name field references a String object that holds the employee's
    name.
    * idNumber. The idNumber is an int variable that holds the employee's ID
    number.
    * department. The department field references a String object that holds the
    name of the department where the employee works.
    * position. The position field references a String object that holds the
    employee's job title.
    
    The class should have the following constructors:
    
    * A constructor that accepts the following values as arguments and assigns
    them to the appropriate fields: employee's name, employee's ID number,
    department, and position.
    * A constructor that accepts the following values as arguments and assigns
    them to the appropriate fields: employee's name and ID number. The
    department and position fields should be assigned an empty string ("").
    * A no-arg constructor that assigns empty string ("") to the name,
    department, and position fields, and 0 to the idNumber field.
    
    Write appropriate mutator methods that store values in these fields and
    accessor methods that return the values in these fields. Once you have
    written the class, write a separate program that creates three Employee
    objects to hold the following data:
    
     Name        | ID Number | Department    | Position
    -------------+-----------+---------------+----------------
    Susan Meyers | 47899     | Accounting    | Vice President
    Mark Jones   | 39119     | IT            | Programmer
    Joy Rogers   | 81774     | Manufacturing | Engineer
    
    The program should store this data in the three objects and then display the
    data for each employee on the screen.
    */
    
    public void execute() {
        List<Employee> employees = new ArrayList<Employee>();
        
        // All-args constructor
        employees.add(new Employee(
                "Susan Meyers", 47889, "Accounting", "Vice President"
        ));
        
        // Some-args constructor
        Employee markJones = new Employee("Mark Jones", 39119);
        markJones.setDepartment("IT");
        markJones.setPosition("Programmer");
        employees.add(markJones);
        
        // No-args constructor
        Employee joyRogers = new Employee();
        joyRogers.setName("Joy Rogers");
        joyRogers.setIdNumber(81774);
        joyRogers.setDepartment("Manufacturing");
        joyRogers.setPosition("Engineer");
        employees.add(joyRogers);
        
        for (Employee e : employees) {
            System.out.printf("Name: %s\n", e.getName());
            System.out.printf("ID: %d\n", e.getIdNumber());
            System.out.printf("Department: %s\n", e.getDepartment());
            System.out.printf("Position: %s\n", e.getPosition());
            System.out.println("");
        }
    }
}
