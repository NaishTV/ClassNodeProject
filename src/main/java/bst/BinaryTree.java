package bst;

public class BinaryTree {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node parent, int value) {
        if (parent == null) {
            return new Node(value);
        }

        if (value < parent.value) {
            parent.left = insertRecursive(parent.left, value);
        } else {
            parent.right = insertRecursive(parent.right, value);
        }

        return parent;
    }
}