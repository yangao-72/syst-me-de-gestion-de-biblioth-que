package data_structures;

 // Implémentation d'une liste chaînée pour gérer l'historique des emprunts.
public class LinkedList<T> {


    private class Node {
        T data;     
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size; 
    
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    

    public void add(T data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public void addFirst(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    public T getFirst() {
        return head != null ? head.data : null;
    }
    
    public T getLast() {
        if (head == null) {
            return null;
        }
        
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }
    
    public boolean contains(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        
    
        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    


    public void clear() {
        head = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }
    

    public boolean isEmpty() {
        return size == 0;
    }
    

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
    
    public void printList() {
        Node current = head;
        int index = 0;
        while (current != null) {
            System.out.println(index + ": " + current.data);
            current = current.next;
            index++;
        }
    }
}
