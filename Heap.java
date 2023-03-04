import java.util.Arrays;

public class Heap {
    private int[] items = new int[10];
    private int size;

    public void insert(int item){
        if (isFull())
            throw new IllegalStateException();
        
        items[size++] = item;
        bubbleUp();
    }

    private void bubbleUp(){
        var index = size - 1;
        while (index > 0 && items[index] > items[parent(index)]){
            swapItems(index, parent(index));
            index = parent(index);
        }
        
        
        // if (index == 0)
        //     return;
        // var parent = (index - 1) / 2;
        // if (items[index] > items[parent]){
        //     swapItems(index,parent);
        //     bubbleUp(parent);
        // }
        // return;
    }

    private int parent(int index){
        return (index - 1) / 2;
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();

        var root = items[0];
        items[0] = items[--size];
        bubbleDown();

        return root;
    }

    private void bubbleDown() {
        var index = 0;
        while (index <= size && !isValidParent(index)){
            var largerChildIndex = largerChildIndex(index);
            swapItems(index, largerChildIndex);
            index = largerChildIndex;
        }
        
        // var left = parent * 2 + 1;
        // var right = parent * 2 + 2;

        // if (items[parent] < items[left] || items[parent] < items[right]) {
        //     if (items[left] > items[right]) {
        //         swapItems(parent, left);
        //         bubbleDown(left);
        //     }
        //     else {
        //         swapItems(parent, right);
        //         bubbleDown(right);
        //     }
        // }
        // return;
    }

    private void swapItems(int index, int target) {
        var temp = items[index];
        items[index] = items[target];
        items[target] = temp;
    }

    private int leftChildIndex(int index){
        return index * 2 + 1;
    }

    private int rightChildIndex(int index){
        return index * 2 + 2;
    }

    private int leftChild(int index){
        return items[leftChild(index)];
    }

    private int rightChild(int index){
        return items[rightChild(index)];
    }

    private boolean hasLeftChild(int index){
        return leftChildIndex(index) <= size;
    }

    private boolean hasRightChild(int index){
        return rightChildIndex(index) <= size;
    }

    private boolean isValidParent(int index){
        if (!hasLeftChild(index))
            return true;

        if (!hasRightChild(index))
            return items[index] >= leftChild(index);

        return items[index] >= leftChild(size) && items[index] >= rightChild(size);
    }

    private int largerChildIndex(int index){
        if (!hasLeftChild(index))
            return index;
        
        if (!hasRightChild(index))
            return leftChildIndex(index);

        return (leftChild(index) > rightChild(index)) ? leftChildIndex(index) : rightChildIndex(index);
    }

    public boolean isFull(){
        return size == items.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int max(){
        if (isEmpty())
            throw new IllegalStateException();
        
        return items[0];
    }

    public static boolean isMaxHeap(int[] array){
        return isMaxHeap(array, 0);
                
        // for (int i = 0; i < array.length; i++){
        //     var leftChildIndex = i * 2 + 1;
        //     if (leftChildIndex < array.length && array[i] < array[leftChildIndex])
        //         return false;
            
        //     var rightChildIndex = i * 2 + 2; 
        //     if (rightChildIndex < array.length && array[i] < array[rightChildIndex])
        //         return false;
        // }

        // return true;
    }

    private static boolean isMaxHeap(int[] array, int index){
        var lastParentIndex = (array.length - 1) / 2;
        if (index > lastParentIndex)
            return true;
        
        var leftIndex = index * 2 + 1;
        var rightIndex = index * 2 + 2;

        boolean isValidParent = array[index] >= array[leftIndex] && array[index] >= array[rightIndex];

        return isValidParent && isMaxHeap(array, leftIndex) && isMaxHeap(array, rightIndex);
    }

    @Override
    public String toString(){
        return Arrays.toString(items);
    }
}
