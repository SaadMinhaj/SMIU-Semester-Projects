package dsa;

import model.Student;
import java.util.ArrayList;
import java.util.List;

public class Sorting {
    
    // Bubble Sort by Roll Number with step-by-step trace
    public static SortResult bubbleSortByRollNumber(StudentArray students) {
        System.out.println("[DSA] Starting Bubble Sort by Roll Number");
        List<SortStep> steps = new ArrayList<>();
        Student[] arr = students.getAll();
        int n = arr.length;
        int totalSwaps = 0;
        int totalComparisons = 0;
        
        steps.add(new SortStep(0, "Initial array", copyArray(arr), 0, 0, false));
        
        for (int i = 0; i < n - 1; i++) {
            int swapsInPass = 0;
            
            for (int j = 0; j < n - i - 1; j++) {
                totalComparisons++;
                boolean swapped = false;
                
                if (arr[j].getRollNumber().compareTo(arr[j + 1].getRollNumber()) > 0) {
                    // Swap
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapsInPass++;
                    totalSwaps++;
                    swapped = true;
                    
                    System.out.println("[DSA] Pass " + (i + 1) + ", Comparison " + (j + 1) + ": Swapped " +
                                     arr[j].getRollNumber() + " and " + arr[j + 1].getRollNumber());
                } else {
                    System.out.println("[DSA] Pass " + (i + 1) + ", Comparison " + (j + 1) + ": No swap needed");
                }
                
                steps.add(new SortStep(i + 1, "After comparison " + (j + 1), copyArray(arr), j, j + 1, swapped));
            }
            
            System.out.println("[DSA] Pass " + (i + 1) + " complete. Swaps in this pass: " + swapsInPass);
            
            if (swapsInPass == 0) {
                System.out.println("[DSA] No swaps in pass " + (i + 1) + ". Array is sorted!");
                break;
            }
        }
        
        System.out.println("[DSA] Bubble Sort complete. Total swaps: " + totalSwaps + ", Total comparisons: " + totalComparisons);
        return new SortResult(arr, totalSwaps, totalComparisons, steps);
    }
    
    // Selection Sort by Name with step-by-step trace
    public static SortResult selectionSortByName(StudentArray students) {
        System.out.println("[DSA] Starting Selection Sort by Name");
        List<SortStep> steps = new ArrayList<>();
        Student[] arr = students.getAll();
        int n = arr.length;
        int totalSwaps = 0;
        int totalComparisons = 0;
        
        steps.add(new SortStep(0, "Initial array", copyArray(arr), 0, 0, false));
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                totalComparisons++;
                
                if (arr[j].getName().compareToIgnoreCase(arr[minIndex].getName()) < 0) {
                    minIndex = j;
                    System.out.println("[DSA] Pass " + (i + 1) + ": New minimum found at index " + j + " (" + arr[j].getName() + ")");
                }
            }
            
            if (minIndex != i) {
                // Swap
                Student temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                totalSwaps++;
                
                System.out.println("[DSA] Pass " + (i + 1) + ": Swapped " + arr[minIndex].getName() + " with " + arr[i].getName());
                steps.add(new SortStep(i + 1, "After swap", copyArray(arr), i, minIndex, true));
            } else {
                System.out.println("[DSA] Pass " + (i + 1) + ": No swap needed, element already in correct position");
                steps.add(new SortStep(i + 1, "No swap needed", copyArray(arr), i, minIndex, false));
            }
        }
        
        System.out.println("[DSA] Selection Sort complete. Total swaps: " + totalSwaps + ", Total comparisons: " + totalComparisons);
        return new SortResult(arr, totalSwaps, totalComparisons, steps);
    }
    
    // Insertion Sort by Marks with step-by-step trace
    public static SortResult insertionSortByMarks(StudentArray students) {
        System.out.println("[DSA] Starting Insertion Sort by Marks");
        List<SortStep> steps = new ArrayList<>();
        Student[] arr = students.getAll();
        int n = arr.length;
        int totalShifts = 0;
        int totalComparisons = 0;
        
        steps.add(new SortStep(0, "Initial array", copyArray(arr), 0, 0, false));
        
        for (int i = 1; i < n; i++) {
            Student key = arr[i];
            int j = i - 1;
            int shifts = 0;
            
            System.out.println("[DSA] Pass " + i + ": Inserting " + key.getName() + " with marks " + key.getMarks());
            
            while (j >= 0 && arr[j].getMarks() > key.getMarks()) {
                totalComparisons++;
                arr[j + 1] = arr[j]; // Shift right
                j--;
                shifts++;
                System.out.println("[DSA] Pass " + i + ": Shifted element to the right");
            }
            
            if (j >= 0) {
                totalComparisons++;
            }
            
            arr[j + 1] = key;
            totalShifts += shifts;
            
            if (shifts > 0) {
                System.out.println("[DSA] Pass " + i + ": Inserted " + key.getName() + " at position " + (j + 1) + ". Shifts: " + shifts);
                steps.add(new SortStep(i, "After insertion", copyArray(arr), j + 1, i, true));
            } else {
                System.out.println("[DSA] Pass " + i + ": No shifts needed, element already in correct position");
                steps.add(new SortStep(i, "No shifts needed", copyArray(arr), j + 1, i, false));
            }
        }
        
        System.out.println("[DSA] Insertion Sort complete. Total shifts: " + totalShifts + ", Total comparisons: " + totalComparisons);
        return new SortResult(arr, totalShifts, totalComparisons, steps);
    }
    
    // Helper method to copy array
    private static Student[] copyArray(Student[] original) {
        Student[] copy = new Student[original.length];
        for (int i = 0; i < original.length; i++) {
            if (original[i] != null) {
                copy[i] = new Student(
                    original[i].getRollNumber(),
                    original[i].getName(),
                    original[i].getMarks(),
                    original[i].getPassword()
                );
                copy[i].setApproved(original[i].isApproved());
            }
        }
        return copy;
    }
    
    // Result class for sort operations
    public static class SortResult {
        private final Student[] sortedArray;
        private final int operations;
        private final int comparisons;
        private final List<SortStep> steps;
        
        public SortResult(Student[] sortedArray, int operations, int comparisons, List<SortStep> steps) {
            this.sortedArray = sortedArray;
            this.operations = operations;
            this.comparisons = comparisons;
            this.steps = steps;
        }
        
        public Student[] getSortedArray() { return sortedArray; }
        public int getOperations() { return operations; }
        public int getComparisons() { return comparisons; }
        public List<SortStep> getSteps() { return steps; }
    }
    
    // Step class for detailed trace
    public static class SortStep {
        private final int pass;
        private final String description;
        private final Student[] arrayState;
        private final int index1;
        private final int index2;
        private final boolean operationPerformed;
        
        public SortStep(int pass, String description, Student[] arrayState, int index1, int index2, boolean operationPerformed) {
            this.pass = pass;
            this.description = description;
            this.arrayState = arrayState;
            this.index1 = index1;
            this.index2 = index2;
            this.operationPerformed = operationPerformed;
        }
        
        public int getPass() { return pass; }
        public String getDescription() { return description; }
        public Student[] getArrayState() { return arrayState; }
        public int getIndex1() { return index1; }
        public int getIndex2() { return index2; }
        public boolean isOperationPerformed() { return operationPerformed; }
    }
}