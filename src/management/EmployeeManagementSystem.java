package management;

import employee.AbstractEmployee;
import employee.Employee;
import employee.EmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeManagementSystem implements ManagementRepository {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public void addEmployee(Employee employee) {
        //Employee employee = new Employee("John Doe", LocalDate.of(2024, 2, 1), "Logistics", "technician",1800.00);
        employees.add(employee);
        System.out.println("Employee " + employee.getName() + " added successfully.");
    }

    @Override
    public void editEmployee(int id, String name, String department, String role, double salary) {
        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getId() == id && e.isActive())
                .findFirst();
        if (employee.isPresent()) {
            AbstractEmployee updatedEmployee = employee.get();
            if (name != null && !name.isEmpty()) updatedEmployee.setName(name);
            if (department != null && !department.isEmpty()) updatedEmployee.setDepartment(department);
            if (role != null && !role.isEmpty()) updatedEmployee.setRole(role);
            if (salary >= 0) updatedEmployee.setSalary(salary);
            System.out.println("Employee " + id + " updated successfully.");
        } else {
            System.out.println("Employee not found or inactive.");
        }
    }

    @Override
    public void fireEmployee(int id) {
        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getId() == id && e.isActive()).findFirst();
        if (employee.isPresent()) {
            AbstractEmployee firedEmployee = employee.get();
            firedEmployee.setActive(false);
            firedEmployee.setEndDate(LocalDate.now());
            System.out.println("Employee " + id + " has been flagged as no longer working at the company.");
        } else {
            System.out.println("Employee not found or already inactive.");
        }
    }

    @Override
    public void listEmployees() {
        System.out.println("List of Active Employees:");
        employees.stream()
                .filter(EmployeeRepository::isActive)
                .forEach(EmployeeRepository::getDescription);
    }
}
