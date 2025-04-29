// File: SAPPortal/src/main/java/com/pranav/sportal/gui/MainPanel.java
package com.pranav.sportal.gui;

import com.pranav.sportal.db.DatabaseConnection;
import com.pranav.sportal.model.Student;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainPanel extends JFrame {
    private JTextField nameField;
    private JTextField sapField;
    private JTextField contactField;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton truncateButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public MainPanel() {
        setTitle("SAP Portal");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel sapLabel = new JLabel("SAP ID:");
        sapField = new JTextField();
        JLabel contactLabel = new JLabel("Contact:");
        contactField = new JTextField();

        saveButton = new JButton("Save Student");
        saveButton.addActionListener(e -> saveStudent());

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(sapLabel);
        formPanel.add(sapField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.NORTH);

        // Table to view students
        tableModel = new DefaultTableModel(new String[]{"Select", "Name", "SAP ID", "Contact"}, 0);
        studentTable = new JTable(tableModel);
        studentTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Buttons to delete and truncate records
        JPanel buttonPanel = new JPanel();
        deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> deleteSelectedStudents());
        truncateButton = new JButton("Truncate Table");
        truncateButton.addActionListener(e -> truncateTable());

        buttonPanel.add(deleteButton);
        buttonPanel.add(truncateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadStudents();
    }

    private void saveStudent() {
        String name = nameField.getText();
        String sap = sapField.getText();
        String contact = contactField.getText();

        Student student = new Student(name, sap, contact);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO student (name, sap, contact) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getSap());
            stmt.setString(3, student.getContact());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student Saved Successfully!");
            loadStudents();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadStudents() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {

            tableModel.setRowCount(0);  // Clear the table before loading data
            while (rs.next()) {
                tableModel.addRow(new Object[]{false, rs.getString("name"), rs.getString("sap"), rs.getString("contact")});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteSelectedStudents() {
        for (int i = 0; i < studentTable.getRowCount(); i++) {
            Boolean isSelected = (Boolean) studentTable.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                String sap = (String) studentTable.getValueAt(i, 2);
                deleteStudentBySap(sap);
            }
        }
        loadStudents();
    }

    private void deleteStudentBySap(String sap) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM student WHERE sap = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sap);
            stmt.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void truncateTable() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "TRUNCATE TABLE student";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Table Truncated Successfully!");
            loadStudents();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPanel frame = new MainPanel();
            frame.setVisible(true);
        });
    }
}
