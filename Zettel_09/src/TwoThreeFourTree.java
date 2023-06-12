
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoThreeFourTree<K extends Comparable<K>> {

    private static class SplitResult<K extends Comparable<K>> {
        Node<K> sibling;
        K key;

        SplitResult(Node<K> sibling, K key) {
            this.sibling = sibling;
            this.key = key;
        }
    }

    private static class Node<K extends Comparable<K>> {
        List<Node<K>> children;
        List<K> keys;

        Node() {
            children = new ArrayList<>();
            keys = new ArrayList<>();
        }

        boolean isLeaf() {
            return children.isEmpty();
        }

        /**
         * Check if a node is overflowing, i.e. it has 4 keys (and 5 children) - one too many. If so, it needs to be split.
         */
        boolean isOverFlow() {
            return keys.size() == 4;
        }

        /**
         * Splits a node and returns a tuple containing the new sibling and the key that is to be inserted in the parent
         *
         * @return the SplitResult
         */
        SplitResult<K> split() {
            // TODO
            return null;
        }

        /**
         * Insert a key into the tree.
         *
         * @param key The key to be inserted into the tree.
         */
        void insert(K key) {
            // TODO
        }

        boolean contains(K key) {
            // TODO
            return false;
        }

        List<K> inOrder() {
            // TODO
            return null;
        }

        @Override
        public String toString() {
            return keys.toString();
        }
    }

    private Node<K> root;

    public TwoThreeFourTree() {
        this.root = new Node<>();
    }

    /**
     * Insert a key into the tree.
     *
     * @param key The key to be inserted into the tree.
     */
    public void insert(K key) {
        // TODO: implement Node.insert
        root.insert(key);

        // TODO: check for overflow and fix it, if necessary
    }

    /**
     * Check if a key is contained in the tree.
     *
     * @param key The key.
     * @return true, if key is contained in the tree.
     */
    public boolean contains(K key) {
        // TODO: implement Node.contains
        return root.contains(key);
    }

    /**
     * Get the minimal element in the tree.
     *
     * @return The minimal element in the tree.
     */
    public K getMin() {
        // TODO
        return null;
    }

    /**
     * Get the maximal element in the tree.
     *
     * @return The maximal element in the tree.
     */
    public K getMax() {
        // TODO
        return null;
    }

    /**
     * Get an ordered list of all elements in the tree.
     *
     * @return An ordered list of all elements in the tree
     */
    public List<K> inOrder() {
        // TODO: implement Node.inOrder
        return root.inOrder();
    }

    public static void main(String[] args) {
        // Note: assertions are only enabled if `-ea` is passed to java

        int num_inserts = 20;
        int num_generated = num_inserts * 2;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num_generated; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        TwoThreeFourTree<Integer> tree = new TwoThreeFourTree<>();

        for (int i = 0; i < num_inserts; i++) {
            tree.insert(list.get(i));
        }

        for (int i = 0; i < num_inserts; i++) {
            assert tree.contains(list.get(i));
        }

        System.out.println(tree.inOrder());

        System.out.println("Min: " + tree.getMin());
        System.out.println("Max: " + tree.getMax());
    }
}