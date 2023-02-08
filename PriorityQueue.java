import java.util.Arrays;

public class PriorityQueue {
    
    private int[] items = new int[5];
    int count = 0;

    public void add(int item){
        if (isFull())
            throw new IllegalStateException();

        var index = shiftItemsToInsert(item);
        items[index] = item;
        count++;
    }

    public int shiftItemsToInsert(int item){
        int i;
        for (i=count-1;i>=0;i--){
            if (items[i] > item){
                items[i+1] = items[i];
            }
            else
                break;
        }
        return i+1;
    }

    public int remove(){
        if (isEmpty())
            throw new IllegalStateException();
        
        var out = items[0];

        for(int i=0;i<count-1;i++){
            items[i]=items[i+1];
        }

        items[--count] = 0;

        return out;
    }

    public boolean isFull(){
        return count == items.length;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    @Override
    public String toString(){
        return Arrays.toString(items);
    }
}
