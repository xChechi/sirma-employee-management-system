# OOP Exam – 13.07.2024
## Employee Management System

### Project Overview
This project involves developing a console-based application in Java, focusing on Object-Oriented Programming (OOP) principles. The application will serve as an Employee Management System, enabling users to manage employee data for a company.

### Key Objectives
- Implement OOP concepts like encapsulation, inheritance, polymorphism, and abstraction.
- Create a user-friendly console interface.
- Ensure data persistence by reading and writing employee data to/from a CSV file.

### Employee Data Management
- **Add Employee:** Input employee details (ID, name, department, role, salary) and add them to the system.
- **Edit Employee:** Modify existing employee details.
- **Fire Employee:** Flag that the employee doesn’t work at the company anymore.
- **List Employees:** Display a list of all active employees with their details.

### Data Persistence
- **Read Data:** Initialize the system with employee data from a CSV file.
- **Write Data:** Save current employee data to a CSV file upon exit.
- **Search Functionality:** Search for employees by name, ID, or department.

### Technical Requirements
- **Classes:** Employee and other relevant classes encapsulating respective properties and methods.
- **Interfaces:** Define interfaces for common functionalities.
- **Abstract Classes:** Use abstract classes where appropriate.

### File Handling
- Implement CSV file operations for data persistence (**DO NOT USE LIBRARIES FOR READING CSV FILES**).

### Exception Handling
- Robust error and exception management for user inputs and file operations.

### Data Structures
- Use appropriate collections (like Lists, Maps) to manage and organize data.

### Documentation
- Comprehensive documentation of classes, methods, and logic flow.
- Source Code: Well-organized and commented Java source files.

### Evaluation Criteria
- **Adherence to OOP principles**
- **Code quality and organization**
- **Functionality and correctness**
- **Error handling and validations**
- **Documentation and ease of use**

Each criterion gives you 20% of total points.

# Comprehensive documentation of classes, methods, and logic flow

# Class: `AbstractEmployee`
**Description**:  
`AbstractEmployee` is an abstract class representing a generic employee in the system. It implements the `EmployeeRepository` and `CsvConvertable` interfaces, providing methods for employee management and CSV data handling. This class is designed to be extended by concrete classes like `Employee`.

## Fields
- **`protected int id`**: Unique identifier for the employee.
- **`protected String name`**: Name of the employee.
- **`protected LocalDate startDate`**: Date when the employee started.
- **`protected LocalDate endDate`**: Date when the employee left (null if currently employed).
- **`protected String department`**: Department in which the employee works.
- **`protected String role`**: Role or job title of the employee.
- **`protected double salary`**: Employee's salary.
- **`protected boolean active`**: Status indicating whether the employee is currently active.
- **`private static int nextId`**: Static field for generating unique employee IDs.

## Constructor
- **`public AbstractEmployee(String name, LocalDate startDate, String department, String role, double salary)`**  
  Initializes a new `AbstractEmployee` with the given parameters. The `id` is automatically generated. `endDate` is set to `null`, and `active` is set to `true` by default.

## Methods

### Abstract Methods
- **`@Override public abstract void getDescription()`**  
  Abstract method that must be implemented by subclasses to provide a description of the employee.

### Implemented Methods
- **`@Override public boolean isActive()`**  
  Returns `true` if the employee is active or if `endDate` is `null`; otherwise, returns `false`.

- **`@Override public boolean equals(Object o)`**  
  Compares this employee with another object for equality based on the employee ID.

- **`@Override public int hashCode()`**  
  Returns a hash code value for the employee based on the ID.

- **`@Override public String toCSV()`**  
  Converts the employee's data into a CSV string format for serialization. The format includes fields for ID, name, start date, end date, department, role, salary, and active status.

- **`public static AbstractEmployee fromCSV(String csv)`**  
  Static method to create an `AbstractEmployee` instance from a CSV string. Parses the CSV fields and initializes an `Employee` object with the provided data.

## Logic Flow
- **ID Generation**: The `generateId()` method ensures that each `AbstractEmployee` has a unique ID, incremented from `nextId`.

- **Active Status**: The `isActive()` method determines if an employee is currently active based on the presence of an `endDate`.

- **CSV Handling**: The `toCSV()` method formats the employee's details into a CSV-compatible string for easy storage. The `fromCSV()` method parses a CSV string to recreate an `Employee` object with the provided data.

# Class: `Employee`

**Description**:  
`Employee` is a concrete class that extends `AbstractEmployee`. It represents a standard employee with additional formatting for salary display and specific implementation of the abstract methods inherited from `AbstractEmployee`.

