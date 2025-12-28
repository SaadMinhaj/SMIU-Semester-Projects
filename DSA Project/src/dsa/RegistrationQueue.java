package dsa;

import model.Student;

public class RegistrationQueue {
    private Student[] queue;
    private int capacity;
    private int size;
    private int front;
    private int rear;
    
    public RegistrationQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Student[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }
    
    // Enqueue - Add student to queue
    public boolean enqueue(Student student) {
        if (isFull()) {
            System.out.println("[DSA] RegistrationQueue is full. Cannot enqueue " + student.getName());
            return false;
        }
        
        rear = (rear + 1) % capacity;
        queue[rear] = student;
        size++;
        
        System.out.println("[DSA] Enqueued student at rear=" + rear + ", size=" + size + ", front=" + front);
        return true;
    }
    
    // Dequeue - Remove and return student from front
    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("[DSA] RegistrationQueue is empty. Cannot dequeue");
            return null;
        }
        
        Student student = queue[front];
        queue[front] = null; // Clear reference
        front = (front + 1) % capacity;
        size--;
        
        System.out.println("[DSA] Dequeued student from front=" + ((front - 1 + capacity) % capacity) + ", new front=" + front + ", size=" + size);
        return student;
    }
    
    // Peek - View front student without removing
    public Student peek() {
        if (isEmpty()) {
            System.out.println("[DSA] RegistrationQueue is empty. Nothing to peek");
            return null;
        }
        
        System.out.println("[DSA] Peeking at front=" + front + ": " + queue[front].getName());
        return queue[front];
    }
    
    // Get all students in queue (for display)
    public Student[] getAll() {
        if (isEmpty()) {
            return new Student[0];
        }
        
        Student[] result = new Student[size];
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            result[i] = queue[index];
        }
        
        System.out.println("[DSA] Retrieved " + size + " students from queue");
        return result;
    }
    
    // Check if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Check if queue is full
    public boolean isFull() {
        return size == capacity;
    }
    
    // Get current size
    public int getSize() {
        return size;
    }
    
    // Get capacity
    public int getCapacity() {
        return capacity;
    }
    
    // Get front index
    public int getFront() {
        return front;
    }
    
    // Get rear index
    public int getRear() {
        return rear;
    }
    
    // Display queue contents with pointer positions
    public void display() {
        System.out.println("[DSA] RegistrationQueue state:");
        System.out.println("  Capacity: " + capacity);
        System.out.println("  Size: " + size);
        System.out.println("  Front: " + front);
        System.out.println("  Rear: " + rear);
        System.out.println("  Contents:");
        
        for (int i = 0; i < capacity; i++) {
            String marker = "";
            if (i == front && i == rear) marker = " [F,R]";
            else if (i == front) marker = " [F]";
            else if (i == rear) marker = " [R]";
            
            System.out.println("    [" + i + "] " + (queue[i] != null ? queue[i].getName() : "null") + marker);
        }
    }
}