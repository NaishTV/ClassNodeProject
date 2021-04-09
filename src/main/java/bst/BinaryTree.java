package bst;

public class BinaryTree {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    /**
     * Inserts a value into the binary search tree.
     *
     * @param value the value to be inserted into the tree
     */
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    /**
     * Recursively calls itself comparing the parent node's value and the specified value until a possible leaf node has been reached.
     * A new instance of Node with the specified value is created and returned, which represents the new leaf node.
     * <p>
     * If the value already exists in the tree, the recursion occurs without insertion.
     *
     * @param parent the current node
     * @param value the value to be inserted into the tree
     * @return either the new leaf node or the parent node
     */
    private Node insertRecursive(Node parent, int value) {
        // If the parent node is null, it is a possible leaf node and the value is inserted into it.
        if (parent == null) {
            return new Node(value);
        }

        // Check whether the value to be inserted is less than or greater than the parent node's value.
        if (value < parent.value) {
            parent.left = insertRecursive(parent.left, value);      // Insert recursively into the parent's left child.
        } else {
            parent.right = insertRecursive(parent.right, value);    // Insert recursively into the parent's right child.
        }

        return parent;  // Return an instance of Node that contains the specified value, which represents a leaf node.
    }
}