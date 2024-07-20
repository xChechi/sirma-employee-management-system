package management;

import employee.AbstractEmployee;
import employee.Employee;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeManagementSystem implements ManagementRepository, FileOperations {
    private static final String FILENAME = "employees.csv";
    private final List<AbstractEmployee> employees = new ArrayList<>();

    public void displayMenu() {
        System.out.println("\nEmployee Management System Menu:");
        System.out.println("================================");
        System.out.println("1. Add Employee");
        System.out.println("2. Edit Employee");
        System.out.println("3. Fire Employee");
        System.out.println("4. List of Active Employees");
        System.out.println("5. List of ex-Employees");
        System.out.println("6. Save Progress");
        System.out.println("7. Load Data");
        System.out.println("8. Search employee by");
        System.out.println("8. Exit");
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
                    saveProgress();
                    break;
                case 7:
                    loadData();
                    break;
                case 8:
                    searchEmployee(scanner);
                    break;
                case 9:
                    saveProgress();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void addEmployee(Scanner scanner) {
        AbstractEmployee newEmployee;
        try {
            scanner.nextLine();

            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter start date (dd/MM/yyyy):");
            String startDateString = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println("Enter department:");
            String department = scanner.nextLine();

            System.out.println("Enter role:");
            String role = scanner.nextLine();

            System.out.println("Enter salary:");
            double salary = Double.parseDouble(scanner.nextLine());

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

        System.out.println("Enter new role or press ENTER:");
        String role = scanner.nextLine();

        System.out.println("Enter new salary or press ENTER:");
        String salaryInput = scanner.nextLine();
        scanner.nextLine();

        Optional<AbstractEmployee> employee = employees.stream()
                .filter(e -> e.getId() == employeeId && e.isActive())
                .findFirst();

        if (employee.isPresent()) {
            AbstractEmployee updatedEmployee = employee.get();
            if (name != null && !name.isEmpty()) updatedEmployee.setName(name);
            if (department != null && !department.isEmpty()) updatedEmployee.setDepartment(department);
            if (role != null && !role.isEmpty()) updatedEmployee.setRole(role);
            if (salaryInput != null && !salaryInput.isEmpty()) {
                double salary = Double.parseDouble(salaryInput);
                if (salary >= 0) updatedEmployee.setSalary(salary);
            }
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

    @Override
    public void searchEmployee(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Select search criteria:");
        System.out.println("1. Name");
        System.out.println("2. Department");
        System.out.println("3. Role");

        int criteriaChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter search keyword:");
        String keyword = scanner.nextLine().toLowerCase();

        List<AbstractEmployee> results;

        switch (criteriaChoice) {
            case 1:
                results = employees.stream()
                        .filter(e -> e.getName().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                break;
            case 2:
                results = employees.stream()
                        .filter(e -> e.getDepartment().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                break;
            case 3:
                results = employees.stream()
                        .filter(e -> e.getRole().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No employees found matching the criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(AbstractEmployee::getDescription);
        }
    }

    @Override
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            writer.write("Id,Name,StartDate,EndDate,Department,Role,Salary\n");
            for (AbstractEmployee employee : employees) {
                writer.write(employee.toCSV());
                writer.newLine();
            }
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException {
        employees.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                AbstractEmployee employee = Employee.fromCSV(line);
                employees.add(employee);
            }
        }
    }

    private void saveProgress() {
        try {
            saveToFile(FILENAME);
            System.out.println("Progress saved successfully.");
        } catch (IOException e) {
            System.out.println("Could not save employees to file: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            loadFromFile(FILENAME);
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Could not load employees from file: " + e.getMessage());
        }
    }


}
