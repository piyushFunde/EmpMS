package OOPS.Project;

import javax.swing.*;
import java.sql.*;
import java.awt.*;

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
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();

            if (name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
                return;
            }

            try {
                int age = Integer.parseInt(ageText);

                Connection conn = DBhelper.getConnection();
                String query = "INSERT INTO employee(name, age) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Employee Added Successfully!");

                nameField.setText("");
                ageField.setText("");

                ps.close();
                conn.close();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Age must be a number!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        });

        // 👀 View All button action
       viewBtn.addActionListener(e -> {
            try {
                Connection conn = DBhelper.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM employee");

                StringBuilder data = new StringBuilder();
                while (rs.next()) {
                    data.append("ID: ").append(rs.getInt("id"))
                        .append(" | Name: ").append(rs.getString("name"))
                        .append(" | Age: ").append(rs.getInt("age"))
                        .append("\n");
                }

                JOptionPane.showMessageDialog(frame, data.toString());

                rs.close();
                st.close();
                conn.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error Fetching Data!");
            }
        });

        frame.setVisible(true);
    }
}
