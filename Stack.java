import java.util.Arrays;

public class Stack {
    
    private int top = 0;
    private int[] arr = new int[5];

    public void push(int item){
        if (top == arr.length)
            throw new StackOverflowError();

        arr[top++] = item;
    }

    public int pop(){
        if (isEmpty()) 
            throw new IllegalStateException();
        
        return arr[--top];
    }

    public int peek(){
        if (isEmpty()) 
            throw new IllegalStateException();
        
        return arr[top - 1];
    }

    @Override
    public String toString(){
        var content = Arrays.copyOfRange(arr, 0, top);
        return Arrays.toString(content);
    }

    public boolean isEmpty(){
        return top == 0;
    }

}
