package employee;

import java.time.LocalDate;

public class Employee extends AbstractEmployee {


    public Employee(String name, LocalDate startDate, String department, String role, double salary) {
        super(name, startDate, department, role, salary);
    }

    public String getFormattedSalary() {
        return String.format("%.2f", salary);
    }

    @Override
    public void getDescription() {
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Start Date: " + getStartDate() + ", End Date: " + getEndDate() + ", Department: " + getDepartment() +
                ", Role: " + getRole() + ", Salary: " + getFormattedSalary());
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }
}
