package OOPS.Project;

import javax.swing.*;
import java.sql.*;

public class EmployeeGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Manager");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 30, 80, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 30, 200, 25);
        frame.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 70, 80, 25);
        frame.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(100, 70, 200, 25);
        frame.add(ageField);

        JButton insertBtn = new JButton("Insert");
        insertBtn.setBounds(100, 120, 90, 30);
        frame.add(insertBtn);

        JButton viewBtn = new JButton("View All");
        viewBtn.setBounds(210, 120, 90, 30);
        frame.add(viewBtn);

        // 🔘 Insert button action
        insertBtn.addActionListener(e -> {
            String name = nameField.getText();
            int age;

            try {
                age = Integer.parseInt(ageField.getText());

                Connection conn = DBhelper.getConnection();
                String query = "INSERT INTO employee (name, age) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, age);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(frame, "Employee inserted successfully!");
                }

                ps.close();
                conn.close();

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid age format.");
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(frame, "Database error: " + se.getMessage());
            }
        });

        // 👀 View All button action
        viewBtn.addActionListener(e -> {
            try {
                Connection conn = DBhelper.getConnection();
                String query = "SELECT * FROM employee";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    sb.append("ID: ").append(rs.getInt("id"))
                            .append(", Name: ").append(rs.getString("name"))
                            .append(", Age: ").append(rs.getInt("age"))
                            .append("\n");
                }

                JOptionPane.showMessageDialog(frame, sb.toString());

                rs.close();
                st.close();
                conn.close();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(frame, "Error fetching data: " + se.getMessage());
            }
        });

        frame.setVisible(true);
    }
}
