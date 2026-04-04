package OOPS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

// Inheritance: Full-time employee
class FulltimeEmployee extends Employee {
    public FulltimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.salary = monthlySalary;
    }
}

// Hierarchical Inheritance: Part-time employee
class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.salary = hoursWorked * hourlyRate;
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
    }
}


