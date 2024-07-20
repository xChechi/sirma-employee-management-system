package employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class AbstractEmployee implements EmployeeRepository, CsvConvertable {
    protected int id;
    protected String name;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected String department;
    protected String role;
    protected double salary;
    protected boolean active;
    private static int nextId = 1;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (endDate != null) {
            this.active = false;
        }
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
    public boolean isActive() {
        return active || endDate == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEmployee that = (AbstractEmployee) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String endDateStr = (endDate == null) ? "" : endDate.format(formatter);
        return String.format("%d,%s,%s,%s,%s, %s, %.2f,%b",
                id, name, startDate.format(formatter), endDateStr, department, role, salary, active);
    }

    public static AbstractEmployee fromCSV(String csv) {
        String[] parts = csv.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        LocalDate startDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = parts[3].isEmpty() ? null : LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String department = parts[4];
        String role = parts[5];
        double salary = Double.parseDouble(parts[6]);
        boolean active = Boolean.parseBoolean(parts[7]);

        Employee employee = new Employee(name, startDate, department, role, salary);
        employee.setId(id);
        employee.setEndDate(endDate);
        employee.setActive(active);
        return employee;
    }
}
