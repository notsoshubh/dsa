import java.util.Arrays;

public class TwoStacks {
    
    private int[] items = new int[10];
    private int count1 = 0;
    private int count2 = 0;

    public void push1(int item){
        if (count1 + count2 > items.length)
            throw new StackOverflowError();
        
        items[count1++] = item;
    }

    public void push2(int item){
        if (count1 + count2 > items.length)
            throw new StackOverflowError();

        items[items.length - count2++ - 1] = item;
    }

    public int pop1(){
        if (count1 == 0)
            throw new IllegalStateException();
        
        return items[--count1];
    }

    public int pop2(){
        if (count2 == 0)
            throw new IllegalStateException();

        return items[items.length - count2--];
    }

    public boolean isFull1(){
        return (count1 + count2 > items.length);
    }

    public boolean isFull2(){
        return (count1 + count2 > items.length);
    }

    public boolean isEmpty1(){
        return count1 == 0;
    }

    public boolean isEmpty2(){
        return count2 == 0;
    }

    @Override
    public String toString(){
        return Arrays.toString(items);
    }
}
