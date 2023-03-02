public class AVLTree {
    
    private class AVLNode{
        private int value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        AVLNode(int value){
            this.value = value;
        }

        @Override
        public String toString(){
            return "Value=" + this.value;
        }
    }

    private AVLNode root;

    public void insert(int value){
        root = insert(value,root);
    }

    public AVLNode insert(int value, AVLNode root){
        if (root == null)
            return new AVLNode(value);
        if (value > root.value)
            root.rightChild = insert(value, root.rightChild);
        else
            root.leftChild = insert(value, root.leftChild);
        
        setHeight(root);
        
        return balance(root);
    }
    
    private int setHeight(AVLNode node){
        return 1 + Math.max(height(root.rightChild), height(root.leftChild));
    }
    
    private AVLNode balance(AVLNode root){
        if (isLeftHeavy(root)){
            if (balanceFactor(root.leftChild) < 0)
                root.leftChild = leftRotate(root.leftChild);
            root = rightRotate(root);
        }            
        else if (isRightHeavy(root)){
            if (balanceFactor(root.rightChild) > 0)
                root.rightChild = rightRotate(root.rightChild);
            root = leftRotate(root);
        }
        return root;
    }

    private int height (AVLNode node){
        return node == null ? -1 : node.height;
    }

    private int balanceFactor(AVLNode node){
        return node == null ? 0 : height(node.leftChild) - height(node.rightChild);
    }
    
    private boolean isLeftHeavy(AVLNode node){
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node){
        return balanceFactor(node) < -1;
    }

    private AVLNode leftRotate(AVLNode root){
        AVLNode newRoot = root.rightChild;
        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;
        setHeight(root);
        setHeight(newRoot);
        return newRoot;
    }

    private AVLNode rightRotate(AVLNode root){
        AVLNode newRoot = root.leftChild;
        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;
        setHeight(root);
        setHeight(newRoot);
        return newRoot;
    }
}
