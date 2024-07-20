package menu;

import java.time.LocalDate;

public interface IEmployeeManagementSystem {
    void addEmployee(String name, LocalDate startDate, String department, String role, double salary);
    void editEmployee(int id, String name, String department, String role, double salary);
    void fireEmployee(int id);
    void listEmployees();
}
