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
    

}
