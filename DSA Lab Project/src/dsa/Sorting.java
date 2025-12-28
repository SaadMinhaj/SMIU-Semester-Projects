package dsa;

import model.Student;

public class Sorting {
    
    // Bubble Sort by Roll Number
    public static void bubbleSortByRollNumber(Student[] students, int size) {
        System.out.println("\n[DSA] Starting Bubble Sort by Roll Number");
        System.out.println("[DSA] Initial array:");
        printArray(students, size);
        
        for (int i = 0; i < size - 1; i++) {
            System.out.println("\n[DSA] Pass " + (i + 1) + ":");
            boolean swapped = false;
            
            for (int j = 0; j < size - i - 1; j++) {
                System.out.println("[DSA] Comparing index " + j + " and " + (j + 1));
                System.out.println("[DSA] Comparing " + students[j].getRollNumber() + 
                                 " with " + students[j + 1].getRollNumber());
                
                if (students[j].getRollNumber().compareTo(students[j + 1].getRollNumber()) > 0) {
                    System.out.println("[DSA] Swapping needed! " + students[j].getRollNumber() + 
                                     " > " + students[j + 1].getRollNumber());
                    
                    // Swap students
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                    swapped = true;
                    
                    System.out.println("[DSA] Swapped: " + students[j].getRollNumber() + 
                                     " and " + students[j + 1].getRollNumber());
                } else {
                    System.out.println("[DSA] No swap needed");
                }
            }
            
            System.out.println("[DSA] After Pass " + (i + 1) + ":");
            printArray(students, size);
            
            if (!swapped) {
                System.out.println("[DSA] No swaps in this pass, array is sorted!");
                break;
            }
        }
        
        System.out.println("\n[DSA] Bubble Sort completed!");
    }
    
    // Selection Sort by Name
    public static void selectionSortByName(Student[] students, int size) {
        System.out.println("\n[DSA] Starting Selection Sort by Name");
        System.out.println("[DSA] Initial array:");
        printArray(students, size);
        
        for (int i = 0; i < size - 1; i++) {
            System.out.println("\n[DSA] Pass " + (i + 1) + " - Finding minimum from index " + i + " to " + (size - 1));
            int minIndex = i;
            
            for (int j = i + 1; j < size; j++) {
                System.out.println("[DSA] Comparing " + students[j].getName() + 
                                 " with current minimum " + students[minIndex].getName());
                
                if (students[j].getName().compareTo(students[minIndex].getName()) < 0) {
                    System.out.println("[DSA] New minimum found at index " + j + ": " + students[j].getName());
                    minIndex = j;
                }
            }
            
            if (minIndex != i) {
                System.out.println("[DSA] Swapping index " + i + " with " + minIndex);
                System.out.println("[DSA] Swapping " + students[i].getName() + " with " + students[minIndex].getName());
                
                Student temp = students[i];
                students[i] = students[minIndex];
                students[minIndex] = temp;
                
                System.out.println("[DSA] Swapped successfully!");
            } else {
                System.out.println("[DSA] No swap needed, element already in correct position");
            }
            
            System.out.println("[DSA] After Pass " + (i + 1) + ":");
            printArray(students, size);
        }
        
        System.out.println("\n[DSA] Selection Sort completed!");
    }
    
    // Insertion Sort by Marks
    public static void insertionSortByMarks(Student[] students, int size) {
        System.out.println("\n[DSA] Starting Insertion Sort by Marks");
        System.out.println("[DSA] Initial array:");
        printArray(students, size);
        
        for (int i = 1; i < size; i++) {
            System.out.println("\n[DSA] Pass " + i + " - Inserting element at index " + i);
            Student key = students[i];
            System.out.println("[DSA] Key element: " + key.getName() + " with marks: " + key.getMarks());
            
            int j = i - 1;
            
            while (j >= 0 && students[j].getMarks() > key.getMarks()) {
                System.out.println("[DSA] " + students[j].getMarks() + " > " + key.getMarks() + 
                                 ", shifting " + students[j].getName() + " to the right");
                students[j + 1] = students[j];
                j--;
            }
            
            students[j + 1] = key;
            System.out.println("[DSA] Inserted " + key.getName() + " at index " + (j + 1));
            
            System.out.println("[DSA] After Pass " + i + ":");
            printArray(students, size);
        }
        
        System.out.println("\n[DSA] Insertion Sort completed!");
    }
    
    // Helper method to print array
    private static void printArray(Student[] students, int size) {
        System.out.println("[DSA] Current array state:");
        for (int i = 0; i < size; i++) {
            System.out.println("[DSA] Index " + i + ": " + students[i].getRollNumber() + 
                             " - " + students[i].getName() + 
                             " - Marks: " + students[i].getMarks());
        }
    }
}