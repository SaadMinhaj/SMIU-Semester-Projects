package gui;

import main.StudentManagementSystem;
import model.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private StudentManagementSystem sms;
    
    // GUI Components
    private JTabbedPane tabbedPane;
    private JPanel studentPanel;
    private JPanel adminPanel;
    private JTextField studentRollField;
    private JPasswordField studentPasswordField;
    private JTextField adminUsernameField;
    private JPasswordField adminPasswordField;
    private JButton studentLoginButton;
    private JButton studentRegisterButton;
    private JButton adminLoginButton;
    private JTextArea consoleArea;

    JLabel studentIcon = new JLabel(new ImageIcon("path/to/studentIcon.png"));
    JLabel studentTitle = new JLabel("Student Login");
    JLabel studentRollLabel = new JLabel("Roll No:");
    JLabel studentPasswordLabel = new JLabel("Password:");

    JLabel adminIcon = new JLabel(new ImageIcon("path/to/adminIcon.png"));
    JLabel adminTitle = new JLabel("Admin Login");
    JLabel adminUsernameLabel = new JLabel("Username:");
    JLabel adminPasswordLabel = new JLabel("Password:");

    // Modern Color Scheme
//    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
//    private static final Color SECONDARY_COLOR = new Color(52, 73, 94);
//    private static final Color ACCENT_COLOR = new Color(231, 76, 60);
//    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
//    private static final Color WARNING_COLOR = new Color(241, 196, 15);
//    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
//    private static final Color PANEL_COLOR = Color.WHITE;
//    private static final Color TEXT_COLOR = new Color(44, 62, 80);
//    private static final Color BORDER_COLOR = new Color(189, 195, 199);

    // Modern Color Scheme
    private static final Color PRIMARY_COLOR = new Color(38, 166, 154);  // Teal
    private static final Color SECONDARY_COLOR = new Color(41, 50, 65);   // Charcoal Gray
    private static final Color ACCENT_COLOR = new Color(253, 121, 68);    // Vibrant Orange
    private static final Color SUCCESS_COLOR = new Color(34, 193, 195);   // Emerald Green
    private static final Color WARNING_COLOR = new Color(255, 182, 47);   // Amber Yellow
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);  // Light Gray
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 33, 33);        // Dark Gray
    private static final Color BORDER_COLOR = new Color(206, 212, 218);   // Light Gray

    public LoginFrame() {
        this.sms = StudentManagementSystem.getInstance();
        
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not set system look and feel");
        }
        
        // Set background color
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        initComponents();
        layoutComponents();
        
        // Redirect console output to text area
        redirectConsoleOutput();
    }
    
    private void initComponents() {
        // Modern Fonts
        Font titleFont = new Font("SansSerif", Font.BOLD, 28);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        
        // Student Login Panel
        studentPanel = new JPanel(new GridBagLayout());
        studentPanel.setBackground(PANEL_COLOR);
        studentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_COLOR),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        
        // Student Panel Title
        JLabel studentTitle = new JLabel("Student Portal");
        studentTitle.setFont(titleFont);
        studentTitle.setForeground(Color.BLACK);
        studentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Student Icon
        JLabel studentIcon = new JLabel("🎓");
        studentIcon.setFont(new Font("SansSerif", Font.PLAIN, 48));
        studentIcon.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Student Roll Number
        JLabel studentRollLabel = new JLabel("Roll Number");
        studentRollLabel.setFont(labelFont);
        studentRollLabel.setForeground(Color.BLACK);

        studentRollField = new JTextField(20);
        studentRollField.setFont(fieldFont);
        studentRollField.setForeground(Color.BLACK);     // ✅ TEXT COLOR
        studentRollField.setCaretColor(Color.BLACK);     // ✅ CURSOR COLOR
        studentRollField.setBackground(Color.WHITE);
        studentRollField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        // Student Password
        JLabel studentPasswordLabel = new JLabel("Password");
        studentPasswordLabel.setFont(labelFont);
        studentPasswordLabel.setForeground(Color.BLACK);

        studentPasswordField = new JPasswordField(20);
        studentPasswordField.setFont(fieldFont);
        studentPasswordField.setForeground(Color.BLACK);
        studentPasswordField.setCaretColor(Color.BLACK);
        studentPasswordField.setBackground(Color.WHITE);
        studentPasswordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Student Buttons
        studentLoginButton = new JButton("Sign In");
        studentLoginButton.setFont(buttonFont);
        studentLoginButton.setBackground(PRIMARY_COLOR);
        studentLoginButton.setForeground(Color.WHITE);
        studentLoginButton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        studentLoginButton.setFocusPainted(false);
        studentLoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        studentRegisterButton = new JButton("Register");
        studentRegisterButton.setFont(buttonFont);
        studentRegisterButton.setBackground(SECONDARY_COLOR);
        studentRegisterButton.setForeground(Color.WHITE);
        studentRegisterButton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        studentRegisterButton.setFocusPainted(false);
        studentRegisterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Admin Login Panel
        adminPanel = new JPanel(new GridBagLayout());
        adminPanel.setBackground(PANEL_COLOR);
        adminPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Admin Panel Title
        JLabel adminTitle = new JLabel("Admin Portal");
        adminTitle.setFont(titleFont);
        adminTitle.setForeground(ACCENT_COLOR);
        adminTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Admin Icon
        JLabel adminIcon = new JLabel("🔐");
        adminIcon.setFont(new Font("SansSerif", Font.PLAIN, 48));
        adminIcon.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Admin Username
        JLabel adminUsernameLabel = new JLabel("Username");
        adminUsernameLabel.setFont(labelFont);
        adminUsernameLabel.setForeground(Color.BLACK);
        
        adminUsernameField = new JTextField(20);
        adminUsernameField.setFont(fieldFont);
        adminUsernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        adminUsernameField.setBackground(Color.WHITE);
        
        // Admin Password
        JLabel adminPasswordLabel = new JLabel("Password");
        adminPasswordLabel.setFont(labelFont);
        adminUsernameLabel.setForeground(Color.BLACK);
        
        adminPasswordField = new JPasswordField(20);
        adminPasswordField.setFont(fieldFont);
        adminPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        adminPasswordField.setBackground(Color.WHITE);
        
        // Admin Button
        adminLoginButton = new JButton("Sign In");
        adminLoginButton.setFont(buttonFont);
        adminLoginButton.setBackground(ACCENT_COLOR);
        adminLoginButton.setForeground(Color.WHITE);
        adminLoginButton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        adminLoginButton.setFocusPainted(false);
        adminLoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 16));
        tabbedPane.setBackground(BACKGROUND_COLOR);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        // Console Area for DSA logging
        consoleArea = new JTextArea(8, 50);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        consoleArea.setEditable(false);
        consoleArea.setBackground(new Color(30, 30, 30));
        consoleArea.setForeground(new Color(0, 255, 0));
        
        // Action Listeners
        studentLoginButton.addActionListener(e -> handleStudentLogin());
        studentRegisterButton.addActionListener(e -> handleStudentRegister());
        adminLoginButton.addActionListener(e -> handleAdminLogin());
    }
    
    private void layoutComponents() {
        // Student Panel Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        
        gbc.gridy = 0;
        studentPanel.add(studentIcon, gbc);
        
        gbc.gridy = 1;
        studentPanel.add(studentTitle, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        studentPanel.add(studentRollLabel, gbc);
        
        gbc.gridx = 1;
        studentPanel.add(studentRollField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        studentPanel.add(studentPasswordLabel, gbc);
        
        gbc.gridx = 1;
        studentPanel.add(studentPasswordField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(PANEL_COLOR);
        buttonPanel.add(studentLoginButton);
        buttonPanel.add(studentRegisterButton);
        studentPanel.add(buttonPanel, gbc);
        
        // Admin Panel Layout
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        
        gbc.gridy = 0;
        adminPanel.add(adminIcon, gbc);
        
        gbc.gridy = 1;
        adminPanel.add(adminTitle, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        adminPanel.add(adminUsernameLabel, gbc);
        
        gbc.gridx = 1;
        adminPanel.add(adminUsernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        adminPanel.add(adminPasswordLabel, gbc);
        
        gbc.gridx = 1;
        adminPanel.add(adminPasswordField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        adminPanel.add(adminLoginButton, gbc);
        
        // Add panels to tabbed pane
        tabbedPane.addTab("Student Login", new JScrollPane(studentPanel));
        tabbedPane.addTab("Admin Login", new JScrollPane(adminPanel));
        
        // Main Layout
        setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Student Management System");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel subtitleLabel = new JLabel("Data Structures & Algorithms Project");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(200, 200, 200));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
        
        // Main content
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContent.add(tabbedPane, BorderLayout.CENTER);
        
        // Console Panel
        JPanel consolePanel = new JPanel(new BorderLayout());
        consolePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            "DSA Operations Log",
            0,
            0,
            new Font("SansSerif", Font.BOLD, 14),
            TEXT_COLOR
        ));
        consolePanel.setBackground(PANEL_COLOR);
        consolePanel.add(new JScrollPane(consoleArea), BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
        add(consolePanel, BorderLayout.SOUTH);
    }
    
    private void handleStudentLogin() {
        String rollNumber = studentRollField.getText().trim();
        String password = new String(studentPasswordField.getPassword());
        
        if (rollNumber.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both roll number and password", 
                                        "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (sms.authenticateStudent(rollNumber, password)) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome " + 
                                        sms.getLoggedInStudent().getName(), 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            sms.showStudentDashboard();
        } else {
            Student student = sms.getStudentArray().findByRollNumber(rollNumber);
            if (student != null && !student.isApproved()) {
                JOptionPane.showMessageDialog(this, "Your registration is pending approval. Please wait for admin approval.", 
                                            "Not Approved", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid roll number or password!", 
                                            "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void handleStudentRegister() {
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
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Student Registration", 
                                                 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String rollNumber = rollField.getText().trim();
                String name = nameField.getText().trim();
                double marks = Double.parseDouble(marksField.getText().trim());
                String password = new String(passwordField.getPassword());
                
                if (rollNumber.isEmpty() || name.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", 
                                                "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100!", 
                                                "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (sms.registerStudent(rollNumber, name, marks, password)) {
                    JOptionPane.showMessageDialog(this, "Registration request submitted successfully!\n" +
                                                "Please wait for admin approval.", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed! Student with this roll number may already exist or queue is full.", 
                                                "Registration Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks!", 
                                            "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void handleAdminLogin() {
        String username = adminUsernameField.getText().trim();
        String password = new String(adminPasswordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", 
                                        "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (sms.authenticateAdmin(username, password)) {
            JOptionPane.showMessageDialog(this, "Admin login successful!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            sms.showAdminDashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid admin credentials!", 
                                        "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
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