## Constructor
- **`public Employee(String name, LocalDate startDate, String department, String role, double salary)`**  
  Initializes a new `Employee` with the specified details. This constructor calls the superclass constructor in `AbstractEmployee` to set the employee's initial state.

## Methods

### Getters and Formatters
- **`public String getFormattedSalary()`**  
  Returns the employee's salary formatted as a string with two decimal places. This method ensures that the salary is presented in a user-friendly format.

### Overridden Methods
- **`@Override public void getDescription()`**  
  Provides a detailed description of the employee, including ID, name, start date, end date, department, role, and formatted salary. This method overrides the abstract method in `AbstractEmployee` to output a complete description of the employee.

- **`@Override public boolean isActive()`**  
  Inherits the `isActive()` method from `AbstractEmployee` to determine if the employee is currently active. It returns the active status based on whether the `endDate` is null.

### Static Methods
- **`public static AbstractEmployee fromCSV(String csv)`**  
  Static method to create an `AbstractEmployee` instance from a CSV string. It leverages the `fromCSV()` method in the `AbstractEmployee` class to parse the CSV string and initialize an `Employee` object with the provided data.

# Class: `EmployeeManagementSystem`

**Description**:  
`EmployeeManagementSystem` is a class that manages a collection of `AbstractEmployee` instances. It provides functionality to add, edit, fire, list, and search employees, as well as to save and load employee data from a CSV file. The class implements `ManagementRepository` for employee management operations and `FileOperations` for file handling.

## Fields

- **`private static final String FILENAME = "employees.csv";`**  
  The name of the file where employee data is saved and loaded.

- **`private final List<AbstractEmployee> employees = new ArrayList<>();`**  
  A list that holds all employee records.

## Methods

### Menu Display
- **`public void displayMenu()`**  
  Displays the main menu of the Employee Management System with options for various operations such as adding, editing, or firing employees.

### Main Loop
- **`public void start()`**  
  Starts the main loop of the system, displaying the menu and processing user choices until the user decides to exit. Handles the interaction between the user and the system.

### Employee Management
- **`@Override public void addEmployee(Scanner scanner)`**  
  Adds a new employee to the system. Prompts the user for employee details and creates an `Employee` instance. Handles parsing of input data and potential exceptions.

- **`@Override public void editEmployee(Scanner scanner)`**  
  Edits an existing employee's details. Finds the employee by ID, updates fields if provided, and handles cases where fields are left empty or invalid.

- **`@Override public void fireEmployee(Scanner scanner)`**  
  Flags an employee as inactive and sets their end date to the current date. Finds the employee by ID and updates their status accordingly.

- **`@Override public void listActiveEmployees()`**  
  Lists all active employees by filtering the employee list and displaying their descriptions.

- **`@Override public void listLeftEmployees()`**  
  Lists all employees who have left the company by filtering the employee list for inactive employees.

### Employee Search
- **`@Override public void searchEmployee(Scanner scanner)`**  
  Allows the user to search for employees based on criteria such as name, department, or role. Displays search results that match the specified keyword.

### File Operations
- **`@Override public void saveToFile(String filename) throws IOException`**  
  Saves all employee data to a CSV file. Writes employee details in CSV format, including headers for each field.

- **`@Override public void loadFromFile(String filename) throws IOException`**  
  Loads employee data from a CSV file. Reads each line, parses the data to create `Employee` instances, and populates the employee list.

### Internal Methods
- **`private void saveProgress()`**  
  Saves the current state of the employee list to the file defined by `FILENAME`. Handles IO exceptions and confirms success.

- **`private void loadData()`**  
  Loads employee data from the file defined by `FILENAME`. Handles IO exceptions and confirms success.

## Logic Flow
- **User Interaction**: The `start()` method runs a loop that continuously presents the menu and processes user input to perform various actions such as adding, editing, or listing employees.

- **Employee Management**: The system allows for CRUD operations on employee records, including adding new employees, editing existing records, and marking employees as no longer active.

- **File Handling**: The `saveToFile()` and `loadFromFile()` methods handle the persistence of employee data. The data is saved and loaded in CSV format, ensuring that employee details are preserved between sessions.

- **Search Functionality**: The `searchEmployee()` method provides flexible search options based on different criteria, enhancing the ability to find specific employees.

## Notes
- **Error Handling**: Methods include basic error handling for invalid input and file operations, with user-friendly messages for issues encountered during these processes.

- **Data Integrity**: The use of CSV format ensures that employee data can be easily saved and restored, maintaining data integrity across sessions.
