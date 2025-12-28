package main;

import dsa.*;
import java.util.Scanner;
import model.Admin;
import model.Student;

public class MainMenu {
    private static StudentArray studentArray;
    private static RegistrationQueue registrationQueue;
    private static Admin admin;
    private static Scanner scanner;
    private static Student loggedInStudent;
    
    public static void main(String[] args) {
        // Initialize data structures
        studentArray = new StudentArray(10); // Initial capacity of 10
        registrationQueue = new RegistrationQueue(20); // Queue capacity of 20
        scanner = new Scanner(System.in);
        
        // Create default admin
        admin = new Admin("admin", "admin123");
        
        // Add some sample students for demonstration
        addSampleStudents();
        
        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
        System.out.println("DSA Lab Project - Console Application");
        System.out.println("=====================================\n");
        
        while (true) {
            showMainMenu();
        }
    }
    
    private static void addSampleStudents() {
        System.out.println("\n[SYSTEM] Adding sample students for demonstration...");
        
        Student s1 = new Student("S001", "Rao Hamza Tariq", "hamza123", 85.5);
        s1.setApproved(true);
        studentArray.insert(s1);
        
        Student s2 = new Student("S002", "Imtiaz Maqbool", "imtiaz123", 92.0);
        s2.setApproved(true);
        studentArray.insert(s2);
        
        Student s3 = new Student("S003", "Shahmeer Baqai", "shahmeer123", 78.3);
        s3.setApproved(true);
        studentArray.insert(s3);
        
        Student s4 = new Student("S004", "Muhammad Saad", "saad123", 88.7);
        s4.setApproved(true);
        studentArray.insert(s4);
        
        System.out.println("[SYSTEM] Sample students added successfully!\n");
    }
    
