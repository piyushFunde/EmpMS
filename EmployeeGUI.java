package OOPS;

import OOPS.EmployeeDAO;
import OOPS.Employee;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeGUI extends JFrame {
    private JTextField nameField, ageField, salaryField;
    private JTable table;
    private DefaultTableModel tableModel;
    private EmployeeDAO dao = new EmployeeDAO();

    public EmployeeGUI() {
        setTitle("Employee Management System");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Header ---
        JLabel title = new JLabel("Employee Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(41, 128, 185));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // --- Input Panel ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);

        JButton addBtn = new JButton("Add Employee");
        addBtn.setBackground(new Color(46, 204, 113));
        addBtn.addActionListener(e -> handleAddEmployee());
        inputPanel.add(addBtn);
        
        add(inputPanel, BorderLayout.WEST);

        // --- Table Panel ---
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Salary"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Bottom Panel (Actions) ---
        JPanel actionPanel = new JPanel();
        JButton viewBtn = new JButton("Refresh List");
        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBackground(new Color(231, 76, 60));
        deleteBtn.setForeground(Color.WHITE);

        viewBtn.addActionListener(e -> loadData());
        deleteBtn.addActionListener(e -> handleDelete());

        actionPanel.add(viewBtn);
        actionPanel.add(deleteBtn);
        add(actionPanel, BorderLayout.SOUTH);

        loadData();
        setVisible(true);
    }

    private void handleAddEmployee() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double salary = Double.parseDouble(salaryField.getText());

            if (name.isEmpty() || salary <= 0) {
                JOptionPane.showMessageDialog(this, "Valid Name and Positive Salary required!");
                return;
            }

            if (dao.addEmployee(new Employee(name, age, salary))) {
                JOptionPane.showMessageDialog(this, "Success!");
                loadData();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadData() {
        try {
            tableModel.setRowCount(0);
            List<Employee> list = dao.getAllEmployees();
            for (Employee e : list) {
                tableModel.addRow(new Object[]{e.getId(), e.getName(), e.getAge(), e.getSalary()});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleDelete() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) tableModel.getValueAt(row, 0);
            try {
                dao.deleteEmployee(id);
                loadData();
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
}
