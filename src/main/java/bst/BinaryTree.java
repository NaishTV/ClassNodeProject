package bst;

public class BinaryTree{
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

    /**
     *
     * @param parent the root of Tree.
     * @param value the value which we want to delete
     */
    public Node delete(Node parent, int value) {
        /*
         * first part is find the value.
         */

        // if the parent is null, then the tree is empty. retrun the empty tree
        if( parent == null ) {
            System.out.println("the tree is empty.");
            return null;
        }

        // if the be deleted value is smaller then the parent value then go to the parent's left child to search the value.
        // when we found it then return the parent which is the be deleted node's parent.
        if( value < parent.value ){
            parent.left = delete( parent.left , value );
            return parent;
        }
        // otherwise the value is bigger than the parent value then go to right and return the parent.
        else if( value > parent.value ){
            parent.right = delete( parent.right, value );
            return parent;
        }
        // when we found the node which have the same value.
        else{

            /*
             * the second part is remove the node which has the value.
             */

            // if the node's left child is empty. then we need to take the right child as well. and put it into the node.
            // let the right child empty after the node be change.
            if( parent.left == null ){
                Node rightNode = parent.right;
                parent.right = null;
                return rightNode;
            }

            // otherwise as same.
            if( parent.right == null ){
                Node leftNode = parent.left;
                parent.left = null;
                return leftNode;
            }

            // if the two side children are not empty. then we need find out the minimum leaf on the right child.
            // and then put the left child of the node which we want to delete into the minimum leaf's left child.
            // the delete node's right child will be the left child of delete node's parent node.
            /*
             * 交换的原理 :
             * 当删除的时候, 左孩子应该在右孩子的最小值的位置, 用右孩子替换被删除的父母.
             * 但是这样会破坏平衡,
             * 所以用右孩子的最小值替换被删除的值. 比较平衡
             *
             *                                                     root
             *                         left-child(delete)(parent)                                   right-child
             *          left-LC(1)                          right_LC(2)                      left-RC         right-RC
             *  left-LLC(3)    right_LLC(4)         left-RLC(5)    right-RLC(6)
             *                        left-RLC.left-leaf  left-RLC.right-leaf
             *
             *
             *     AFTER DELETE
             *
             *
             *                                                     root
             *                         left-RLC.left-leaf                                   right-child
             *          left-LC(1)                          right_LC(2)                      left-RC         right-RC
             *  left-LLC(3)    right_LLC(4)          left-RLC(5)    right-RLC(6)
             *                                    null   left-RLC.right-leaf
             *
             */

            // right here we need a method which can find out the minimum leaf and save the node.
            Node minimum = minimum(parent.right);

            // this step means we put the minimum node on the delete node's position.
            /*
             * 1. use the method removeMin() take the minimum node's parent and put it on the minimum's right child
             * 2. put the delete node's left child on the minimum node's left child.
             */
            minimum.right = removeMin(parent.right);
            minimum.left = parent.left;

            // remove the delete node.
            parent.left = parent.right = null;

            // return the new node.
            return minimum;
        }


    }

    /**
     * this method is working for search the minimum node
     * @param node which branch we are looking for.
     * @return the node which is the smallest node.
     */
    private Node minimum(Node node){
    // if the node's left child is empty then this is the minimum leaf
        if(node.left == null){
            return node;
        }
        // if it is not empty then go down to the leaf
        return minimum(node.left);
    }

    /**
     *
     *  if the left child is empty which means the node is the smallest node.
     *  then we take the minimum node's right child ( which is all bigger then the minimum node)
     *  instead of the minimum node. so the minimum node is removed.
     *  and the method will return the node which is the parameter.
     *
     * @param node the branch which should be find out the minimum node and remove the minimum node.
     * @return the new branch which already remove the minimum node.
     */
    private Node removeMin(Node node){
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }
}