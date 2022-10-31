package Sorting;

import java.util.ArrayList;
import java.util.Iterator;


public class TreeSet<T> implements SetInterface<Comparable<T>> {

    private TreeNode root;
    private int numElements;

    /**
     * No-Param Class Constructor
     * Creates empty GFATreeSet.
     *
     * Time Complexity - O(1)
     */
    public TreeSet() {
        this.root = null;
        this.numElements = 0;
    }

    public TreeSet(Comparable<T> data) {
        this.root = new TreeNode(data);
        this.numElements = 1;

    }

    /**
     * Adds a new node to the TreeSet.
     *
     * Time-Complexity - O(log(n))
     * @return Process Result (boolean)
     */
    @Override
    public boolean add(Comparable<T> element) {
        TreeNode ptr = this.root;
        TreeNode nodeToAdd = new TreeNode(element);

        if (isEmpty()) {
            this.root = nodeToAdd;
            this.numElements++;
            return true;
        }

        return findPlaceToAdd(ptr, nodeToAdd);
    }

    /**
     * Empties the entire TreeSet
     *
     * Time-Complexity - O(log(n))
     */
    @Override
    public void clear() {
        this.root = null;
        this.numElements = 0;
    }

    /**
     * Returns boolean dependent on if an item is in the TreeSet.
     *
     * Time-Complexity - O(log(n))
     * @return Process Result
     */
    @Override
    public boolean contains(Comparable<T> object) {
        if (isEmpty()) return false;
        TreeNode ptr = this.root;
        return matchNode(ptr, object);
    }

    /**
     * Returns boolean dependent on if the TreeSet has no objects.
     *
     * Time-Complexity - O(1)
     * @return Process Result (boolean)
     */
    @Override
    public boolean isEmpty() {
        return this.numElements == 0;
    }

    /**
     * Removes a specified node from the TreeSet.
     *
     * Time-Complexity - O(log(n))
     * @return Process Result (boolean)
     */
    @Override
    public boolean remove (Comparable<T> object) {
        if (isEmpty()) return false;

        // Handle only-root case first
        if (this.root.data.equals(object) && this.root.isLeaf()) {
            root = null;
            numElements--;
            return true;
        }

        TreeNode left = this.root.left;
        TreeNode right = this.root.right;

        if (this.root.data.equals(object)) { //if the root is to be removed
            if (left != null && right == null) { //one child on left
                this.root = this.root.left;
                this.numElements--;
                return true;
            } else if (left == null && right != null) { //one child on right
                this.root = root.right;
                this.numElements--;
                return true;
            }

            TreeNode repNode = left;

            ArrayList<TreeNode> nodeArr = repNode(repNode, null);

            repNode = nodeArr.get(0);

            // Preservation of repNode's left subtree
            if (repNode.equals(this.root.left))
                this.root.left = this.root.left.left;

            this.root.data = repNode.data;
            this.numElements--;
            return true;
        }

        TreeNode ptr = this.root;
        return removeNode(object, ptr, null);
    }

    /**
     *  Returns the number of elements in the TreeSet.
     *
     *  Time-Complexity - O(1)
     *
     * @return this.numElements
     */
    @Override
    public int size() {
        return this.numElements;
    }

    /**
     * Returns a string of the entire TreeSet, in-order. (Least to Greatest)
     *
     * Time-Complexity - O(n)
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        TreeNode ptr = this.root;
        String s = "[" + makeString(ptr);
        s = s.substring(0, s.length() - 2) + "]";
        return s;
    }

    public TreeIterator treeIterator() {
        return new TreeIterator();
    }

    public TreeNode floor() {
        return getFloor(this.root);
    }

    public TreeNode max() {
        return getMax(this.root);
    }

    // *************  HELPER METHODS  *************

    private TreeNode getFloor(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private TreeNode getMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    /**
     *  Makes the string for the toString() to return.
     *
     *  Time-Complexity - O(n)
     */
    private String makeString(TreeNode node) {
        String s = "";
        if (node == null || node.data == null) {
            return "";
        }

        s += makeString(node.left);
        s += node.data + ", ";
        s += makeString(node.right);

        return s;
    }

    /**
     * Finds place to add a node to the TreeSet
     *
     * Time-Complexity - O(log(n))
     * @return Process result (boolean)
     */
    private boolean findPlaceToAdd(TreeNode ptr, TreeNode nodeToAdd) {

        // Compare current item vs node looking to add
        int comp = nodeToAdd.data.compareTo((T) ptr.data);

        // Look Right
        if (comp > 0) {
            // If at the end, add
            if (ptr.right == null) {
                ptr.right = nodeToAdd;
                this.numElements++;
                return true;
            }
            // Continue Iterating
            findPlaceToAdd(ptr.right, nodeToAdd);
        }

        // Look Left
        if (comp < 0) {
            // If at the end, add
            if (ptr.left == null) {
                ptr.left = nodeToAdd;
                this.numElements++;
                return true;
            }
            // Continue iterating
            findPlaceToAdd(ptr.left, nodeToAdd);
        }
        // Item is already contained in the list.
        return false;
    }

