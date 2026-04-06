package OOPS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    public boolean addEmployee(Employee emp) throws SQLException {
        String query = "INSERT INTO employees (name, age, salary) VALUES (?, ?, ?)";
        try (Connection conn = DBhelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getAge());
            ps.setDouble(3, emp.getSalary());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection conn = DBhelper.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"), 
                    rs.getInt("age"),
                    rs.getDouble("salary")
                ));
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