public class MinPriorityQueue {
    private MinHeap heap = new MinHeap();

    public void add(int key, String string){
        heap.insert(key,string);
    }

    public String remove(){
        return heap.remove();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }
}
