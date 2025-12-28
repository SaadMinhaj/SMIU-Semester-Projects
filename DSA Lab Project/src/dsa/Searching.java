package dsa;

import model.Student;

public class Searching {
    
    // Linear Search by Roll Number
    public static Student linearSearchByRollNumber(Student[] students, int size, String rollNumber) {
        System.out.println("\n[DSA] Starting Linear Search for roll number: " + rollNumber);
        System.out.println("[DSA] Searching through " + size + " students");
        
        for (int i = 0; i < size; i++) {
            System.out.println("[DSA] Checking index " + i + ": " + students[i].getRollNumber());
            
            if (students[i].getRollNumber().equals(rollNumber)) {
                System.out.println("[DSA] FOUND! Student found at index " + i);
                System.out.println("[DSA] Student details: " + students[i].getName() + 
                                 ", Marks: " + students[i].getMarks());
                return students[i];
            } else {
                System.out.println("[DSA] No match at index " + i);
            }
        }
        
        System.out.println("[DSA] Student with roll number " + rollNumber + " NOT FOUND");
        return null;
    }
    
    // Linear Search by Name
    public static Student linearSearchByName(Student[] students, int size, String name) {
        System.out.println("\n[DSA] Starting Linear Search for name: " + name);
        System.out.println("[DSA] Searching through " + size + " students");
        
        for (int i = 0; i < size; i++) {
            System.out.println("[DSA] Checking index " + i + ": " + students[i].getName());
            
            if (students[i].getName().equalsIgnoreCase(name)) {
                System.out.println("[DSA] FOUND! Student found at index " + i);
                System.out.println("[DSA] Student details: " + students[i].getRollNumber() + 
                                 ", Marks: " + students[i].getMarks());
                return students[i];
            } else {
                System.out.println("[DSA] No match at index " + i);
            }
        }
        
        System.out.println("[DSA] Student with name " + name + " NOT FOUND");
        return null;
    }
    
    // Binary Search by Roll Number (requires sorted array)
    public static Student binarySearchByRollNumber(Student[] students, int size, String rollNumber) {
        System.out.println("\n[DSA] Starting Binary Search for roll number: " + rollNumber);
        System.out.println("[DSA] Array must be sorted by roll number for binary search");
        
        int left = 0;
        int right = size - 1;
        int step = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("\n[DSA] Step " + step + ":");
            System.out.println("[DSA] Left: " + left + ", Right: " + right + ", Mid: " + mid);
            System.out.println("[DSA] Comparing with student at mid index: " + students[mid].getRollNumber());
            
            int comparison = students[mid].getRollNumber().compareTo(rollNumber);
            
            if (comparison == 0) {
                System.out.println("[DSA] FOUND! Student found at index " + mid);
                System.out.println("[DSA] Student details: " + students[mid].getName() + 
                                 ", Marks: " + students[mid].getMarks());
                return students[mid];
            } else if (comparison < 0) {
                System.out.println("[DSA] " + students[mid].getRollNumber() + " < " + rollNumber);
                System.out.println("[DSA] Searching in right half");
                left = mid + 1;
            } else {
                System.out.println("[DSA] " + students[mid].getRollNumber() + " > " + rollNumber);
                System.out.println("[DSA] Searching in left half");
                right = mid - 1;
            }
            
            step++;
        }
        
        System.out.println("\n[DSA] Student with roll number " + rollNumber + " NOT FOUND");
        return null;
    }
    
    // Binary Search by Name (requires sorted array)
    public static Student binarySearchByName(Student[] students, int size, String name) {
        System.out.println("\n[DSA] Starting Binary Search for name: " + name);
        System.out.println("[DSA] Array must be sorted by name for binary search");
        
        int left = 0;
        int right = size - 1;
        int step = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("\n[DSA] Step " + step + ":");
            System.out.println("[DSA] Left: " + left + ", Right: " + right + ", Mid: " + mid);
            System.out.println("[DSA] Comparing with student at mid index: " + students[mid].getName());
            
            int comparison = students[mid].getName().compareToIgnoreCase(name);
            
            if (comparison == 0) {
                System.out.println("[DSA] FOUND! Student found at index " + mid);
                System.out.println("[DSA] Student details: " + students[mid].getRollNumber() + 
                                 ", Marks: " + students[mid].getMarks());
                return students[mid];
            } else if (comparison < 0) {
                System.out.println("[DSA] " + students[mid].getName() + " < " + name);
                System.out.println("[DSA] Searching in right half");
                left = mid + 1;
            } else {
                System.out.println("[DSA] " + students[mid].getName() + " > " + name);
                System.out.println("[DSA] Searching in left half");
                right = mid - 1;
            }
            
            step++;
        }
        
        System.out.println("\n[DSA] Student with name " + name + " NOT FOUND");
        return null;
    }
}