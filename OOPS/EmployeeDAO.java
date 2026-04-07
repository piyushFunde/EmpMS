package OOPS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    public boolean addEmployee(Employee emp) throws SQLException {
        String query = "INSERT INTO employees (name, age, salary, department) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBhelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getAge());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getDepartment());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return getEmployeesByDepartment("All Departments");
    }

    public List<Employee> getEmployeesByDepartment(String department) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String query;
        if ("All Departments".equals(department)) {
            query = "SELECT * FROM employees";
        } else {
            query = "SELECT * FROM employees WHERE department = ?";
        }

        try (Connection conn = DBhelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            if (!"All Departments".equals(department)) {
                ps.setString(1, department);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"), 
                        rs.getInt("age"),
                        rs.getDouble("salary"),
                        rs.getString("department")
                    ));
                }
            }
        }
        return list;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DBhelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}