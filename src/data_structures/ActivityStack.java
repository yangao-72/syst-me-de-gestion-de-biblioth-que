package data_structures;


 // Implémentation d'une pile (stack) pour gérer les activités récentes.

public class ActivityStack<T>{
    
    private LinkedList<T> stack;
    private int maxSize;  
    
    public ActivityStack() {
        this.stack = new LinkedList<>();
        this.maxSize = Integer.MAX_VALUE;
    }
    
    public ActivityStack(int maxSize) {
        this.stack = new LinkedList<>();
        this.maxSize = maxSize;
    }
    
    public void push(T activity) {
        
        if (stack.size() >= maxSize) {
            removeBottom();
        }
        
        stack.addFirst(activity);
    }
    
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        
        T activity = stack.getFirst();
        removeTop();
        return activity;
    }
    
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.getFirst();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public int size() {
        return stack.size();
    }
    
    public void clear() {
        stack.clear();
    }



    public void printStack() {
        System.out.println("\n=== Activités Récentes (Plus récent → Plus ancien) ===");
        if (isEmpty()) {
            System.out.println("Aucune activité enregistrée.");
            return;
        }
        
        
        Object[] activities = stack.toArray();
        for (int i = activities.length - 1; i >= 0; i--) {
            System.out.println((activities.length - i) + ". " + activities[i]);
        }
    }
    
    public void printRecent(int n) {
        System.out.println("\n=== " + n + " Activités Récentes ===");
        if (isEmpty()) {
            System.out.println("Aucune activité enregistrée.");
            return;
        }
        
        int count = Math.min(n, stack.size());
        Object[] activities = stack.toArray();
        
        for (int i = activities.length - 1; i >= activities.length - count; i--) {
            System.out.println((activities.length - i) + ". " + activities[i]);
        }
    }
    



    private void removeTop() {
    
        if (stack.size() <= 1) {
            stack.clear();
            return;
        }
        
        LinkedList<T> newStack = new LinkedList<>();
        Object[] elements = stack.toArray();
        for (int i = 1; i < elements.length; i++) {
            newStack.add((T)elements[i]);
        }
        this.stack = newStack;
    }
    
    
    private void removeBottom() {
        Object[] elements = stack.toArray();
        if (elements.length > 1) {
            LinkedList<T> newStack = new LinkedList<>();
            for (int i = 1; i < elements.length; i++) {
                newStack.add((T) elements[i]);
            }
            this.stack = newStack;
        }
    }
    
    public Object[] toArray() {
        return stack.toArray();
    }
}
