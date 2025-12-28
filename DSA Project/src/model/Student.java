package model;

public class Student {
    private String rollNumber;
    private String name;
    private double marks;
    private boolean isApproved;
    private String password;
    
    public Student(String rollNumber, String name, double marks, String password) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
        this.password = password;
        this.isApproved = false; // New students are not approved by default
    }
    
    // Getters and Setters
    public String getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
    }
    
    public boolean isApproved() {
        return isApproved;
    }
    
    public void setApproved(boolean approved) {
        isApproved = approved;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "rollNumber='" + rollNumber + '\'' +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                ", isApproved=" + isApproved +
                '}';
    }
    
    public String toDisplayString() {
        return String.format("%-10s | %-20s | %-6.2f | %-10s", 
                           rollNumber, name, marks, isApproved ? "Approved" : "Pending");
    }
}