import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class QueueReverser {

    public void reverse(Queue<Integer> queue, int k){
        
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> tempQueue = new ArrayDeque<>();
        int size = queue.size();
        for (int i = 0; i < size; i++){
            if (i < k)
                stack.push(queue.remove());
            else
                tempQueue.add(queue.remove());
        }
        
        for (int i = 0; i < size; i++){
            if (i < k)
                queue.add(stack.pop());
            else
                queue.add(tempQueue.remove());
        }

    }
}
