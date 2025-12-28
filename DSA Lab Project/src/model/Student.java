package model;

public class Student {
    private String rollNumber;
    private String name;
    private String password;
    private double marks;
    private boolean isApproved;
    
    public Student(String rollNumber, String name, String password, double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.password = password;
        this.marks = marks;
        this.isApproved = false;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public boolean isApproved() {
        return isApproved;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
    }
    
    public void setApproved(boolean approved) {
        this.isApproved = approved;
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
}