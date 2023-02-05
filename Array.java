public class Array {
    
    private int[] arr;
    private int count = 0;
    public Array(int number){
        arr = new int[number];
    }
    public int indexOf(int number){
        for(int i=0;i<count;i++){
            if (arr[i]==number)
                return i;
        }
        return -1;
    }
    public void insert(int number){
        resizeIfRequired();
        
        arr[count++] = number;
    }
    public void removeAt(int index){
        if (index < 0 || index >= count){
            throw new IllegalArgumentException();
        }
        for (int i=index;i<count;i++)
            arr[i]=arr[i+1];
        count--;
    }
    public void print(){
        for (int i=0;i<count;i++){
            System.out.println(arr[i]);
        }
    }
    public int max(){
        int number = Integer.MIN_VALUE;
        for (int i=0;i<count;i++){
            if (arr[i] > number)
                number = arr[i];
        }
        return number;
    }
    public Array intersect(Array other){
        var intersection = new Array(count);
        
        for (int item: arr)
            if (other.indexOf(item) >= 0)
                intersection.insert(item);
        
        return intersection;
    }
    public void reverse(){
        int[] result = new int[count];
        
        for (int i = 0; i < count; i++)
            result[count - i - 1] = arr[i];
        
        arr = result;
    }
    public void insertAt(int item,int index){
        if (index > count || index < 0)
            throw new IllegalArgumentException();
        
            resizeIfRequired();
        
        for (int i = count - 1; i >= index; i--){
            arr[i + 1]=arr[i];
        }
        arr[index] = item;
        count++;
    }
    public void resizeIfRequired(){
        if (count == arr.length){
            int[] newArr = new int[count * 2];

            for (int i=0;i<count;i++)
                newArr[i] = arr[i];
            
            arr = newArr;
        }
    }
}
