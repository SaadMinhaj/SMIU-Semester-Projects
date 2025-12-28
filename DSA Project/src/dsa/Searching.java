package dsa;

import model.Student;
import java.util.ArrayList;
import java.util.List;

public class Searching {
    
    // Linear Search by Roll Number with step-by-step trace
    public static SearchResult linearSearchByRollNumber(StudentArray students, String rollNumber) {
        System.out.println("[DSA] Starting Linear Search by Roll Number for: " + rollNumber);
        List<SearchStep> steps = new ArrayList<>();
        
        for (int i = 0; i < students.getSize(); i++) {
            Student student = students.get(i);
            boolean match = student.getRollNumber().equals(rollNumber);
            
            steps.add(new SearchStep(i, student.getRollNumber(), student.getName(), match));
            System.out.println("[DSA] Index " + i + ": Comparing " + student.getRollNumber() + " with " + rollNumber + " -> " + (match ? "MATCH!" : "No match"));
            
            if (match) {
                System.out.println("[DSA] Linear Search successful! Found at index: " + i);
                return new SearchResult(true, student, i, steps);
            }
        }
        
        System.out.println("[DSA] Linear Search completed. Element not found. Comparisons made: " + students.getSize());
        return new SearchResult(false, null, -1, steps);
    }
    
    // Linear Search by Name with step-by-step trace
    public static SearchResult linearSearchByName(StudentArray students, String name) {
        System.out.println("[DSA] Starting Linear Search by Name for: " + name);
        List<SearchStep> steps = new ArrayList<>();
        
        for (int i = 0; i < students.getSize(); i++) {
            Student student = students.get(i);
            boolean match = student.getName().equalsIgnoreCase(name);
            
            steps.add(new SearchStep(i, student.getRollNumber(), student.getName(), match));
            System.out.println("[DSA] Index " + i + ": Comparing '" + student.getName() + "' with '" + name + "' -> " + (match ? "MATCH!" : "No match"));
            
            if (match) {
                System.out.println("[DSA] Linear Search successful! Found at index: " + i);
                return new SearchResult(true, student, i, steps);
            }
        }
        
        System.out.println("[DSA] Linear Search completed. Element not found. Comparisons made: " + students.getSize());
        return new SearchResult(false, null, -1, steps);
    }
    
    // Binary Search by Roll Number (requires sorted array) with step-by-step trace
    public static SearchResult binarySearchByRollNumber(StudentArray students, String rollNumber) {
        System.out.println("[DSA] Starting Binary Search by Roll Number for: " + rollNumber);
        List<SearchStep> steps = new ArrayList<>();
        
        int left = 0;
        int right = students.getSize() - 1;
        int iterations = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Student midStudent = students.get(mid);
            int comparison = midStudent.getRollNumber().compareTo(rollNumber);
            iterations++;
            
            boolean match = (comparison == 0);
            steps.add(new SearchStep(mid, midStudent.getRollNumber(), midStudent.getName(), match));
            
            System.out.println("[DSA] Iteration " + iterations + ": mid=" + mid + 
                             ", comparing " + midStudent.getRollNumber() + " with " + rollNumber + 
                             " -> " + (comparison == 0 ? "MATCH!" : comparison < 0 ? "Go right" : "Go left"));
            
            if (comparison == 0) {
                System.out.println("[DSA] Binary Search successful! Found at index: " + mid + 
                                 " in " + iterations + " iterations");
                return new SearchResult(true, midStudent, mid, steps);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println("[DSA] Binary Search completed. Element not found. Iterations: " + iterations);
        return new SearchResult(false, null, -1, steps);
    }
    
    // Binary Search by Name (requires sorted array) with step-by-step trace
    public static SearchResult binarySearchByName(StudentArray students, String name) {
        System.out.println("[DSA] Starting Binary Search by Name for: " + name);
        List<SearchStep> steps = new ArrayList<>();
        
        int left = 0;
        int right = students.getSize() - 1;
        int iterations = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Student midStudent = students.get(mid);
            int comparison = midStudent.getName().compareToIgnoreCase(name);
            iterations++;
            
            boolean match = (comparison == 0);
            steps.add(new SearchStep(mid, midStudent.getRollNumber(), midStudent.getName(), match));
            
            System.out.println("[DSA] Iteration " + iterations + ": mid=" + mid + 
                             ", comparing '" + midStudent.getName() + "' with '" + name + "'" +
                             " -> " + (comparison == 0 ? "MATCH!" : comparison < 0 ? "Go right" : "Go left"));
            
            if (comparison == 0) {
                System.out.println("[DSA] Binary Search successful! Found at index: " + mid + 
                                 " in " + iterations + " iterations");
                return new SearchResult(true, midStudent, mid, steps);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println("[DSA] Binary Search completed. Element not found. Iterations: " + iterations);
        return new SearchResult(false, null, -1, steps);
    }
    
    // Result class for search operations
    public static class SearchResult {
        private final boolean found;
        private final Student student;
        private final int index;
        private final List<SearchStep> steps;
        
        public SearchResult(boolean found, Student student, int index, List<SearchStep> steps) {
            this.found = found;
            this.student = student;
            this.index = index;
            this.steps = steps;
        }
        
        public boolean isFound() { return found; }
        public Student getStudent() { return student; }
        public int getIndex() { return index; }
        public List<SearchStep> getSteps() { return steps; }
    }
    
    // Step class for detailed trace
    public static class SearchStep {
        private final int index;
        private final String rollNumber;
        private final String name;
        private final boolean match;
        
        public SearchStep(int index, String rollNumber, String name, boolean match) {
            this.index = index;
            this.rollNumber = rollNumber;
            this.name = name;
            this.match = match;
        }
        
        public int getIndex() { return index; }
        public String getRollNumber() { return rollNumber; }
        public String getName() { return name; }
        public boolean isMatch() { return match; }
    }
}