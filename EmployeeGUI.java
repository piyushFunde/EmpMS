package OOPS.Project;

import javax.swing.*;
import java.sql.*;
import java.awt.*;

public class EmployeeGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Management System");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center window

        // Main panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 248, 250));
        panel.setLayout(new BorderLayout(10, 10));
        frame.add(panel);

        // Title
        JLabel title = new JLabel("Employee Manager", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(33, 37, 41));
        panel.add(title, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(new Color(245, 248, 250));

        JLabel nameLabel = new JLabel("Employee Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Employee Age:");
        JTextField ageField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(ageLabel);
        formPanel.add(ageField);

        panel.add(formPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));

        JButton insertBtn = new JButton("Insert");
        JButton viewBtn = new JButton("View All");

        insertBtn.setBackground(new Color(40, 167, 69));
        insertBtn.setForeground(Color.WHITE);
        insertBtn.setFocusPainted(false);

        viewBtn.setBackground(new Color(0, 123, 255));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setFocusPainted(false);

        buttonPanel.add(insertBtn);
        buttonPanel.add(viewBtn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

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
