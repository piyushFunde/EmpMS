package OOPS.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertEmployee {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my_company"; // ✅ your DB name
        String user = "root"; // ✅ your MySQL username
        String password = ""; // ✅ your MySQL password
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO employees (id, name, salary) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 101);
            stmt.setString(2, "Piyush");
            stmt.setDouble(3, 50000);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee inserted successfully!");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
