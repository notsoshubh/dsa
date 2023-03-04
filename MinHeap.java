public class MinHeap {
    private class Node {
        private int key;
        private String value;

        public Node(int key, String value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return key + "-" + value;
        }
    }

    Node[] nodes = new Node[10];
    int size;

    public void insert(int key, String value){
        if (isFull())
            throw new IllegalStateException();
        
        nodes[size++] = new Node(key, value);
        bubbleUp();
        
    }

    public String remove(){
        if (isEmpty())
            throw new IllegalStateException();
        
        var root = nodes[0].value;
        nodes[0] = nodes[--size];

        bubbleDown();
        return root;
    }

    private void bubbleDown(){
        var index = 0;
        while (index <= size && !isValidParent(index)){
            var smallerChildIndex = smallerChildIndex(index);
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }

    }

    private int smallerChildIndex(int index){
        if(!hasLeftChild(index))
            return index;
        if(!hasRightChild(index))
            return leftChildIndex(index);
        return (leftChild(index).key < rightChild(index).key) ? leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index){
        if (!hasLeftChild(index))
            return true;
        if (!hasRightChild(index))
            return nodes[index].key <= leftChild(index).key;
        return nodes[index].key <= leftChild(index).key && nodes[index].key <= rightChild(index).key;
    }

    private int leftChildIndex(int index){
        return index * 2 + 1;
    }

    private int rightChildIndex(int index){
        return index * 2 + 2;
    }

    private Node leftChild(int index){
        return nodes[leftChildIndex(index)];
    }

    private Node rightChild(int index){
        return nodes[rightChildIndex(index)];
    }
    
    private boolean hasLeftChild(int index){
        return leftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index){
        return rightChildIndex(index) < size;
    }

    private void bubbleUp(){
        var index = size - 1;
        while (index > 0 && nodes[index].key < nodes[parent(index)].key){
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private int parent(int index){
        return (index - 1) / 2;
    }


    public boolean isFull(){
        return size == nodes.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void swap(int first, int second){
        var temp = nodes[first];
        nodes[first] = nodes[second];
        nodes[second] = temp;
    }

}
