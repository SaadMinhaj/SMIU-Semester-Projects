package gui;

import main.StudentManagementSystem;
import model.Student;
import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {
    private StudentManagementSystem sms;
    private Student loggedInStudent;
    
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
    private static final Color TEXT_COLOR = new Color(0, 0, 0);        // Dark Gray
    private static final Color BORDER_COLOR = new Color(206, 212, 218);   // Light Gray

    // GUI Components
    private JPanel headerPanel;
    private JPanel infoPanel;
    private JPanel sidebarPanel;
    private JButton logoutButton;
    private JTextArea consoleArea;
    private JLabel welcomeLabel;
    private JLabel studentCard;
    
    public StudentDashboard() {
        this.sms = StudentManagementSystem.getInstance();
        this.loggedInStudent = sms.getLoggedInStudent();
        
        setTitle("Student Dashboard - " + loggedInStudent.getName());
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
        
        // Redirect console output
        redirectConsoleOutput();
    }
    
    private void initComponents() {
        // Modern Fonts
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font subtitleFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        
        // Header Panel
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        welcomeLabel = new JLabel("Welcome, Student!");
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(Color.WHITE);
        
        logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.setBackground(ACCENT_COLOR);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Sidebar Panel
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(SECONDARY_COLOR);
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        sidebarPanel.setPreferredSize(new Dimension(250, 0));
        
        // Sidebar Title
        JLabel sidebarTitle = new JLabel("Navigation");
        sidebarTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sidebarTitle.setForeground(Color.WHITE);
        sidebarTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Sidebar Items
        JLabel dashboardItem = createSidebarItem("Dashboard", true);
        JLabel profileItem = createSidebarItem("Profile", false);
        JLabel marksItem = createSidebarItem("Marks", false);
        JLabel statusItem = createSidebarItem("Status", false);
        
        // Student Info Panel
        infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(PANEL_COLOR);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        // Student Card
        studentCard = new JLabel();
        studentCard.setFont(new Font("Segoe UI", Font.BOLD, 18));
        studentCard.setForeground(TEXT_COLOR);
        studentCard.setHorizontalAlignment(SwingConstants.CENTER);
        studentCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, PRIMARY_COLOR),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        studentCard.setBackground(new Color(40, 40, 40));
        studentCard.setForeground(Color.WHITE);
        studentCard.setOpaque(true);
        
        // Console Area for DSA logging
        consoleArea = new JTextArea(10, 50);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        consoleArea.setEditable(false);
        consoleArea.setBackground(new Color(30, 30, 30));
        consoleArea.setForeground(new Color(0, 255, 0));
        consoleArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Action Listeners
        logoutButton.addActionListener(e -> handleLogout());
        
        // Add components to sidebar
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(sidebarTitle);
        sidebarPanel.add(Box.createVerticalStrut(30));
        sidebarPanel.add(dashboardItem);
        sidebarPanel.add(Box.createVerticalStrut(15));
        sidebarPanel.add(profileItem);
        sidebarPanel.add(Box.createVerticalStrut(15));
        sidebarPanel.add(marksItem);
        sidebarPanel.add(Box.createVerticalStrut(15));
        sidebarPanel.add(statusItem);
        sidebarPanel.add(Box.createVerticalGlue());
    }

    private JLabel createSidebarItem(String text, boolean active) {
        JLabel item = new JLabel(text);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        item.setForeground(active ? Color.WHITE : new Color(180, 180, 180));
        item.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        item.setOpaque(true);
        item.setBackground(active ? PRIMARY_COLOR : new Color(0, 0, 0, 0)); // ✅ FIX
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return item;
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Header Layout
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        // Main Content Layout
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Info Panel Content
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        infoPanel.add(studentCard, gbc);
        
        // Add info panel to center
        mainContent.add(infoPanel, BorderLayout.CENTER);
        
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
        
        mainContent.add(consolePanel, BorderLayout.SOUTH);
        
        // Add all components to frame
        add(headerPanel, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);
    }
    
    private void loadStudentData() {
        if (loggedInStudent != null) {
            welcomeLabel.setText("Welcome, " + loggedInStudent.getName());
            
            // Create student card content
            StringBuilder cardContent = new StringBuilder();
            cardContent.append("<html><center>");
            cardContent.append("<div style='font-size: 24px; margin-bottom: 20px;'>👤</div>");
            cardContent.append("<div style='font-size: 20px; font-weight: bold; margin-bottom: 10px;'>").append(loggedInStudent.getName()).append("</div>");
            cardContent.append("<div style='font-size: 14px; color: #666; margin-bottom: 5px;'>Roll Number: <strong>").append(loggedInStudent.getRollNumber()).append("</strong></div>");
            cardContent.append("<div style='font-size: 14px; color: #666; margin-bottom: 5px;'>Marks: <strong>").append(loggedInStudent.getMarks()).append("</strong></div>");
            
            String status = loggedInStudent.isApproved() ? "Approved" : "Pending";
            String statusColor = loggedInStudent.isApproved() ? SUCCESS_COLOR.toString() : WARNING_COLOR.toString();
            cardContent.append("<div style='font-size: 14px; color: ").append(statusColor).append("; margin-top: 10px; padding: 5px 15px; border-radius: 15px; background: #f0f0f0;'>").append(status).append("</div>");
            cardContent.append("</center></html>");
            
            studentCard.setText(cardContent.toString());
        }
    }
    
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", 
                                                  "Confirm Logout", JOptionPane.YES_NO_OPTION, 
                                                  JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            sms.logout();
            dispose();
            sms.showLogin();
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