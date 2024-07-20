package management;

import employee.AbstractEmployee;
import employee.Employee;
import employee.EmployeeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeManagementSystem implements ManagementRepository {
    private List<AbstractEmployee> employees = new ArrayList<>();

    public void displayMenu() {
        System.out.println("\nEmployee Management System Menu:");
        System.out.println("================================");
        System.out.println("1. Add Employee");
        System.out.println("2. Edit Employee");
        System.out.println("3. Fire Employee");
        System.out.println("4. List of Active Employees");
        System.out.println("5. List of ex-Employees");
        System.out.println("6. Exit");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    editEmployee(scanner);
                    break;
                case 3:
                    fireEmployee(scanner);
                    break;
                case 4:
                    listActiveEmployees();
                    break;
                case 5:
                    listLeftEmployees();
                    break;
                case 6:
                    //saveProgress();
                    break;
                case 7:
                    //loadInventory();
                    break;
                case 8:
                    //sortItemsByName();
                    break;
                case 9:
                    //saveProgress();
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void addEmployee(Scanner scanner) {
        Employee newEmployee;
        try {
            scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter start date (dd/MM/yyyy):");
            String startDateString = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println("Enter department:");
            String department = scanner.nextLine();
            //scanner.nextLine();

            System.out.println("Enter role:");
            String role = scanner.nextLine();
            //scanner.nextLine();

            System.out.println("Enter salary:");
            double salary = Double.parseDouble(scanner.nextLine());
            //scanner.nextLine();

            newEmployee = new Employee(name, startDate, department, role, salary);
            employees.add(newEmployee);
            System.out.println("Employee " + newEmployee.getName() + " added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
        }

    }

    @Override
    public void editEmployee(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Enter employee ID:");
        int employeeId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter new name or press ENTER:");
        String name = scanner.nextLine();

        System.out.println("Enter new department or press ENTER:");
        String department = scanner.nextLine();
        //scanner.nextLine();

        System.out.println("Enter new role or press ENTER:");
        String role = scanner.nextLine();
        //scanner.nextLine();

        System.out.println("Enter new salary or press ENTER:");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        Optional<AbstractEmployee> employee = employees.stream()
                .filter(e -> e.getId() == employeeId && e.isActive())
                .findFirst();

        if (employee.isPresent()) {
            AbstractEmployee updatedEmployee = employee.get();
            if (name != null && !name.isEmpty()) updatedEmployee.setName(name);
            if (department != null && !department.isEmpty()) updatedEmployee.setDepartment(department);
            if (role != null && !role.isEmpty()) updatedEmployee.setRole(role);
            if (salary >= 0) updatedEmployee.setSalary(salary);
            System.out.println("Employee " + employeeId + " updated successfully.");
        } else {
            System.out.println("Employee not found or inactive.");
        }
    }

    @Override
    public void fireEmployee(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Enter employee ID:");
        int employeeId = Integer.parseInt(scanner.nextLine());

        Optional<AbstractEmployee> employee = employees.stream()
                .filter(e -> e.getId() == employeeId && e.isActive()).findFirst();
        if (employee.isPresent()) {
            AbstractEmployee firedEmployee = employee.get();
            firedEmployee.setActive(false);
            firedEmployee.setEndDate(LocalDate.now());
            System.out.println("Employee " + employeeId + " has been flagged as no longer working at the company.");
        } else {
            System.out.println("Employee not found or already inactive.");
        }
    }

    @Override
    public void listActiveEmployees() {
        System.out.println("List of Active Employees:");
        employees.stream()
                .filter(AbstractEmployee::isActive)
                .forEach(AbstractEmployee::getDescription);
    }

    @Override
    public void listLeftEmployees() {
        System.out.println("List of Employees already left:");
        employees.stream()
                .filter(e -> !e.isActive())
                .forEach(AbstractEmployee::getDescription);
    }
}
