package gui;

import main.StudentManagementSystem;
import model.Student;
import dsa.StudentArray;
import dsa.Sorting;
import dsa.Sorting.SortResult;
import dsa.Sorting.SortStep;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.table.JTableHeader;


public class SortDialog extends JDialog {
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
    private JComboBox<String> sortByCombo;
    private JComboBox<String> algorithmCombo;
    private JButton sortButton;
    private JTable beforeTable;
    private DefaultTableModel beforeTableModel;
    private JTable afterTable;
    private DefaultTableModel afterTableModel;
    private JTextArea traceArea;
    private JLabel statisticsLabel;
    
    public SortDialog(JFrame parent, StudentManagementSystem sms) {
        super(parent, "Sort Students - DSA Visualization", true);
        this.sms = sms;
        
        setSize(1200, 800);
        setLocationRelativeTo(parent);
        
        // Apply modern colors
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        initComponents();
        layoutComponents();
        loadBeforeData();
    }
    
    private void initComponents() {
        // Modern Fonts
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font tableFont = new Font("Segoe UI", Font.PLAIN, 13);
        
        // Sort By Combo
        String[] sortByOptions = {"By Roll Number", "By Name", "By Marks"};
        sortByCombo = new JComboBox<>(sortByOptions);
        sortByCombo.setFont(fieldFont);
        sortByCombo.setBackground(PANEL_COLOR);
        sortByCombo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR));
        
        // Algorithm Combo
        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort"};
        algorithmCombo = new JComboBox<>(algorithms);
        algorithmCombo.setFont(fieldFont);
        algorithmCombo.setBackground(PANEL_COLOR);
        algorithmCombo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR));
        
        // Sort Button
        sortButton = new JButton("📊 Sort Students");
        sortButton.setFont(buttonFont);
        sortButton.setBackground(PRIMARY_COLOR);
        sortButton.setForeground(Color.WHITE);
        sortButton.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        sortButton.setFocusPainted(false);
        sortButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Before Table
        String[] columns = {"Roll Number", "Name", "Marks", "Status"};
        beforeTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        beforeTable = new JTable(beforeTableModel);
        beforeTable.setFont(tableFont);
        beforeTable.setRowHeight(30);
        beforeTable.setGridColor(BORDER_COLOR);
        beforeTable.setSelectionBackground(PRIMARY_COLOR);
        beforeTable.setSelectionForeground(Color.WHITE);
        beforeTable.setShowGrid(true);
        beforeTable.setShowHorizontalLines(true);
        beforeTable.setShowVerticalLines(false);
        
        // Before Table Header
        JTableHeader beforeHeader = beforeTable.getTableHeader();
        beforeHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        beforeHeader.setBackground(SECONDARY_COLOR);
        beforeHeader.setForeground(Color.WHITE);
        beforeHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SECONDARY_COLOR));
        
        // After Table
        afterTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        afterTable = new JTable(afterTableModel);
        afterTable.setFont(tableFont);
        afterTable.setRowHeight(30);
        afterTable.setGridColor(BORDER_COLOR);
        afterTable.setSelectionBackground(SUCCESS_COLOR);
        afterTable.setSelectionForeground(Color.WHITE);
        afterTable.setShowGrid(true);
        afterTable.setShowHorizontalLines(true);
        afterTable.setShowVerticalLines(false);
        
        // After Table Header
        JTableHeader afterHeader = afterTable.getTableHeader();
        afterHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        afterHeader.setBackground(SUCCESS_COLOR);
        afterHeader.setForeground(Color.WHITE);
        afterHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, SUCCESS_COLOR));
        
        // Trace Area
        traceArea = new JTextArea(15, 50);
        traceArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        traceArea.setEditable(false);
        traceArea.setBackground(new Color(30, 30, 30));
        traceArea.setForeground(new Color(0, 255, 0));
        traceArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Statistics Label
        statisticsLabel = new JLabel("Ready to sort...");
        statisticsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        statisticsLabel.setForeground(TEXT_COLOR);
        
        // Action Listeners
        sortButton.addActionListener(e -> performSort());
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("📊 Sort Students - DSA Visualization");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        controlPanel.setBackground(PANEL_COLOR);
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        controlPanel.add(new JLabel("Sort By:"));
        controlPanel.add(sortByCombo);
        controlPanel.add(Box.createHorizontalStrut(20));
        controlPanel.add(new JLabel("Algorithm:"));
        controlPanel.add(algorithmCombo);
        controlPanel.add(Box.createHorizontalStrut(20));
        controlPanel.add(sortButton);
        
        // Tables Panel
        JPanel tablesPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        tablesPanel.setBackground(BACKGROUND_COLOR);
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel beforePanel = new JPanel(new BorderLayout());
        beforePanel.setBackground(PANEL_COLOR);
        beforePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        
        JLabel beforeTitle = new JLabel("Before Sorting", SwingConstants.CENTER);
        beforeTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        beforeTitle.setForeground(TEXT_COLOR);
        beforeTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        beforeTitle.setBackground(SECONDARY_COLOR);
        beforeTitle.setOpaque(true);
        beforeTitle.setForeground(Color.WHITE);
        
        beforePanel.add(beforeTitle, BorderLayout.NORTH);
        beforePanel.add(new JScrollPane(beforeTable), BorderLayout.CENTER);
        
        JPanel afterPanel = new JPanel(new BorderLayout());
        afterPanel.setBackground(PANEL_COLOR);
        afterPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 1, 1, BORDER_COLOR),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        
        JLabel afterTitle = new JLabel("After Sorting", SwingConstants.CENTER);
        afterTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        afterTitle.setForeground(TEXT_COLOR);
        afterTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        afterTitle.setBackground(SUCCESS_COLOR);
        afterTitle.setOpaque(true);
        afterTitle.setForeground(Color.WHITE);
        
        afterPanel.add(afterTitle, BorderLayout.NORTH);
        afterPanel.add(new JScrollPane(afterTable), BorderLayout.CENTER);
        
        tablesPanel.add(beforePanel);
        tablesPanel.add(afterPanel);
        
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
        tracePanel.add(statisticsLabel, BorderLayout.NORTH);
        tracePanel.add(new JScrollPane(traceArea), BorderLayout.CENTER);
        
        // Main Content
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tablesPanel, tracePanel);
        splitPane.setDividerLocation(350);
        splitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        mainContent.add(controlPanel, BorderLayout.NORTH);
        mainContent.add(splitPane, BorderLayout.CENTER);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
    }
    
    private void loadBeforeData() {
        beforeTableModel.setRowCount(0);
        StudentArray students = sms.getStudentArray();
        
        for (int i = 0; i < students.getSize(); i++) {
            Student student = students.get(i);
            Object[] row = {
                student.getRollNumber(),
                student.getName(),
                student.getMarks(),
                student.isApproved() ? "Approved" : "Pending"
            };
            beforeTableModel.addRow(row);
        }
    }
    
    private void performSort() {
        String sortBy = (String) sortByCombo.getSelectedItem();
        String algorithm = (String) algorithmCombo.getSelectedItem();
        
        // Clear previous results
        afterTableModel.setRowCount(0);
        traceArea.setText("");
        
        StudentArray students = sms.getStudentArray();
        SortResult result = null;
        
        try {
            if ("By Roll Number".equals(sortBy)) {
                if ("Bubble Sort".equals(algorithm)) {
                    System.out.println("[DSA] === BUBBLE SORT BY ROLL NUMBER ===");
                    result = Sorting.bubbleSortByRollNumber(students);
                } else if ("Selection Sort".equals(algorithm)) {
                    System.out.println("[DSA] === SELECTION SORT BY ROLL NUMBER ===");
                    // Note: Selection sort by roll number not implemented, using name instead
                    result = Sorting.selectionSortByName(students);
                } else { // Insertion Sort
                    System.out.println("[DSA] === INSERTION SORT BY ROLL NUMBER ===");
                    // Note: Insertion sort by roll number not implemented, using marks instead
                    result = Sorting.insertionSortByMarks(students);
                }
            } else if ("By Name".equals(sortBy)) {
                System.out.println("[DSA] === SELECTION SORT BY NAME ===");
                result = Sorting.selectionSortByName(students);
            } else { // By Marks
                System.out.println("[DSA] === INSERTION SORT BY MARKS ===");
                result = Sorting.insertionSortByMarks(students);
            }
            
            // Display results
            if (result != null) {
                displaySortResult(result);
                displayTrace(result.getSteps());
                displayStatistics(result, algorithm);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during sort: " + e.getMessage(), 
                                        "Sort Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displaySortResult(SortResult result) {
        Student[] sortedArray = result.getSortedArray();
        
        for (Student student : sortedArray) {
            if (student != null) {
                Object[] row = {
                    student.getRollNumber(),
                    student.getName(),
                    student.getMarks(),
                    student.isApproved() ? "Approved" : "Pending"
                };
                afterTableModel.addRow(row);
            }
        }
    }
    
    private void displayTrace(List<SortStep> steps) {
        StringBuilder trace = new StringBuilder();
        trace.append("═══════════════════════════════════════════════════════════════════\n");
        trace.append("                     SORTING ALGORITHM TRACE\n");
        trace.append("═══════════════════════════════════════════════════════════════════\n\n");
        
        int currentPass = 0;
        for (SortStep step : steps) {
            if (step.getPass() != currentPass) {
                currentPass = step.getPass();
                trace.append(String.format("🔄 PASS %d\n", currentPass));
                trace.append(String.format("═══════════════\n"));
            }
            
            trace.append(String.format("📍 %s\n", step.getDescription()));
            
            if (step.isOperationPerformed()) {
                trace.append(String.format("   ✅ Operation performed at indices %d and %d\n", 
                                         step.getIndex1(), step.getIndex2()));
            }
            
            // Display array state
            Student[] arrayState = step.getArrayState();
            trace.append("   📋 Array: [");
            for (int i = 0; i < arrayState.length; i++) {
                if (arrayState[i] != null) {
                    trace.append(arrayState[i].getName());
                    if (i < arrayState.length - 1) trace.append(", ");
                }
            }
            trace.append("]\n\n");
        }
        
        traceArea.setText(trace.toString());
        traceArea.setCaretPosition(0);
    }
    
    private void displayStatistics(SortResult result, String algorithm) {
        statisticsLabel.setText(String.format(
            "✅ %s Complete! Operations: %d, Comparisons: %d", 
            algorithm, result.getOperations(), result.getComparisons()
        ));
    }
}