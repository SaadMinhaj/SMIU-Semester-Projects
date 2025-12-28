package gui;

import main.StudentManagementSystem;
import model.Student;
import dsa.StudentArray;
import dsa.Searching;
import dsa.Searching.SearchResult;
import dsa.Searching.SearchStep;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.table.JTableHeader;


public class SearchDialog extends JDialog {
    private StudentManagementSystem sms;
    
    // Modern Color Scheme
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 73, 94);
    private static final Color ACCENT_COLOR = new Color(231, 76, 60);
    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(44, 62, 80);
    private static final Color BORDER_COLOR = new Color(189, 195, 199);
    
    // GUI Components
    private JComboBox<String> searchTypeCombo;
    private JComboBox<String> algorithmCombo;
    private JTextField searchField;
    private JButton searchButton;
    private JTable resultTable;
    private DefaultTableModel resultTableModel;
    private JTextArea traceArea;
    private JLabel resultLabel;
    
    public SearchDialog(JFrame parent, StudentManagementSystem sms) {
        super(parent, "Search Students - DSA Visualization", true);
        this.sms = sms;
        
        setSize(1000, 700);
        setLocationRelativeTo(parent);
        
        // Apply modern colors
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        initComponents();
        layoutComponents();
    }
    
    private void initComponents() {
        // Modern Fonts
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font tableFont = new Font("Segoe UI", Font.PLAIN, 13);
        
        // Search Type Combo
        String[] searchTypes = {"By Roll Number", "By Name"};
        searchTypeCombo = new JComboBox<>(searchTypes);
        searchTypeCombo.setFont(fieldFont);
        searchTypeCombo.setBackground(PANEL_COLOR);
        searchTypeCombo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR));
        
        // Algorithm Combo
        String[] algorithms = {"Linear Search", "Binary Search"};
        algorithmCombo = new JComboBox<>(algorithms);
        algorithmCombo.setFont(fieldFont);
        algorithmCombo.setBackground(PANEL_COLOR);
        algorithmCombo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR));
        
        // Search Field
        searchField = new JTextField(20);
        searchField.setFont(fieldFont);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        searchField.setBackground(PANEL_COLOR);
        
        // Search Button
        searchButton = new JButton("🔍 Search");
        searchButton.setFont(buttonFont);
        searchButton.setBackground(PRIMARY_COLOR);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Result Table
        String[] columns = {"Index", "Roll Number", "Name", "Marks", "Status"};
        resultTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable = new JTable(resultTableModel);
        resultTable.setFont(tableFont);
        resultTable.setRowHeight(30);
        resultTable.setGridColor(BORDER_COLOR);
        resultTable.setSelectionBackground(PRIMARY_COLOR);
        resultTable.setSelectionForeground(Color.WHITE);
        resultTable.setShowGrid(true);
        resultTable.setShowHorizontalLines(true);
        resultTable.setShowVerticalLines(false);
        
        // Table Header
        JTableHeader tableHeader = resultTable.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(PRIMARY_COLOR);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR));
        
        // Trace Area
        traceArea = new JTextArea(15, 50);
        traceArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        traceArea.setEditable(false);
        traceArea.setBackground(new Color(30, 30, 30));
        traceArea.setForeground(new Color(0, 255, 0));
        traceArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Result Label
        resultLabel = new JLabel("Ready to search...");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setForeground(TEXT_COLOR);
        
        // Action Listeners
        searchButton.addActionListener(e -> performSearch());
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("🔍 Student Search - DSA Visualization");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Search Configuration Panel
        JPanel configPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        configPanel.setBackground(PANEL_COLOR);
        configPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        configPanel.add(new JLabel("Search Type:"));
        configPanel.add(searchTypeCombo);
        configPanel.add(Box.createHorizontalStrut(20));
        configPanel.add(new JLabel("Algorithm:"));
        configPanel.add(algorithmCombo);
        configPanel.add(Box.createHorizontalStrut(20));
        configPanel.add(new JLabel("Search Value:"));
        configPanel.add(searchField);
        configPanel.add(Box.createHorizontalStrut(20));
        configPanel.add(searchButton);
        
        // Result Panel
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(PANEL_COLOR);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        
        // Trace Panel
        JPanel tracePanel = new JPanel(new BorderLayout());
        tracePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
            "Algorithm Trace (DSA Operations)",
            0,
            0,
            new Font("Segoe UI", Font.BOLD, 14),
            TEXT_COLOR
        ));
        tracePanel.setBackground(PANEL_COLOR);
        tracePanel.add(new JScrollPane(traceArea), BorderLayout.CENTER);
        
        // Main Content
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, resultPanel, tracePanel);
        splitPane.setDividerLocation(300);
        splitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        mainContent.add(configPanel, BorderLayout.NORTH);
        mainContent.add(splitPane, BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
    }
    
    private void performSearch() {
        String searchValue = searchField.getText().trim();
        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search value!", 
                                        "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String searchType = (String) searchTypeCombo.getSelectedItem();
        String algorithm = (String) algorithmCombo.getSelectedItem();
        
        // Clear previous results
        resultTableModel.setRowCount(0);
        traceArea.setText("");
        
        StudentArray students = sms.getStudentArray();
        SearchResult result = null;
        
        try {
            if ("By Roll Number".equals(searchType)) {
                if ("Linear Search".equals(algorithm)) {
                    System.out.println("[DSA] === LINEAR SEARCH BY ROLL NUMBER ===");
                    result = Searching.linearSearchByRollNumber(students, searchValue);
                } else { // Binary Search
                    System.out.println("[DSA] === BINARY SEARCH BY ROLL NUMBER ===");
                    result = Searching.binarySearchByRollNumber(students, searchValue);
                }
            } else { // By Name
                if ("Linear Search".equals(algorithm)) {
                    System.out.println("[DSA] === LINEAR SEARCH BY NAME ===");
                    result = Searching.linearSearchByName(students, searchValue);
                } else { // Binary Search
                    System.out.println("[DSA] === BINARY SEARCH BY NAME ===");
                    result = Searching.binarySearchByName(students, searchValue);
                }
            }
            
            // Display results
            if (result != null) {
                displaySearchResult(result, searchType, algorithm);
                displayTrace(result.getSteps());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during search: " + e.getMessage(), 
                                        "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displaySearchResult(SearchResult result, String searchType, String algorithm) {
        if (result.isFound()) {
            Student student = result.getStudent();
            Object[] row = {
                result.getIndex(),
                student.getRollNumber(),
                student.getName(),
                student.getMarks(),
                student.isApproved() ? "Approved" : "Pending"
            };
            resultTableModel.addRow(row);
            
            resultLabel.setText("✅ Student found at index " + result.getIndex() + "!");
            resultLabel.setForeground(SUCCESS_COLOR);
            
            System.out.println("[DSA] Search successful! Found " + student.getName() + " at index " + result.getIndex());
        } else {
            resultLabel.setText("❌ Student not found!");
            resultLabel.setForeground(ACCENT_COLOR);
            
            System.out.println("[DSA] Search completed. Student not found.");
        }
    }
    
    private void displayTrace(List<SearchStep> steps) {
        StringBuilder trace = new StringBuilder();
        trace.append("═══════════════════════════════════════════════════════════════════\n");
        trace.append("                     SEARCH ALGORITHM TRACE\n");
        trace.append("═══════════════════════════════════════════════════════════════════\n\n");
        
        for (int i = 0; i < steps.size(); i++) {
            SearchStep step = steps.get(i);
            trace.append(String.format("📍 Step %d: Index %d\n", i + 1, step.getIndex()));
            trace.append(String.format("   Roll Number: %s\n", step.getRollNumber()));
            trace.append(String.format("   Name: %s\n", step.getName()));
            trace.append(String.format("   Comparison: %s\n", step.isMatch() ? "✅ MATCH!" : "❌ No match"));
            trace.append("\n");
        }
        
        traceArea.setText(trace.toString());
        traceArea.setCaretPosition(0);
    }
}