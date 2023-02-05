import java.util.NoSuchElementException;

public class LinkedList {

    private class Node {
        private int value;
        private Node next;

        public Node(int item){
            value = item;
        }
    }
    private Node first;
    private Node last;
    private int count = 0;

    public void addLast(int item){
        var node = new Node(item);
        if (isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = node;
        }
        count++;
    }
    public void addFirst(int item){
        var node = new Node(item);
        if (isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
        count++;
    }
    public void deleteLast(){
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else {
            var previous = getPrevious(last);
            last = previous;
            last.next = null;
        }
        count--;
    }
    public void deleteFirst(){
        if (isEmpty())
            throw new NoSuchElementException();
        
        if (first == last)
            first = last = null;
        
        else {
            var node = first.next;
            first.next = null;
            first = node;
        }
        
        count--;
    }
    public boolean contains(int item){
        return indexOf(item) != -1;
    }
    public int indexOf(int item){
        var node = first;
        int index = 0;
        while (node != null){
            if (node.value == item)
                return index;
            node = node.next;
            index++;
        }
        return -1;
    }
    public void print(){
        var node = first;
        while (true){
            System.out.println(node.value);
            if (node == last)
                break;
            else
                node = node.next;
        }
    }

    private boolean isEmpty() {
        return first == null;
    }

    private Node getPrevious(Node node){
        var current = first;
        while (current != null){
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }

    public int size(){
        return count;
    }

    public int[] toArray(){
        int[] array = new int[count];
        var current = first;
        var index = 0;
        
        while(current != null){
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

    public void reverse(){
        if (isEmpty()) return;

        var current = first;
        var before = last.next;

        while(current != null){
            var after = current.next;
            current.next = before;
            before = current;
            current = after;
        }
        last = first;
        first = before;
    }

    public int getKthFromTheEnd(int k){
        if (isEmpty() || k <= 0)
            throw new IllegalStateException();
        
        var one = first;
        var two = first;
        var count = 0;

        while (two.next != null){
            if (count != k - 1){
                two = two.next;
                count++;
            }
            else{
                two = two.next;
                one = one.next;
            }
        }
        
        if (count != k - 1)
            throw new IllegalArgumentException();
        
        return one.value;

    }

    public void printMiddle(){
        if (isEmpty()) throw new IllegalStateException();

        var one = first;
        var two = first;
        
        while (two != last && two.next != last){
            two = two.next.next;
            one = one.next;            
        }

        if (two == last)
            System.out.println(one.value);
        else
            System.out.println(one.value + " " + one.next.value);
    }

    public boolean hasLoop(){
        if (isEmpty()) throw new IllegalStateException();

        if (first == last) return false;
        var fast = first;
        var slow = first;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;

        }
        return false;
    }

    public static LinkedList createWithLoop(){
        var list = new LinkedList();
        list.addFirst(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);
        list.last.next = list.first;
        
        return list;

    }
}
