package gui;

import main.StudentManagementSystem;
import model.Student;
import dsa.StudentArray;
import dsa.RegistrationQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.JTableHeader;


public class AdminDashboard extends JFrame {
    private StudentManagementSystem sms;

    // Modern Color Scheme
//    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
//    private static final Color SECONDARY_COLOR = new Color(52, 73, 94);
//    private static final Color ACCENT_COLOR = new Color(231, 76, 60);
//    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
//    private static final Color WARNING_COLOR = new Color(241, 196, 15);
//    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
//    private static final Color PANEL_COLOR = Color.WHITE;
//    private static final Color TEXT_COLOR = new Color(44, 62, 80);
//    private static final Color TEXT_COLOR = new Color(0, 0, 0);
//    private static final Color BORDER_COLOR = new Color(189, 195, 199);

    // Modern Color Scheme
    private static final Color PRIMARY_COLOR = new Color(38, 166, 154);  // Teal
    private static final Color SECONDARY_COLOR = new Color(41, 50, 65);   // Charcoal Gray
    private static final Color ACCENT_COLOR = new Color(253, 121, 68);    // Vibrant Orange
    private static final Color SUCCESS_COLOR = new Color(34, 193, 195);   // Emerald Green
    private static final Color WARNING_COLOR = new Color(255, 182, 47);   // Amber Yellow
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);  // Light Gray
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 33, 33);        // Dark Black
    private static final Color BORDER_COLOR = new Color(206, 212, 218);   // Light Gray

    // GUI Components
//    private JPanel headerPanel;
//    private JPanel titleLabel;
    private JTabbedPane tabbedPane;
    private JPanel studentManagementPanel;
    private JPanel registrationQueuePanel;
    private JPanel algorithmsPanel;
    private JPanel reportsPanel;
