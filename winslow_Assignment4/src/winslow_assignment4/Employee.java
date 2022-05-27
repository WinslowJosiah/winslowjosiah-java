package winslow_assignment4;

public class Employee {
    private String name;
    private int idNumber;
    private String department;
    private String position;
    
    public Employee(
            String name, int idNumber, String department, String position
    ) {
        setName(name);
        setIdNumber(idNumber);
        setDepartment(department);
        setPosition(position);
    }
    
    public Employee(String name, int idNumber) {
        this(name, idNumber, "", "");
    }
    
    public Employee() {
        this("", 0, "", "");
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getIdNumber() {
        return this.idNumber;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public String getPosition() {
        return this.position;
    }
}
