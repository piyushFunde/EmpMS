# Employee Management System

A Java-based payroll and employee management system that demonstrates Object-Oriented Programming (OOP) principles with GUI and database integration.

## 📋 Project Overview

This project implements an employee management system with the following components:

- **Payroll System**: Core business logic for managing employees
- **GUI Application**: User-friendly interface for employee operations
- **Database Integration**: MySQL database connectivity for persistent storage

## 🏗️ Architecture & OOP Concepts

### Core Classes

- **[`Employee`](Main.java)** (Abstract Class)
  - Base class for all employee types
  - Encapsulation: Private attributes with public getters
  - Abstract method: `calculateSalary()`

- **[`FulltimeEmployee`](Main.java)** (Inheritance)
  - Extends `Employee`
  - Implements fixed monthly salary calculation

- **[`PartTimeEmployee`](Main.java)** (Hierarchical Inheritance)
  - Extends `Employee`
  - Calculates salary based on hours worked × hourly rate

- **[`PayRollSystem`](Main.java)** (Collection Management)
  - Manages `ArrayList<Employee>`
  - Features: Add, remove, and display employees

- **[`DBhelper`](DBhelper.java)** (Database Utility)
  - Singleton pattern for database connections
  - Centralized connection management


## 🔑 Key Features

### 1. **Payroll System** ([`Main.java`](Main.java))
- Employee type polymorphism
- Add/remove employees dynamically
- Display employee details with calculated salaries
- MySQL database connectivity test

### 2. **GUI Application** ([`EmployeeGUI.java`](EmployeeGUI.java))
- Insert employee records via form
- View all employees in database
- Error handling with dialog boxes
- Uses [`DBhelper`](DBhelper.java) for connections

### 3. **Database Operations** ([`InsertEmployee.java`](InsertEmployee.java))
- Prepared statement for secure data insertion
- Prevents SQL injection

### 4. **Database Helper** ([`DBhelper.java`](DBhelper.java))
- Centralized connection configuration
- Reusable connection method

## 🚀 Getting Started

### Prerequisites
- Java JDK 8+
- MySQL Server
- MySQL JDBC Driver (mysql-connector-java)

### Setup Instructions

1. **Create Database**
   ```sql
   CREATE DATABASE my_company;
   USE my_company;
   
   CREATE TABLE employees (
       id INT PRIMARY KEY,
       name VARCHAR(100),
       salary DOUBLE
   );
   
   CREATE TABLE employee (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       age INT
   );
   ```

2. **Update Database Configuration**
   - Edit [`DBhelper.java`](DBhelper.java):
     ```java
     static final String DB_URL = "jdbc:mysql://localhost:3306/my_company";
     static final String USER = "root";
     static final String PASS = ""; // Add your password if needed
     ```

3. **Add MySQL JDBC Driver**
   - Download: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
   - Add to classpath

4. **Compile & Run**
   ```bash
   javac OOPS/Project/*.java
   java OOPS.Project.Main           # Run payroll system
   java OOPS.Project.EmployeeGUI    # Run GUI application
   ```

## 📊 OOP Concepts Demonstrated

| Concept | Implementation |
|---------|-----------------|
| **Abstraction** | `Employee` abstract class with abstract `calculateSalary()` |
| **Inheritance** | `FulltimeEmployee` & `PartTimeEmployee` extend `Employee` |
| **Polymorphism** | Overridden `calculateSalary()` & `toString()` methods |
| **Encapsulation** | Private attributes with public getters in `Employee` |
| **Collections** | `ArrayList<Employee>` in `PayRollSystem` |

## 💾 Database Schema

### employees table
```sql
id (INT, PRIMARY KEY)
name (VARCHAR)
salary (DOUBLE)
```

### employee table
```sql
id (INT, AUTO_INCREMENT, PRIMARY KEY)
name (VARCHAR)
age (INT)
```

## 🔧 Usage Examples

### Running Payroll System
```java
PayRollSystem payRollSystem = new PayRollSystem();
Employee emp1 = new FulltimeEmployee("Piyush", 3, 5000);
Employee emp2 = new PartTimeEmployee("Alex", 2, 40, 100);
payRollSystem.addEmployee(emp1);
payRollSystem.displayEmployees();
```

### Using GUI Application
1. Launch `EmployeeGUI`
2. Enter Name and Age
3. Click **Insert** to add employee
4. Click **View All** to see all employees

## 📝 Notes

- Ensure MySQL server is running before executing database operations
- Update credentials in [`DBhelper.java`](DBhelper.java) according to your setup
- GUI uses Swing framework for desktop interface
- All database operations use PreparedStatements for security

## 🎯 Future Enhancements

- Update/Delete employee functionality
- Employee search and filter
- Salary reports and analytics
- User authentication
- Data export to CSV/PDF
