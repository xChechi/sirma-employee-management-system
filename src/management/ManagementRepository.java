package management;

import employee.Employee;

import java.util.Scanner;

public interface ManagementRepository {
    void addEmployee(Scanner scanner);
    void editEmployee(Scanner scanner);
    void fireEmployee(Scanner scanner);
    void listActiveEmployees();
    void listLeftEmployees();
}
