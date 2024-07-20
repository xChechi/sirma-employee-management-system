package management;

import employee.Employee;

public interface ManagementRepository {
    void addEmployee(Employee employee);
    void editEmployee(int id, String name, String department, String role, double salary);
    void fireEmployee(int id);
    void listEmployees();
}
