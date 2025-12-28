package dsa;

import model.Student;
import java.util.Arrays;

public class StudentArray {
    private Student[] students;
    private int size;
    private int capacity;
    
    public StudentArray() {
        this.capacity = 10;
        this.students = new Student[capacity];
        this.size = 0;
    }
    
    public StudentArray(int initialCapacity) {
        this.capacity = initialCapacity;
        this.students = new Student[capacity];
        this.size = 0;
    }
    
    // Insert a student at the end
    public void insert(Student student) {
        if (size == capacity) {
            resize();
        }
        students[size] = student;
        size++;
        System.out.println("[DSA] Inserted student at index " + (size - 1) + ". New size: " + size);
    }
    
    // Insert at specific index
    public void insertAt(int index, Student student) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (size == capacity) {
            resize();
        }
        
        // Shift elements to the right
        for (int i = size; i > index; i--) {
            students[i] = students[i - 1];
        }
        
        students[index] = student;
        size++;
        System.out.println("[DSA] Inserted student at index " + index + ". Shifted " + (size - index - 1) + " elements. New size: " + size);
    }
    
    // Delete by roll number
    public boolean delete(String rollNumber) {
        int index = findIndexByRollNumber(rollNumber);
        if (index == -1) {
            System.out.println("[DSA] Student with roll number " + rollNumber + " not found for deletion");
            return false;
        }
        
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
        }
        
        students[size - 1] = null; // Clear last element
        size--;
        System.out.println("[DSA] Deleted student at index " + index + ". Shifted " + (size - index) + " elements. New size: " + size);
        return true;
    }
    
    // Update student
    public boolean update(String rollNumber, String newName, double newMarks) {
        int index = findIndexByRollNumber(rollNumber);
        if (index == -1) {
            System.out.println("[DSA] Student with roll number " + rollNumber + " not found for update");
            return false;
        }
        
        students[index].setName(newName);
        students[index].setMarks(newMarks);
        System.out.println("[DSA] Updated student at index " + index + ": " + students[index]);
        return true;
    }
    
    // Find student by roll number
    public Student findByRollNumber(String rollNumber) {
        for (int i = 0; i < size; i++) {
            if (students[i].getRollNumber().equals(rollNumber)) {
                System.out.println("[DSA] Found student at index " + i);
                return students[i];
            }
        }
        System.out.println("[DSA] Student with roll number " + rollNumber + " not found");
        return null;
    }
    
    // Find student by name
    public Student findByName(String name) {
        for (int i = 0; i < size; i++) {
            if (students[i].getName().equalsIgnoreCase(name)) {
                System.out.println("[DSA] Found student at index " + i);
                return students[i];
            }
        }
        System.out.println("[DSA] Student with name " + name + " not found");
        return null;
    }
    
    // Get student at index
    public Student get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return students[index];
    }
    
    // Get all students
    public Student[] getAll() {
        return Arrays.copyOf(students, size);
    }
    
    // Get size
    public int getSize() {
        return size;
    }
    
    // Get capacity
    public int getCapacity() {
        return capacity;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Dynamic resizing
    private void resize() {
        int newCapacity = capacity * 2;
        Student[] newArray = new Student[newCapacity];
        
        // Manual copy
        for (int i = 0; i < size; i++) {
            newArray[i] = students[i];
        }
        
        students = newArray;
        capacity = newCapacity;
        System.out.println("[DSA] Resized array from " + (capacity / 2) + " to " + capacity);
    }
    
    // Helper method to find index by roll number
    private int findIndexByRollNumber(String rollNumber) {
        for (int i = 0; i < size; i++) {
            if (students[i].getRollNumber().equals(rollNumber)) {
                return i;
            }
        }
        return -1;
    }
    
    // Display array contents
    public void display() {
        System.out.println("[DSA] StudentArray contents (size=" + size + ", capacity=" + capacity + "):");
        for (int i = 0; i < size; i++) {
            System.out.println("  [" + i + "] " + students[i]);
        }
    }
}