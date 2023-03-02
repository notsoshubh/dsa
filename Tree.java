import java.util.ArrayList;
import java.util.List;

public class Tree {
    
    private class Node {
        public int value;
        public Node leftChild;
        public Node rightChild;

        public Node(int value){
            this.value = value;
        }

        @Override
        public String toString(){
            return "Node - " + value;
        }
    }

    private Node root;

    public void insert(int item){

        var node = new Node(item);

        if (root == null){
            root = node;
            return;
        }

        var current = root;
        while (true){
            if (current.value > item){
                if (current.leftChild == null){
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            }

            else {
                if (current.rightChild == null){
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int item){

        var current = root;
        while (current != null){
            if (current.value == item)
                return true;
            if (current.value > item)
                current = current.leftChild;
            else
                current = current.rightChild;
        }

        return false;
    }

    public void traversePreOrder(){
        traversePreOrder(root);
    }

    public void traversePostOrder(){
        traversePostOrder(root);
    }

    public void traverseInOrder(){
        traverseInOrder(root);
    }

    private void traversePreOrder(Node root){
        if (root == null)
            return;
        
        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }

    private void traversePostOrder(Node root){
        if (root == null)
            return;
        
        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value);
    }

    private void traverseInOrder(Node root){
        if (root == null)
            return;
        
        traverseInOrder(root.leftChild);
        System.out.println(root.value);
        traverseInOrder(root.rightChild);    
    }

    public int height(){
        return height(root);
    }

    private int height(Node root){
        if (root == null)
            return -1;

        if(isLeaf(root))
            return 0;

        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    private boolean isLeaf(Node node){
        return node.leftChild == null && node.rightChild == null;
    }

    public int min(){
        return min(root);
    }

    private int min(Node root){
        if (isLeaf(root))
            return root.value;

        var left = min(root.leftChild);
        var right = min(root.rightChild);

        return Math.min(Math.min(left, right), root.value);
    }

    public int minBST(){
        return minBST(root);
    }

    private int minBST(Node root){
        if (root == null)
            throw new IllegalStateException();

        var current = root;
        var last = current;
        while (current != null){
            last = current;
            current = current.leftChild;
        }

        return last.value;
    }

    public boolean equals(Tree tree){
        if (tree == null)
            return false;
            
        return equals(root,tree.root);
    }

    private boolean equals(Node first, Node second){
        if (first == null && second == null)
            return true;

        if (first != null && second != null){
            return first.value == second.value
                && equals(first.leftChild, second.leftChild)
                && equals(first.rightChild, second.rightChild);
        }

        return false;        
    }

    public boolean isBinarySearchTree(){
        return isBinarySearchTree(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root,long lowerLimit, long upperLimit){
        if (root == null)
            return true;
        
        if (root.value < lowerLimit || root.value > upperLimit)
            return false;
        
        return isBinarySearchTree(root.leftChild,lowerLimit,root.value) && isBinarySearchTree(root.rightChild,root.value,upperLimit);     
    }

    public ArrayList<Integer> getNodesAtdistance(int k){
        ArrayList<Integer> list = new ArrayList<>();
        getNodesAtdistance(root, k, list);
        return list;
    }

    private void getNodesAtdistance(Node root, int k, ArrayList<Integer> list){
        if (root == null)
            return;
        if (k == 0){
            list.add(root.value);
            return;
        }
        getNodesAtdistance(root.leftChild, k - 1, list);
        getNodesAtdistance(root.rightChild, k - 1, list);
    }

    public void levelOrderTraversal(){
        for (var i = 0; i <= height(); i++){
            for (var node : getNodesAtdistance(i)){
                System.out.println(node);
            }
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node root){
        if (root == null)
            return 0;
        else
            return (1 + size(root.leftChild) + size(root.rightChild));
    }

    public int countLeaves(){
        return countLeaves(root);
    }

    private int countLeaves(Node root){
        if (root == null)
            return 0;
        if (isLeaf(root))
            return 1;
        else
            return countLeaves(root.leftChild) + countLeaves(root.rightChild);
    }

    public int max(){
        return max(root);
    }

    private int max(Node root){
        if (root.rightChild == null)
            return root.value;
        
        return max(root.rightChild);
    }

    public boolean contains(int item){
        return contains(item,root);
    }

    private boolean contains(int item, Node root){
        if (root == null)
            return false;
        if (root.value == item)
            return true;
        return contains(item, root.leftChild) || contains(item,root.rightChild); 
    }

    public boolean areSiblings(int value1, int value2){
        if (contains(value1) && contains(value2)){
            return areSiblings(value1, value2, root);
        }
        return false;
    }

    private boolean areSiblings(int value1, int value2, Node root){
        if ((root.leftChild.value == value1 && root.rightChild.value == value2) || (root.leftChild.value == value2 && root.rightChild.value == value1))
            return true;
        if (value1 > root.value && root.rightChild != null)
            return areSiblings(value1, value2, root.rightChild);
        if (value1 < root.value && root.leftChild != null)
            return areSiblings(value1, value2, root.leftChild);
        return false; 
    }

    public List<Integer> getAncestors(int value){
        if (!contains(value))
            throw new IllegalArgumentException();
        
        List<Integer> list = new ArrayList<>();
        return getAncestors(value,list,root);
    }

    private List<Integer> getAncestors(int value, List<Integer> list, Node root){
        if (root.value == value)
            return list;
        list.add(root.value);
        if (value > root.value && root.rightChild != null)
            return getAncestors(value, list, root.rightChild);
        if (value < root.value && root.leftChild != null)
            return getAncestors(value, list, root.leftChild);
        return null;
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node root){
        if (root == null)
            return true;
        var balanceFactor = height(root.leftChild) - height(root.rightChild);
        if (balanceFactor > 1 || balanceFactor < -1)
            return false;
        return isBalanced(root.rightChild) && isBalanced(root.leftChild);
    }

    public boolean isPerfect(){
        return size() == Math.pow(2, height(root) + 1) - 1;
    }
}