    /**
     * Finds a match in the TreeSet
     *
     * Time Complexity -O(log(n))
     * @return Process result (boolean)
     */
    private boolean matchNode(TreeNode ptr, Comparable<T> data) {
        int comp = data.compareTo((T) ptr.data);

        // Look Right
        if (comp > 0) {
            if (ptr.right == null) {
                // Isn't contained
                return false;
            }
            // Continue iterating
            return matchNode(ptr.right, data);
        } else if (comp < 0){
            if (ptr.left == null) {
                // Isn't contained
                return false;
            }
            // Continue Iterating
            return matchNode(ptr.left, data);
        }

        return true;
    }

    /**
     * Removes a specified node from the TreeSet
     *
     * Time-Complexity - O(log(n))
     *
     * @return Process Result (boolean)
     */
    private boolean removeNode(Comparable<T> object, TreeNode ptr, TreeNode parent) {
        Comparable<T> data = ptr.data;
        TreeNode left = ptr.left;
        TreeNode right = ptr.right;

        if (data.equals(object)) {                                      // If object is found,
            if (ptr.isLeaf()) {                                         // If node has no children,
                if (parent.getData().compareTo((T) ptr.getData()) > 0)    // left side of parent
                    parent.left = null;
                else                                                    // right side of parent
                    parent.right = null;

                this.numElements--;
                return true;
            } else if (left != null && right == null) {                 // only has left side
                if (parent.getData().compareTo((T) ptr.getData()) > 0)    // left side of parent
                    parent.left = ptr.left;
                else                                                    // right side of parent
                    parent.right = ptr.left;

                this.numElements--;
                return true;
            } else if (left == null && right != null) {                 //only has right side
                if (parent.getData().compareTo((T) ptr.getData()) > 0)    //left side of parent
                    parent.setLeft(ptr.getRight());
                else                                                    //right side of parent
                    parent.setRight(ptr.getRight());

                this.numElements--;
                return true;
            } else {
                assert left != null;
                // STARTS IN LEFT SUBTREE
                ArrayList<TreeNode> arr = repNode(left, ptr);

                TreeNode changeRoot = arr.get(0);
                TreeNode changeParent = arr.get(1);

                ptr.setData(changeRoot.getData());
                changeParent.setRight(changeRoot.getLeft());
                this.numElements--;
                return true;
            }
        }

        if (left!=null) {
            if (removeNode(object, ptr.getLeft(), ptr))
                return true;
        }

        if (right!=null) {
            if (removeNode(object, ptr.getRight(), ptr))
                return true;
        }

        return false;
    }

    /**
     * Finds the replacement node for removal.
     * Returns both the node itself and the parent as well.
     * The node is returned in arr.get(0), the parent arr.get(1);
     *
     * Time-Complexity - O(log(n))
     *
     * @return ArrayList<TreeNode>
     */
    private ArrayList<TreeNode> repNode(TreeNode ptr, TreeNode parent) {
        while (!ptr.isLeaf() && ptr.getRight()!=null) {
            parent = ptr;
            ptr = ptr.getRight();
        }

        ArrayList<TreeNode> arr = new ArrayList<>();
        arr.add(ptr);
        arr.add(parent);
        return arr;
    }

    private class TreeNode {
        private Comparable<T> data;
        private TreeNode left, right;

        public TreeNode(Comparable<T> data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Comparable<T> getData() {
            return this.data;
        }

        public void setData(Comparable<T> data) {
            this.data = data;
        }

        public TreeNode getLeft() {
            return this.left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return this.right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public boolean isLeaf() {
            return (this.left == null && this.right == null);
        }
    }

    public class TreeIterator implements Iterator<Comparable<T>> {
        ArrayList<TreeNode> elements;
        int pos;

        public TreeIterator() {
            this.elements = new ArrayList<>();
            treeToArr(elements, root);
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            return (pos + 1) < elements.size();
        }

        @Override
        public Comparable<T> next() {
            this.pos = this.pos + 1;
            return elements.get(pos).data;
        }

        private void treeToArr(ArrayList<TreeNode> arr, TreeNode node) {
            if (node == null) return;

            treeToArr(arr, node.left);
            arr.add(node);
            treeToArr(arr, node.right);
        }
    }
}