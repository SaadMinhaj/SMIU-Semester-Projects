package main;

import model.Admin;
import model.Student;
import dsa.StudentArray;
import dsa.RegistrationQueue;
import gui.LoginFrame;
import gui.StudentDashboard;
import gui.AdminDashboard;

public class StudentManagementSystem {
    // Singleton instance
    private static StudentManagementSystem instance;
    
    // Data structures
    private StudentArray studentArray;
    private RegistrationQueue registrationQueue;
    private Admin admin;
    
    // Currently logged in user
    private Student loggedInStudent;
    private boolean adminLoggedIn;
    
    // GUI components
    private LoginFrame loginFrame;
    private StudentDashboard studentDashboard;
    private AdminDashboard adminDashboard;
    
    private StudentManagementSystem() {
        // Initialize data structures
        this.studentArray = new StudentArray();
        this.registrationQueue = new RegistrationQueue(20);
        this.admin = new Admin("admin", "admin123");
        
        // Add some sample data
        initializeSampleData();
        
        this.loggedInStudent = null;
        this.adminLoggedIn = false;
    }
    
    public static StudentManagementSystem getInstance() {
        if (instance == null) {
            instance = new StudentManagementSystem();
        }
        return instance;
    }
    
    private void initializeSampleData() {
        // Add some approved students
        Student s1 = new Student("0001", "Muhammad Saad", 85.5, "saad123");
        s1.setApproved(true);
        studentArray.insert(s1);
        
        Student s2 = new Student("0002", "Muhammad Hammad", 92.0, "hammad123");
        s2.setApproved(true);
        studentArray.insert(s2);
        
        Student s3 = new Student("0003", "Muhammad Ali", 78.5, "ali123");
        s3.setApproved(true);
        studentArray.insert(s3);
        
        Student s4 = new Student("0004", "Muhammad Jawad", 88.5, "jawad123");
        s4.setApproved(true);
        studentArray.insert(s4);
        
        System.out.println("[DSA] Sample data initialized with " + studentArray.getSize() + " approved students");
    }
    
    // Authentication methods
    public boolean authenticateStudent(String rollNumber, String password) {
        Student student = studentArray.findByRollNumber(rollNumber);
        if (student != null && student.getPassword().equals(password)) {
            if (student.isApproved()) {
                loggedInStudent = student;
                System.out.println("[DSA] Student login successful: " + student.getName());
                return true;
            } else {
                System.out.println("[DSA] Student not approved: " + student.getName());
                return false;
            }
        }
        return false;
    }
    
    public boolean authenticateAdmin(String username, String password) {
        if (admin.authenticate(username, password)) {
            adminLoggedIn = true;
            System.out.println("[DSA] Admin login successful");
            return true;
        }
        return false;
    }
    
    public void logout() {
        loggedInStudent = null;
        adminLoggedIn = false;
        System.out.println("[DSA] User logged out");
    }
    
    // Registration methods
    public boolean registerStudent(String rollNumber, String name, double marks, String password) {
        // Check if student already exists
        if (studentArray.findByRollNumber(rollNumber) != null) {
            System.out.println("[DSA] Student with roll number " + rollNumber + " already exists");
            return false;
        }
        
        Student newStudent = new Student(rollNumber, name, marks, password);
        boolean success = registrationQueue.enqueue(newStudent);
        
        if (success) {
            System.out.println("[DSA] Student registration request enqueued: " + name);
        }
        
        return success;
    }
    
    public boolean approveRegistration() {
        if (registrationQueue.isEmpty()) {
            System.out.println("[DSA] No pending registration requests");
            return false;
        }
        
        Student student = registrationQueue.dequeue();
        if (student != null) {
            student.setApproved(true);
            studentArray.insert(student);
            System.out.println("[DSA] Registration approved for: " + student.getName());
            return true;
        }
        
        return false;
    }
    
    public boolean rejectRegistration() {
        if (registrationQueue.isEmpty()) {
            System.out.println("[DSA] No pending registration requests");
            return false;
        }
        
        Student student = registrationQueue.dequeue();
        if (student != null) {
            System.out.println("[DSA] Registration rejected for: " + student.getName());
            return true;
        }
        
        return false;
    }
    
    // Getters
    public StudentArray getStudentArray() {
        return studentArray;
    }
    
    public RegistrationQueue getRegistrationQueue() {
        return registrationQueue;
    }
    
    public Student getLoggedInStudent() {
        return loggedInStudent;
    }
    
    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }
    
    // GUI Navigation methods
    public void showLogin() {
        if (studentDashboard != null) {
            studentDashboard.dispose();
            studentDashboard = null;
        }
        if (adminDashboard != null) {
            adminDashboard.dispose();
            adminDashboard = null;
        }
        
        loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
    
    public void showStudentDashboard() {
        if (loginFrame != null) {
            loginFrame.dispose();
            loginFrame = null;
        }
        
        studentDashboard = new StudentDashboard();
        studentDashboard.setVisible(true);
    }
    
    public void showAdminDashboard() {
        if (loginFrame != null) {
            loginFrame.dispose();
            loginFrame = null;
        }
        
        adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not set system look and feel");
        }
        
        // Create and show login frame
        javax.swing.SwingUtilities.invokeLater(() -> {
            StudentManagementSystem.getInstance().showLogin();
        });
    }
}