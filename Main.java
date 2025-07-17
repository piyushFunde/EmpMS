package OOPS.Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

// Abstract class
abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Encapsulation: Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();

    @Override // Polymorphism
    public String toString() {
        return "Employee[name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

// Inheritance: Full-time employee
class FulltimeEmployee extends Employee {
    private double monthlySalary;

    public FulltimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

// Hierarchical Inheritance: Part-time employee
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

// Payroll system to manage employees
class PayRollSystem {
    private ArrayList<Employee> employeesList;

    public PayRollSystem() {
        employeesList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeesList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeesList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeesList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        for (Employee employee : employeesList) {
            System.out.println(employee);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        PayRollSystem payRollSystem = new PayRollSystem();

        FulltimeEmployee emp1 = new FulltimeEmployee("Piyush", 3, 5000);
        PartTimeEmployee emp2 = new PartTimeEmployee("Alex", 2, 40, 100);

        payRollSystem.addEmployee(emp1);
        payRollSystem.addEmployee(emp2);

        System.out.println("Initial Employee System:");
        payRollSystem.displayEmployees();

        System.out.println("\nRemoving Employee with ID 2...");
        payRollSystem.removeEmployee(2);

        System.out.println("\nRemaining Employees Details:");
        payRollSystem.displayEmployees();
        String url = "jdbc:mysql://localhost:3306/payroll_system"; // replace with your DB name
        String user = "root";     // replace with your MySQL username
        String password = "";     // replace with your MySQL password ("" if none)

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL successfully!");

            conn.close(); // close connection
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ SQL Exception occurred.");
            e.printStackTrace();
        }
    }
}


