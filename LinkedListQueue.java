import java.util.Arrays;

public class LinkedListQueue {
    
    private class Node{
        private int value;
        private Node next;

        public Node(int value){
            this.value = value;
        }
    }

    private Node front;
    private Node rear;
    private int count;

    public void enqueue(int item){
        var node = new Node(item);
        if (isEmpty())
            front = rear = node;
        else {
            rear.next = node;
            rear = node;
        }
        count++;
    }

    public int dequeue(){
        if (isEmpty())
            throw new IllegalStateException();
        
        var out = front.value;
        
        if (front == rear){
            front = rear = null;
        }
        else{
            var second = front.next;
            front.next = null;
            front = second;
        }

        count--;
        return out;
    }

    public int peek(){
        return front.value;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public String toString(){
        int[] array = new int[count];
        var current = front;
        int i = 0; 
        while (current != null){
            array[i++] = current.value;
            current = current.next;
        }

        return Arrays.toString(array);
    }

}
