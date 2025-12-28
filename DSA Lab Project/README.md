# Student Management System - DSA Lab Project

## Project Overview
This is a console-based Student Management System built entirely using manual implementation of Data Structures and Algorithms. The project demonstrates core DSA concepts including arrays, queues, sorting algorithms, searching algorithms.

## Features

### Student Features
- Login with roll number and password
- View personal profile
- Request registration (if not registered)
- Check registration approval status
- View exam results and grades

### Admin Features
- Login with admin credentials
- View registration queue
- Approve registration requests
- Add/delete/update students
- Search students (Linear & Binary search)
- Sort students (Bubble, Selection, Insertion sort)
- Generate reports (Total students, Average marks, Top scorer)

## Data Structures Implemented

### 1. Custom Dynamic Array (StudentArray)
- Manual implementation with dynamic resizing
- Insert, delete, update operations
- No built-in ArrayList used

### 2. Queue (RegistrationQueue)
- Array-based queue implementation
- Enqueue and dequeue operations
- Handles registration requests

### 3. Sorting Algorithms
- **Bubble Sort**: Sort by Roll Number
- **Selection Sort**: Sort by Name
- **Insertion Sort**: Sort by Marks
- All algorithms show step-by-step process

### 4. Searching Algorithms
- **Linear Search**: Search in unsorted array
- **Binary Search**: Search in sorted array
- Both algorithms show index-by-index checking

## Project Structure

```
src/
├── model/
│   ├── Student.java      # Student entity class
│   └── Admin.java        # Admin entity class
├── dsa/
│   ├── StudentArray.java     # Custom dynamic array
│   ├── RegistrationQueue.java # Queue implementation
│   ├── Sorting.java          # Sorting algorithms
│   └── Searching.java        # Searching algorithms
└── main/
    └── MainMenu.java     # Console application driver
```

---

**System Architecture Diagram**
![System Architecture Diagram](/DSA_Lab_Student_Management/images/System%20Architechure.png)

---

## Requirements
- Java 17 or above
- VS Code or any Java IDE
- No external libraries required
- No GUI frameworks used

## Compilation and Execution

### Using Command Line

1. **Navigate to project directory:**
   ```cmd
   cd DSA_Lab_Student_Management
   ```

2. **Compile all Java files:**
   ```cmd
   javac -d bin src\model\*.java src\dsa\*.java src\main\*.java
   ```

3. **Run the application:**
   ```cmd
   java -cp bin main.MainMenu
   ```

### Using VS Code

1. Open the project folder in VS Code
2. Install Java Extension Pack if not already installed
3. Open `MainMenu.java`
4. Click "Run" or press F5

---

**Use Case Diagram**
![Use Case Diagram](/DSA_Lab_Student_Management/images/Use%20Case%20Diagram.png)

---

## Default Credentials

### Admin Login
- Username: `admin`
- Password: `admin123`

### Sample Students (Pre-loaded)
1. Roll: S001, Name: Rao Hamza Tariq, Password: hamza123, Marks: 85.5
2. Roll: S002, Name: Imtiaz Maqbook, Password: imtiaz123, Marks: 92.0
3. Roll: S003, Name: Shahmeer Baqai, Password: shahmeer123, Marks: 78.3
4. Roll: S004, Name: Muhammad Saad, Password: saad123, Marks: 88.7

---

**Class Diagram**
![Class Diagram](/DSA_Lab_Student_Management/images/Class%20Diagram.png)

---

## How to Use

### For Students:
1. Select "Login as Student"
2. If registered, enter credentials
3. If not registered, you can request registration
4. View profile, marks, and approval status

### For Admin:
1. Select "Login as Admin"
2. Use default credentials (admin/admin123)
3. Manage registration queue
4. Perform CRUD operations on students
5. Use sorting and searching features
6. Generate reports

---

**Sequence Diagram**
![Sequence Diagram](/DSA_Lab_Student_Management/images/Sequence%20Diagram.png)

---

## DSA Implementation Details

### Console Output Format
All DSA operations print detailed information:
- `[DSA]` prefix for all DSA-related output
- Current index being accessed
- Insert/delete positions
- Sorting swaps and comparisons
- Queue front/rear movements
- Hash calculation steps

### Key Features Demonstrated
- **Dynamic Array Resizing**: Automatic capacity doubling
- **Queue Operations**: Proper front/rear management
- **Sorting Visualization**: Pass-by-pass output
- **Search Process**: Index-by-index checking
- **Hash Functions**: Step-by-step calculation

---

**Activity Diagram**
![Activity Diagram](/DSA_Lab_Student_Management/images/Activity%20Diagram.png)

---

## Evaluation Points

This project demonstrates:
1. Manual implementation of data structures
2. Proper algorithm implementation
3. Console-based menu system
4. Separation of concerns
5. DSA operation logging
6. No built-in collections used
7. No external libraries

---

**Context-Level DFD**
![Context-Level DFD](/DSA_Lab_Student_Management/images/DFD%20Level%200.png)

---

## Sample Console Output

```
[DSA] StudentArray initialized with capacity: 10
[DSA] RegistrationQueue initialized with capacity: 20
[SYSTEM] Adding sample students for demonstration...
[DSA] Inserted student at index 0: Alice Johnson
[DSA] Current size: 1

=== STUDENT MANAGEMENT SYSTEM ===
DSA Lab Project - Console Application
=====================================

--- MAIN MENU ---
1. Login as Student
2. Login as Admin
3. Exit
Enter your choice: 
```

---

**Level 1 DFD**
![Level 1 DFD](/DSA_Lab_Student_Management/images/DFD%20Level%201.png)

---

## Notes for Lab Evaluation

1. All data structures are manually implemented
2. No Java built-in collections (ArrayList, LinkedList, etc.) used
3. No external libraries or frameworks
4. Console output clearly shows DSA operations
5. Menu-driven interface for easy testing
6. Sample data pre-loaded for demonstration

## Troubleshooting

1. **Java version error**: Ensure Java 17+ is installed
2. **Compilation error**: Check all files are in correct package structure
3. **Runtime error**: Verify all classes are compiled in bin directory
4. **Access issues**: Use default credentials provided above

---

## Contributions

### Project Contributors

The Student Management System was developed collaboratively by the following team members:

- **[Rao Hamza Tariq (CSC-24F-042)](https://github.com/RaoHamzaTariq/)** 
- **[Muhammad Saad (CSC-24F-025)](https://github.com/SaadMinhaj)**
