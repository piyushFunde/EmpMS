package OOPS.Project;

import java.sql.*;

public class DBhelper {
    static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
