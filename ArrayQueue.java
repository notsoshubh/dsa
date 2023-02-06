import java.util.Arrays;

public class ArrayQueue {
    
    private int[] items;
    private int front;
    private int rear;
    private int count;
    private int size;

    public ArrayQueue(int capacity){
        items = new int[capacity];
        size = capacity;
    }

    public boolean isFull(){
        return count == size;
    }

    public void enqueue(int item){
        if (isFull())
            throw new IllegalStateException();
        
        items[rear] = item;
        rear = (rear + 1) % size;
        count++;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int dequeue(){
        if (isEmpty())
            throw new IllegalStateException();
        
        var first = items[front];
        items[front] = 0;
        front = (front + 1) % size;
        count--;

        return first;
    }

    @Override
    public String toString(){
        return Arrays.toString(items);
    }
}
