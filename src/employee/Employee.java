package employee;

import java.time.LocalDate;

public class Employee extends AbstractEmployee {


    public Employee(String name, LocalDate startDate, String department, String role, double salary) {
        super(name, startDate, department, role, salary);
    }

    @Override
    public void getDescription() {
        System.out.println("employee.Employee ID: " + id + ", Name: " + name + ", Department: " + department +
                ", Role: " + role + ", Salary: " + salary + ", Active: " + isActive());
    }

    @Override
    public boolean isActive() {
        return super.active;
    }
}
