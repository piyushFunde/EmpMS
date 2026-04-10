# ZenithHR: Employee Management System

A professional Java-based payroll and employee management system demonstrating advanced Object-Oriented Programming (OOP) principles, GUI integration, and secure database management.

## Project Overview

ZenithHR provides a unified platform to manage employee lifecycles, payroll calculations, and persistent data storage.

- **Payroll Engine**: Robust business logic using polymorphism for varied employee types.
- **Modern GUI**: A sleek Swing-based desktop interface for real-time employee management.
- **Secure Persistence**: Centralized JDBC integration with MySQL using PreparedStatements to prevent SQL injection.

##  Architecture & OOP Concepts

### Core Components (OOPS Package)

- **[`Employee`](OOPS/Employee.java)**: The central data model supporting both GUI and backend logic.
- **[`FulltimeEmployee`](OOPS/Main.java)**: Specialization for salaried staff.
- **[`PartTimeEmployee`](OOPS/Main.java)**: Dynamic salary calculation based on hours and rates.
- **[`EmployeeDAO`](OOPS/EmployeeDAO.java)**: Data Access Object for high-level database operations.
- **[`DBhelper`](OOPS/DBhelper.java)**: Centralized connection utility for consistent database access.

##  Getting Started

### Prerequisites
- Java JDK 8+
- MySQL Server
- MySQL JDBC Driver (mysql-connector-java)

### Setup Instructions

1. **Create Database**
   ```sql
   CREATE DATABASE payroll_system;
   USE payroll_system;
   
   CREATE TABLE employees (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       age INT,
       salary DOUBLE,
       department VARCHAR(50)
   );
   ```

2. **Update Database Configuration**
   Edit `OOPS/DBhelper.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/payroll_system";
   private static final String USER = "root";
   private static final String PASS = "your_password";
   ```

3. **Compile Project**
   ```bash
   javac OOPS/*.java
   ```

4. **Run Application**
   - **Launch GUI**: `java OOPS.EmployeeGUI`
   - **Run CLI Demo**: `java OOPS.Main`

##  OOP Concepts Demonstrated

| Concept | Implementation |
|---------|-----------------|
| **Inheritance** | `FulltimeEmployee` & `PartTimeEmployee` extend `Employee` |
| **Polymorphism** | Overridden constructors and dynamic salary assignments |
| **Encapsulation** | Private attributes with public accessors in `Employee` |
| **Data Abstraction** | Decoupled Data Access Object (`EmployeeDAO`) from UI logic |

## 💾 Database Schema

### `employees` table
| Field | Type | Description |
|-------|------|-------------|
| `id` | INT | Primary Key (Auto-increment) |
| `name` | VARCHAR | Employee Full Name |
| `age` | INT | Employee Age |
| `salary` | DOUBLE | Calculated Monthly/Hourly Salary |

## 🔧 Usage Examples

### Running the ZenithHR GUI
1. Launch `EmployeeGUI`.
2. Enter the employee's Name, Age, and Salary.
3. Click **Add Employee** to persist to the database.
4. Use **Refresh List** to sync with the latest database records.

##  Future Roadmap
- [ ] User Authentication & Role-based Access
- [ ] Advanced Reporting (Export to PDF/Excel)
- [ ] Employee Performance Tracking
- [ ] Automated Salary Slip Generation

---
*Developed with ZenithHR: Precision in People Management.*
