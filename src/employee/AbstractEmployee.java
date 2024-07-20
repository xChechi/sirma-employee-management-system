package employee;

import java.time.LocalDate;

public abstract class AbstractEmployee implements IEmployee {
    protected int id;
    protected String name;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected String department;
    protected String role;
    protected double salary;
    protected boolean active;
    private static int nextId;

    public AbstractEmployee(String name, LocalDate startDate, String department, String role, double salary) {
        this.id = generateId();
        this.name = name;
        this.startDate = startDate;
        this.endDate = null;
        this.department = department;
        this.role = role;
        this.salary = salary;
        this.active = true;
    }

    private int generateId() {
        return nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    //=================================
    @Override
    public abstract void getDescription();


    @Override
    public abstract boolean isActive();
}
