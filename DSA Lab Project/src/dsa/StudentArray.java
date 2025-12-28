package dsa;

import model.Student;

public class StudentArray {
    private Student[] students;
    private int size;
    private int capacity;
    
    public StudentArray(int initialCapacity) {
        this.capacity = initialCapacity;
        this.students = new Student[capacity];
        this.size = 0;
        System.out.println("[DSA] StudentArray initialized with capacity: " + capacity);
    }
    
    // Dynamic array resizing
    private void resize() {
        int newCapacity = capacity * 2;
        Student[] newArray = new Student[newCapacity];
        
        System.out.println("[DSA] Resizing array from " + capacity + " to " + newCapacity);
        
        // Copy elements manually
        for (int i = 0; i < size; i++) {
            newArray[i] = students[i];
            System.out.println("[DSA] Copied student at index " + i + " to new array");
        }
        
        students = newArray;
        capacity = newCapacity;
        System.out.println("[DSA] Array resized successfully");
    }
    
    // Insert student at the end
    public void insert(Student student) {
        if (size == capacity) {
            System.out.println("[DSA] Array full, resizing needed");
            resize();
        }
        
        students[size] = student;
        System.out.println("[DSA] Inserted student at index " + size + ": " + student.getName());
        size++;
        System.out.println("[DSA] Current size: " + size);
    }
    
    // Insert student at specific position
    public void insertAt(int index, Student student) {
        if (index < 0 || index > size) {
            System.out.println("[DSA] Invalid index " + index + " for insertion");
            return;
        }
        
        if (size == capacity) {
            resize();
        }
        
        // Shift elements to the right
        System.out.println("[DSA] Shifting elements to make space at index " + index);
        for (int i = size; i > index; i--) {
            students[i] = students[i - 1];
            System.out.println("[DSA] Shifted element from index " + (i - 1) + " to " + i);
        }
        
        students[index] = student;
        size++;
        System.out.println("[DSA] Inserted student at index " + index + ": " + student.getName());
    }
    
    // Delete student by index
    public void delete(int index) {
        if (index < 0 || index >= size) {
            System.out.println("[DSA] Invalid index " + index + " for deletion");
            return;
        }
        
        Student removedStudent = students[index];
        System.out.println("[DSA] Deleting student at index " + index + ": " + removedStudent.getName());
        
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
            System.out.println("[DSA] Shifted element from index " + (i + 1) + " to " + i);
        }
        
        students[size - 1] = null; // Clear last element
        size--;
        System.out.println("[DSA] Student deleted successfully. New size: " + size);
    }
    
    // Delete student by roll number
    public boolean deleteByRollNumber(String rollNumber) {
        System.out.println("[DSA] Searching for student with roll number: " + rollNumber);
        for (int i = 0; i < size; i++) {
            if (students[i].getRollNumber().equals(rollNumber)) {
                System.out.println("[DSA] Found student at index " + i);
                delete(i);
                return true;
            }
        }
        System.out.println("[DSA] Student with roll number " + rollNumber + " not found");
        return false;
    }
    
    // Update student
    public boolean update(String rollNumber, String newName, double newMarks) {
        System.out.println("[DSA] Updating student with roll number: " + rollNumber);
        for (int i = 0; i < size; i++) {
            if (students[i].getRollNumber().equals(rollNumber)) {
                System.out.println("[DSA] Found student at index " + i);
                System.out.println("[DSA] Current values - Name: " + students[i].getName() + ", Marks: " + students[i].getMarks());
                
                students[i].setName(newName);
                students[i].setMarks(newMarks);
                
                System.out.println("[DSA] Updated values - Name: " + newName + ", Marks: " + newMarks);
                return true;
            }
        }
        System.out.println("[DSA] Student with roll number " + rollNumber + " not found");
        return false;
    }
    
    // Get student by index
    public Student get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("[DSA] Invalid index " + index + " for get operation");
            return null;
        }
        System.out.println("[DSA] Retrieved student at index " + index + ": " + students[index].getName());
        return students[index];
    }
    
    // Get student by roll number
    public Student getByRollNumber(String rollNumber) {
        System.out.println("[DSA] Searching for student with roll number: " + rollNumber);
        for (int i = 0; i < size; i++) {
            if (students[i].getRollNumber().equals(rollNumber)) {
                System.out.println("[DSA] Found student at index " + i + ": " + students[i].getName());
                return students[i];
            }
        }
        System.out.println("[DSA] Student with roll number " + rollNumber + " not found");
        return null;
    }
    
    // Display all students
    public void display() {
        System.out.println("\n[DSA] Displaying all students (" + size + " total):");
        if (size == 0) {
            System.out.println("[DSA] No students in the array");
            return;
        }
        
        for (int i = 0; i < size; i++) {
            System.out.println("[DSA] Index " + i + ": " + students[i].getRollNumber() + 
                             " - " + students[i].getName() + 
                             " - Marks: " + students[i].getMarks());
        }
    }
    
    // Get size
    public int getSize() {
        System.out.println("[DSA] Current array size: " + size);
        return size;
    }
    
    // Get capacity
    public int getCapacity() {
        return capacity;
    }
    
    // Check if empty
    public boolean isEmpty() {
        boolean empty = size == 0;
        System.out.println("[DSA] Array empty check: " + empty);
        return empty;
    }
    
    // Get all students (for sorting operations)
    public Student[] getAllStudents() {
        System.out.println("[DSA] Getting all students array");
        Student[] result = new Student[size];
        for (int i = 0; i < size; i++) {
            result[i] = students[i];
            System.out.println("[DSA] Added student at index " + i + " to result array");
        }
        return result;
    }
}