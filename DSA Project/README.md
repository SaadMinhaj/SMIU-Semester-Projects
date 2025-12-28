# Student Management System - GUI Application

## Overview
This is a GUI-based Student Management System built in Java that demonstrates core Data Structures and Algorithms (DSA) concepts including arrays, queues, searching, and sorting algorithms. The system provides role-based access control for students and administrators.

## Features

### Data Structures Implemented
- **StudentArray**: Custom dynamic array with automatic resizing
- **RegistrationQueue**: Circular queue for managing registration requests

### Algorithms Implemented
- **Searching**: Linear Search, Binary Search (with step-by-step trace)
- **Sorting**: Bubble Sort, Selection Sort, Insertion Sort (with step-by-step trace)

### User Roles

#### Student Features
- Login with roll number and password
- View personal profile (name, roll number, marks, status)
- Register for the system (pending admin approval)
- Real-time DSA operation logging

#### Admin Features
- Full CRUD operations on student records
- Manage registration approval queue
- Execute and visualize searching algorithms
- Execute and visualize sorting algorithms with step-by-step trace
- Generate comprehensive reports with statistics
- Real-time DSA operation logging

## Project Structure

```
StudentManagementSystem-GUI/
├── src/
│   ├── main/
│   │   └── StudentManagementSystem.java  # Main application class
│   ├── model/
│   │   ├── Student.java                  # Student entity class
│   │   └── Admin.java                    # Admin entity class
│   ├── dsa/
│   │   ├── StudentArray.java             # Dynamic array implementation
│   │   ├── RegistrationQueue.java        # Circular queue implementation
│   │   ├── Searching.java                # Search algorithms with trace
│   │   └── Sorting.java                  # Sort algorithms with trace
│   └── gui/
│       ├── LoginFrame.java               # Login interface
│       ├── StudentDashboard.java         # Student dashboard
│       ├── AdminDashboard.java           # Admin dashboard
│       ├── SearchDialog.java             # Search visualization
│       └── SortDialog.java               # Sort visualization
├── README.md
└── compile.sh                            # Compilation script (Linux/Mac)
```

## Compilation and Execution

### Using Command Line (Linux/Mac/Windows)

1. Navigate to the project directory:
```bash
cd StudentManagementSystem-GUI
```

2. Compile all Java files:
```bash
javac -d . src/main/*.java src/model/*.java src/dsa/*.java src/gui/*.java
```

3. Run the application:
```bash
java main.StudentManagementSystem
```

### Using IDE (Eclipse/IntelliJ/VS Code)

1. Create a new Java project
2. Copy the `src` folder to your project
3. Set the main class to `main.StudentManagementSystem`
4. Run the application

## Default Credentials

### Admin Login
- **Username**: `admin`
- **Password**: `admin123`

### Student Login (Pre-loaded)
- **Roll Number**: `0001`
- **Password**: `saad123`

Other pre-loaded students:
- 0002, hammad123 (Muhammad Hammad)
- 0003, ali123 (Muhammad Ali)
- 0004, jawad123 (Muhammad Jawad)

## Usage Guide

### For Students

1. **Registration**:
   - Click "Register" on the login screen
   - Fill in roll number, name, marks, and password
   - Wait for admin approval

2. **Login**:
   - Enter your roll number and password
   - View your dashboard with personal information

### For Admins

1. **Login**:
   - Use admin credentials to access the admin dashboard

2. **Student Management**:
   - View all students in a table
   - Add new students directly
   - Update existing student information
   - Delete students

3. **Registration Queue**:
   - View pending registration requests
   - Approve or reject registrations
   - See queue operations (enqueue/dequeue) in the console

4. **Search Algorithms**:
   - Access from the Algorithms tab
   - Choose search type (by roll number or name)
   - Select algorithm (Linear or Binary search)
   - View step-by-step execution trace

5. **Sort Algorithms**:
   - Access from the Algorithms tab
   - Choose sort criteria (roll number, name, or marks)
   - Select algorithm (Bubble, Selection, or Insertion sort)
   - View before/after comparison
   - See detailed step-by-step trace with swaps/comparisons

6. **Reports**:
   - Generate comprehensive system reports
   - View statistics (total students, averages, top scorers)
   - See data structure status (array size, queue state)

## DSA Operation Logging

The system logs all DSA operations in real-time to help you understand how algorithms work:

- **Array Operations**: Insertions, deletions, resizing
- **Queue Operations**: Enqueue, dequeue, pointer movements
- **Search Operations**: Index-by-index comparisons
- **Sort Operations**: Pass-by-pass state, swap counts

Look for the green console area in each window to see the [DSA] prefixed logs.

## Educational Value

This project demonstrates:

1. **Manual Data Structure Implementation**: No built-in Java collections used
2. **Algorithm Visualization**: Step-by-step execution traces
3. **Memory Management**: Dynamic array resizing, circular queue pointers
4. **Object-Oriented Design**: Clean separation of concerns
5. **GUI Development**: Swing-based user interface
6. **Role-Based Access Control**: Different interfaces for different users

## Technical Details

- **Language**: Java SE 17+
- **GUI Framework**: Swing
- **Architecture**: MVC pattern
- **Time Complexities**:
  - Array insertion: O(1) amortized
  - Array deletion: O(n)
  - Linear Search: O(n)
  - Binary Search: O(log n)
  - Bubble/Selection/Insertion Sort: O(n²)

## Future Enhancements

- File persistence for data storage
- Hash table implementation for faster search
- Additional sorting algorithms (QuickSort, MergeSort)
- Multi-threading support
- Database integration
- Enhanced GUI with JavaFX

## Author

Created for Data Structures and Algorithms course project.
Demonstrates fundamental computer science concepts through practical implementation.