    private static void showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                studentLogin();
                break;
            case 2:
                adminLogin();
                break;
            case 3:
                System.out.println("\nThank you for using Student Management System!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }
    
    private static void studentLogin() {
        System.out.println("\n--- STUDENT LOGIN ---");
        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        
        // Search for student
        Student student = studentArray.getByRollNumber(rollNumber);
        
        if (student != null && student.isApproved()) {
            // Verify password
            if (password.equals(student.getPassword())) {
                loggedInStudent = student;
                System.out.println("\nLogin successful! Welcome " + student.getName());
                showStudentMenu();
            } else {
                System.out.println("Invalid password!");
            }
        } else {
            System.out.println("Student not found or not approved!");
            System.out.println("Would you like to register? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                requestRegistration();
            }
        }
    }
    
    private static void adminLogin() {
        System.out.println("\n--- ADMIN LOGIN ---");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        
        if (username.equals(admin.getUsername()) && 
            password.equals(admin.getPassword())) {
            System.out.println("\nAdmin login successful! Welcome " + username);
            showAdminMenu();
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }
    
    private static void requestRegistration() {
        System.out.println("\n--- REGISTRATION REQUEST ---");
        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();
        
        Student newStudent = new Student(rollNumber, name, password, marks);
        
        if (registrationQueue.enqueue(newStudent)) {
            System.out.println("\nRegistration request submitted successfully!");
            System.out.println("Please wait for admin approval.");
        } else {
            System.out.println("Registration queue is full. Please try again later.");
        }
    }
    
    private static void showStudentMenu() {
        while (true) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. View Profile");
            System.out.println("2. View Marks");
            System.out.println("3. Check Registration Status");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    viewStudentProfile();
                    break;
                case 2:
                    viewStudentMarks();
                    break;
                case 3:
                    checkRegistrationStatus();
                    break;
                case 4:
                    loggedInStudent = null;
                    System.out.println("\nLogged out successfully!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void viewStudentProfile() {
        if (loggedInStudent != null) {
            System.out.println("\n--- STUDENT PROFILE ---");
            System.out.println("Roll Number: " + loggedInStudent.getRollNumber());
            System.out.println("Name: " + loggedInStudent.getName());
            System.out.println("Marks: " + loggedInStudent.getMarks());
            System.out.println("Status: " + (loggedInStudent.isApproved() ? "Approved" : "Pending"));
        }
    }
    
    private static void viewStudentMarks() {
        if (loggedInStudent != null) {
            System.out.println("\n--- STUDENT MARKS ---");
            System.out.println("Name: " + loggedInStudent.getName());
            System.out.println("Marks: " + loggedInStudent.getMarks());
            
            if (loggedInStudent.getMarks() >= 90) {
                System.out.println("Grade: A+ (Excellent)");
            } else if (loggedInStudent.getMarks() >= 80) {
                System.out.println("Grade: A (Very Good)");
            } else if (loggedInStudent.getMarks() >= 70) {
                System.out.println("Grade: B (Good)");
            } else if (loggedInStudent.getMarks() >= 60) {
                System.out.println("Grade: C (Average)");
            } else {
                System.out.println("Grade: D (Below Average)");
            }
        }
    }
    
    private static void checkRegistrationStatus() {
        System.out.println("\n--- REGISTRATION STATUS ---");
        if (loggedInStudent != null && loggedInStudent.isApproved()) {
            System.out.println("Your registration is APPROVED!");
        } else {
            System.out.println("Your registration is PENDING approval from admin.");
        }
    }
    
    private static void showAdminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View Registration Queue");
            System.out.println("2. Approve Registration");
            System.out.println("3. Add Student Manually");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Search Student");
            System.out.println("7. Sort Students");
            System.out.println("8. Generate Reports");
            System.out.println("9. Display All Students");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    viewRegistrationQueue();
                    break;
                case 2:
                    approveRegistration();
                    break;
                case 3:
                    addStudentManually();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    updateStudent();
                    break;
                case 6:
                    searchStudent();
                    break;
                case 7:
                    sortStudents();
                    break;
                case 8:
                    generateReports();
                    break;
                case 9:
                    displayAllStudents();
                    break;
                case 10:
                    System.out.println("\nAdmin logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void viewRegistrationQueue() {
        System.out.println("\n--- REGISTRATION QUEUE ---");
        registrationQueue.display();
    }
    
    private static void approveRegistration() {
        System.out.println("\n--- APPROVE REGISTRATION ---");
        
        if (registrationQueue.isEmpty()) {
            System.out.println("No pending registration requests!");
            return;
        }
        
        Student student = registrationQueue.peek();
        
        if (student != null) {
            System.out.println("Next student in queue:");
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Name: " + student.getName());
            System.out.println("Marks: " + student.getMarks());
            
            System.out.print("\nApprove this student? (y/n): ");
            String choice = scanner.nextLine();
            
            if (choice.equalsIgnoreCase("y")) {
                student = registrationQueue.dequeue();
                if (student != null) {
                    student.setApproved(true);
                    studentArray.insert(student);
                    System.out.println("Student approved and added to system!");
                }
            } else {
                System.out.println("Student not approved.");
            }
        }
    }
    
    private static void addStudentManually() {
        System.out.println("\n--- ADD STUDENT MANUALLY ---");
        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();
        
        Student newStudent = new Student(rollNumber, name, password, marks);
        newStudent.setApproved(true);
        studentArray.insert(newStudent);
        
        System.out.println("Student added successfully!");
    }
    
    private static void deleteStudent() {
        System.out.println("\n--- DELETE STUDENT ---");
        System.out.print("Enter Roll Number to delete: ");
        String rollNumber = scanner.nextLine();
        
        if (studentArray.deleteByRollNumber(rollNumber)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }
    
    private static void updateStudent() {
        System.out.println("\n--- UPDATE STUDENT ---");
        System.out.print("Enter Roll Number to update: ");
        String rollNumber = scanner.nextLine();
        
        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter New Marks: ");
        double newMarks = scanner.nextDouble();
        scanner.nextLine();
        
        if (studentArray.update(rollNumber, newName, newMarks)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }
    
    private static void searchStudent() {
        System.out.println("\n--- SEARCH STUDENT ---");
        System.out.println("1. Search by Roll Number (Linear)");
        System.out.println("2. Search by Name (Linear)");
        System.out.println("3. Search by Roll Number (Binary - Sorted Required)");
        System.out.println("4. Search by Name (Binary - Sorted Required)");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        Student[] students = studentArray.getAllStudents();
        int size = studentArray.getSize();
        
        switch (choice) {
            case 1:
                System.out.print("Enter Roll Number: ");
                String rollNumber = scanner.nextLine();
                Searching.linearSearchByRollNumber(students, size, rollNumber);
                break;
            case 2:
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                Searching.linearSearchByName(students, size, name);
                break;
            case 3:
                System.out.print("Enter Roll Number: ");
                String rollNumberBinary = scanner.nextLine();
                Searching.binarySearchByRollNumber(students, size, rollNumberBinary);
                break;
            case 4:
                System.out.print("Enter Name: ");
                String nameBinary = scanner.nextLine();
                Searching.binarySearchByName(students, size, nameBinary);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void sortStudents() {
        System.out.println("\n--- SORT STUDENTS ---");
        System.out.println("1. Sort by Roll Number (Bubble Sort)");
        System.out.println("2. Sort by Name (Selection Sort)");
        System.out.println("3. Sort by Marks (Insertion Sort)");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        Student[] students = studentArray.getAllStudents();
        int size = studentArray.getSize();
        
        switch (choice) {
            case 1:
                Sorting.bubbleSortByRollNumber(students, size);
                break;
            case 2:
                Sorting.selectionSortByName(students, size);
                break;
            case 3:
                Sorting.insertionSortByMarks(students, size);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void generateReports() {
        System.out.println("\n--- GENERATE REPORTS ---");
        
        Student[] students = studentArray.getAllStudents();
        int size = studentArray.getSize();
        
        if (size == 0) {
            System.out.println("No students in the system!");
            return;
        }
        
        // Total students
        System.out.println("Total Students: " + size);
        
        // Calculate average marks
        double totalMarks = 0;
        Student topScorer = students[0];
        
        for (int i = 0; i < size; i++) {
            totalMarks += students[i].getMarks();
            if (students[i].getMarks() > topScorer.getMarks()) {
                topScorer = students[i];
            }
        }
        
        double average = totalMarks / size;
        
        System.out.println("Average Marks: " + String.format("%.2f", average));
        System.out.println("Top Scorer: " + topScorer.getName() + 
                         " (Roll: " + topScorer.getRollNumber() + 
                         ", Marks: " + topScorer.getMarks() + ")");
    }
    
    private static void displayAllStudents() {
        System.out.println("\n--- ALL STUDENTS ---");
        studentArray.display();
    }
}