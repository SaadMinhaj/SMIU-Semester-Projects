package dsa;

import model.Student;

public class RegistrationQueue {
    private Student[] queue;
    private int front;
    private int rear;
    private int capacity;
    private int size;
    
    public RegistrationQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Student[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        System.out.println("[DSA] RegistrationQueue initialized with capacity: " + capacity);
    }
    
    // Enqueue operation
    public boolean enqueue(Student student) {
        if (isFull()) {
            System.out.println("[DSA] Queue is full, cannot enqueue: " + student.getName());
            return false;
        }
        
        rear = (rear + 1) % capacity;
        queue[rear] = student;
        size++;
        
        System.out.println("[DSA] Enqueued student: " + student.getName() + 
                         " at position " + rear);
        System.out.println("[DSA] Queue size: " + size + ", Front: " + front + ", Rear: " + rear);
        return true;
    }
    
    // Dequeue operation
    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("[DSA] Queue is empty, cannot dequeue");
            return null;
        }
        
        Student student = queue[front];
        System.out.println("[DSA] Dequeued student: " + student.getName() + 
                         " from position " + front);
        
        queue[front] = null; // Clear the position
        front = (front + 1) % capacity;
        size--;
        
        System.out.println("[DSA] Queue size after dequeue: " + size + 
                         ", Front: " + front + ", Rear: " + rear);
        return student;
    }
    
    // Peek front element
    public Student peek() {
        if (isEmpty()) {
            System.out.println("[DSA] Queue is empty, nothing to peek");
            return null;
        }
        
        Student student = queue[front];
        System.out.println("[DSA] Peek - Front student: " + student.getName() + 
                         " at position " + front);
        return student;
    }
    
    // Check if queue is empty
    public boolean isEmpty() {
        boolean empty = size == 0;
        System.out.println("[DSA] Queue empty check: " + empty);
        return empty;
    }
    
    // Check if queue is full
    public boolean isFull() {
        boolean full = size == capacity;
        if (full) {
            System.out.println("[DSA] Queue full check: " + full);
        }
        return full;
    }
    
    // Get current size
    public int getSize() {
        System.out.println("[DSA] Current queue size: " + size);
        return size;
    }
    
    // Display queue contents
    public void display() {
        System.out.println("\n[DSA] Displaying registration queue (" + size + " requests):");
        
        if (isEmpty()) {
            System.out.println("[DSA] Queue is empty");
            return;
        }
        
        int current = front;
        for (int i = 0; i < size; i++) {
            Student student = queue[current];
            System.out.println("[DSA] Position " + current + ": " + 
                             student.getRollNumber() + " - " + student.getName());
            current = (current + 1) % capacity;
        }
        
        System.out.println("[DSA] Queue status - Front: " + front + 
                         ", Rear: " + rear + ", Size: " + size);
    }
    
    // Get queue capacity
    public int getCapacity() {
        return capacity;
    }
    
    // Clear the queue
    public void clear() {
        System.out.println("[DSA] Clearing registration queue");
        for (int i = 0; i < capacity; i++) {
            queue[i] = null;
        }
        front = 0;
        rear = -1;
        size = 0;
        System.out.println("[DSA] Queue cleared successfully");
    }
}