//    private JButton logoutButton;

    // Student Management Components
    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;

    // Registration Queue Components
    private JTable queueTable;
    private DefaultTableModel queueTableModel;
    private JButton approveButton;
    private JButton rejectButton;
    private JButton refreshQueueButton;

    // Algorithms Components
    private JButton searchButton;
    private JButton sortButton;

    // Reports Components
    private JTextArea reportArea;
    private JButton generateReportButton;

    // Console
    private JTextArea consoleArea;

    public AdminDashboard() {
        this.sms = StudentManagementSystem.getInstance();

        setTitle("Admin Dashboard - Student Management System");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set background color
        getContentPane().setBackground(BACKGROUND_COLOR);

        initComponents();
        layoutComponents();
        loadStudentData();
        loadQueueData();

        // Redirect console output
        redirectConsoleOutput();
    }

    private void initComponents() {
        // Modern Fonts
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font tableFont = new Font("Segoe UI", Font.PLAIN, 13);

//        logoutButton = new JButton("Logout");
//        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        logoutButton.setBackground(ACCENT_COLOR); // Orange / Red
//        logoutButton.setForeground(Color.WHITE);
//        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
//        logoutButton.setFocusPainted(false);
//        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Action
//        logoutButton.addActionListener(e -> handleLogout());

        // Student Management Panel
        studentManagementPanel = new JPanel(new BorderLayout());
        studentManagementPanel.setBackground(PANEL_COLOR);
        studentManagementPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Student Table
        String[] studentColumns = {"Roll Number", "Name", "Marks", "Status"};
        studentTableModel = new DefaultTableModel(studentColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(studentTableModel);
        studentTable.setFont(tableFont);
        studentTable.setRowHeight(30);
        studentTable.setGridColor(BORDER_COLOR);
        studentTable.setSelectionBackground(PRIMARY_COLOR);
        studentTable.setSelectionForeground(Color.WHITE);
        studentTable.setShowGrid(true);
        studentTable.setShowHorizontalLines(true);
        studentTable.setShowVerticalLines(false);

        // Table Header
        JTableHeader tableHeader = studentTable.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(PRIMARY_COLOR);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR));

        // Student Management Buttons
        addButton = createModernButton("Add Student", SUCCESS_COLOR);
        updateButton = createModernButton("Update Student", WARNING_COLOR);
        deleteButton = createModernButton("Delete Student", ACCENT_COLOR);
        refreshButton = createModernButton("Refresh", PRIMARY_COLOR);

        // Registration Queue Panel
        registrationQueuePanel = new JPanel(new BorderLayout());
        registrationQueuePanel.setBackground(PANEL_COLOR);
        registrationQueuePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Queue Table
        String[] queueColumns = {"Roll Number", "Name", "Marks", "Status"};
        queueTableModel = new DefaultTableModel(queueColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        queueTable = new JTable(queueTableModel);
        queueTable.setFont(tableFont);
        queueTable.setRowHeight(30);
        queueTable.setGridColor(BORDER_COLOR);
        queueTable.setSelectionBackground(PRIMARY_COLOR);
        queueTable.setSelectionForeground(Color.WHITE);

        // Queue Table Header
        JTableHeader queueHeader = queueTable.getTableHeader();
        queueHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        queueHeader.setBackground(ACCENT_COLOR);
        queueHeader.setForeground(Color.WHITE);
        queueHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT_COLOR));

        // Queue Buttons
        approveButton = createModernButton("Approve Selected", SUCCESS_COLOR);
        rejectButton = createModernButton("Reject Selected", ACCENT_COLOR);
        refreshQueueButton = createModernButton("Refresh Queue", PRIMARY_COLOR);

        // Algorithms Panel
        algorithmsPanel = new JPanel(new GridBagLayout());
        algorithmsPanel.setBackground(PANEL_COLOR);
        algorithmsPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        searchButton = createModernButton("🔍 Search Students", PRIMARY_COLOR);
        sortButton = createModernButton("📊 Sort Students", SECONDARY_COLOR);

        // Reports Panel
        reportsPanel = new JPanel(new BorderLayout());
        reportsPanel.setBackground(PANEL_COLOR);
        reportsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        reportArea = new JTextArea();
        reportArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        reportArea.setEditable(false);
        reportArea.setBackground(Color.WHITE);
        reportArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        generateReportButton = createModernButton("📈 Generate Report", PRIMARY_COLOR);

        // Console Area for DSA logging
        consoleArea = new JTextArea(8, 50);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        consoleArea.setEditable(false);
        consoleArea.setBackground(new Color(30, 30, 30));
        consoleArea.setForeground(new Color(0, 255, 0));
        consoleArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Action Listeners
        addButton.addActionListener(e -> handleAddStudent());
        updateButton.addActionListener(e -> handleUpdateStudent());
        deleteButton.addActionListener(e -> handleDeleteStudent());
        refreshButton.addActionListener(e -> loadStudentData());

        approveButton.addActionListener(e -> handleApproveRegistration());
        rejectButton.addActionListener(e -> handleRejectRegistration());
        refreshQueueButton.addActionListener(e -> loadQueueData());

        searchButton.addActionListener(e -> openSearchDialog());
        sortButton.addActionListener(e -> openSortDialog());

        generateReportButton.addActionListener(e -> generateReport());
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, bgColor.darker()),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }

    private void layoutComponents() {
        // Student Management Layout
        JPanel studentButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
//        headerPanel.add(titleLabel, BorderLayout.WEST);
//        headerPanel.add(logoutButton, BorderLayout.EAST);
        studentButtonPanel.setBackground(PANEL_COLOR);
        studentButtonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));
        studentButtonPanel.add(addButton);
        studentButtonPanel.add(updateButton);
        studentButtonPanel.add(deleteButton);
        studentButtonPanel.add(refreshButton);

        studentManagementPanel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        studentManagementPanel.add(studentButtonPanel, BorderLayout.SOUTH);

        // Registration Queue Layout
        JPanel queueButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        queueButtonPanel.setBackground(PANEL_COLOR);
        queueButtonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));
        queueButtonPanel.add(approveButton);
        queueButtonPanel.add(rejectButton);
        queueButtonPanel.add(refreshQueueButton);

        registrationQueuePanel.add(new JScrollPane(queueTable), BorderLayout.CENTER);
        registrationQueuePanel.add(queueButtonPanel, BorderLayout.SOUTH);

        // Algorithms Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        algorithmsPanel.add(searchButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        algorithmsPanel.add(sortButton, gbc);

        // Reports Layout
        JPanel reportButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        reportButtonPanel.setBackground(PANEL_COLOR);
        reportButtonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));
        reportButtonPanel.add(generateReportButton);

        reportsPanel.add(reportButtonPanel, BorderLayout.NORTH);
        reportsPanel.add(new JScrollPane(reportArea), BorderLayout.CENTER);

        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tabbedPane.setBackground(BACKGROUND_COLOR);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Add icons to tabs
        tabbedPane.addTab("Student Management", studentManagementPanel);
        tabbedPane.addTab("Registration Queue", registrationQueuePanel);
        tabbedPane.addTab("Algorithms", algorithmsPanel);
        tabbedPane.addTab("Reports", reportsPanel);

        // Main Layout
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Main content with padding
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContent.add(tabbedPane, BorderLayout.CENTER);

        // Console Panel
        JPanel consolePanel = new JPanel(new BorderLayout());
        consolePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
                "DSA Operations Log",
                0,
                0,
                new Font("Segoe UI", Font.BOLD, 14),
                TEXT_COLOR
        ));
        consolePanel.setBackground(PANEL_COLOR);
        consolePanel.add(new JScrollPane(consoleArea), BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
        add(consolePanel, BorderLayout.SOUTH);
    }

    private void loadStudentData() {
        studentTableModel.setRowCount(0);
        StudentArray students = sms.getStudentArray();

        for (int i = 0; i < students.getSize(); i++) {
            Student student = students.get(i);
            Object[] row = {
                    student.getRollNumber(),
                    student.getName(),
                    student.getMarks(),
                    student.isApproved() ? "Approved" : "Pending"
            };
            studentTableModel.addRow(row);
        }

        System.out.println("[DSA] Loaded " + students.getSize() + " students into table");
    }

    private void loadQueueData() {
        queueTableModel.setRowCount(0);
        RegistrationQueue queue = sms.getRegistrationQueue();
        Student[] pendingStudents = queue.getAll();

        for (Student student : pendingStudents) {
            if (student != null) {
                Object[] row = {
                        student.getRollNumber(),
                        student.getName(),
                        student.getMarks(),
                        "Pending"
                };
                queueTableModel.addRow(row);
            }
        }

        System.out.println("[DSA] Loaded " + pendingStudents.length + " pending registrations into table");
    }

    private void handleAddStudent() {
        JTextField rollField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField marksField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Roll Number:"));
        panel.add(rollField);
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Marks (0-100):"));
        panel.add(marksField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String rollNumber = rollField.getText().trim();
                String name = nameField.getText().trim();
                double marks = Double.parseDouble(marksField.getText().trim());
                String password = new String(passwordField.getPassword());

                if (rollNumber.isEmpty() || name.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create and add student directly (approved)
                Student newStudent = new Student(rollNumber, name, marks, password);
                newStudent.setApproved(true);
                sms.getStudentArray().insert(newStudent);

                JOptionPane.showMessageDialog(this, "Student added successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadStudentData();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleUpdateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rollNumber = (String) studentTableModel.getValueAt(selectedRow, 0);
        Student student = sms.getStudentArray().findByRollNumber(rollNumber);

        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student not found!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField nameField = new JTextField(student.getName());
        JTextField marksField = new JTextField(String.valueOf(student.getMarks()));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Marks (0-100):"));
        panel.add(marksField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String newName = nameField.getText().trim();
                double newMarks = Double.parseDouble(marksField.getText().trim());

                if (newName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name cannot be empty!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (newMarks < 0 || newMarks > 100) {
                    JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                sms.getStudentArray().update(rollNumber, newName, newMarks);
                JOptionPane.showMessageDialog(this, "Student updated successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadStudentData();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDeleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rollNumber = (String) studentTableModel.getValueAt(selectedRow, 0);
        String name = (String) studentTableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student '" + name + "'?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            if (sms.getStudentArray().delete(rollNumber)) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete student!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleApproveRegistration() {
        int selectedRow = queueTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a registration request to approve!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = (String) queueTableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to approve registration for '" + name + "'?",
                "Confirm Approval",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (sms.approveRegistration()) {
                JOptionPane.showMessageDialog(this, "Registration approved successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadQueueData();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to approve registration!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleRejectRegistration() {
        int selectedRow = queueTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a registration request to reject!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = (String) queueTableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to reject registration for '" + name + "'?",
                "Confirm Rejection",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (sms.rejectRegistration()) {
                JOptionPane.showMessageDialog(this, "Registration rejected successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                loadQueueData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to reject registration!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openSearchDialog() {
        SearchDialog searchDialog = new SearchDialog(this, sms);
        searchDialog.setVisible(true);
    }

    private void openSortDialog() {
        SortDialog sortDialog = new SortDialog(this, sms);
        sortDialog.setVisible(true);
    }

    private void generateReport() {
        StudentArray students = sms.getStudentArray();
        RegistrationQueue queue = sms.getRegistrationQueue();

        StringBuilder report = new StringBuilder();
        report.append("STUDENT MANAGEMENT SYSTEM REPORT\n");
        report.append("==================================\n\n");

        // Statistics
        int totalStudents = students.getSize();
        int approvedStudents = 0;
        int pendingStudents = queue.getSize();
        double totalMarks = 0;
        double maxMarks = Double.MIN_VALUE;
        String topScorer = "";

        for (int i = 0; i < students.getSize(); i++) {
            Student student = students.get(i);
            if (student.isApproved()) {
                approvedStudents++;
                totalMarks += student.getMarks();

                if (student.getMarks() > maxMarks) {
                    maxMarks = student.getMarks();
                    topScorer = student.getName();
                }
            }
        }

        double averageMarks = (approvedStudents > 0) ? totalMarks / approvedStudents : 0;

        report.append("STATISTICS\n");
        report.append("----------\n");
        report.append(String.format("Total Students: %d\n", totalStudents));
        report.append(String.format("Approved Students: %d\n", approvedStudents));
        report.append(String.format("Pending Registrations: %d\n", pendingStudents));
        report.append(String.format("Average Marks: %.2f\n", averageMarks));
        report.append(String.format("Top Scorer: %s (%.2f marks)\n\n", topScorer, maxMarks));

        // Data Structure Information
        report.append("DATA STRUCTURES STATUS\n");
        report.append("----------------------\n");
        report.append(String.format("StudentArray: size=%d, capacity=%d\n",
                students.getSize(), students.getCapacity()));
        report.append(String.format("RegistrationQueue: size=%d, capacity=%d, front=%d, rear=%d\n\n",
                queue.getSize(), queue.getCapacity(), queue.getFront(), queue.getRear()));

        // All Students
        report.append("ALL STUDENTS\n");
        report.append("------------\n");
        for (int i = 0; i < students.getSize(); i++) {
            Student student = students.get(i);
            report.append(String.format("%d. %s - %s (%.2f) - %s\n",
                    i + 1,
                    student.getRollNumber(),
                    student.getName(),
                    student.getMarks(),
                    student.isApproved() ? "Approved" : "Pending"));
        }

        reportArea.setText(report.toString());
        System.out.println("[DSA] Report generated successfully");
    }

//    private void handleLogout() {
//        int confirm = JOptionPane.showConfirmDialog(
//                this,
//                "Are you sure you want to logout?",
//                "Confirm Logout",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE
//        );
//
//        if (confirm == JOptionPane.YES_OPTION) {
//            sms.logout();
//            dispose();
//            sms.showLogin();
//        }
//    }

    private void redirectConsoleOutput() {
        ConsoleOutputStream cos = new ConsoleOutputStream(consoleArea);
        System.setOut(new java.io.PrintStream(cos));
        System.setErr(new java.io.PrintStream(cos));
    }

    // Custom OutputStream to redirect console output to JTextArea
    private class ConsoleOutputStream extends java.io.OutputStream {
        private JTextArea textArea;

        public ConsoleOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        }

        @Override
        public void write(byte[] b, int off, int len) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(new String(b, off, len));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        }
    }
}