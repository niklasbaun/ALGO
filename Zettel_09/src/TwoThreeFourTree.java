
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
            //create new sibling
            Node<K> sibling = new Node<>();
            //move last key and children to sibling
            sibling.keys.add(keys.remove(3));
            //remove median key and save it
            K median = keys.remove(2);
            return new SplitResult<>(sibling, median);
        }

        /**
         * Insert a key into the node.
         *
         * @param key The key to be inserted into the node.
         */
        void insert(K key) {
            //check if key is null
            if (key == null) {
                throw new IllegalArgumentException("key cannot be null");
            }
            //check if key is already in node
            if (contains(key)) {
                return;
            }
            //insert key
            int i = 0;
            while (i < this.keys.size() && key.compareTo(this.keys.get(i)) > 0) {
                i++;
            }
            this.keys.add(i, key);

        }

            /**
             * check if the node contains a key
             * @param key the key to be checked
             * @return true if the key is in the node
             */
        boolean contains(K key) {
            //check if key is null
            if (key == null) {
                throw new IllegalArgumentException("key cannot be null");
            }
            //check if key is in node
            int i = 0;
            while (i < keys.size() && key.compareTo(keys.get(i)) > 0) {
                i++;
                if (i < keys.size() && key.compareTo(keys.get(i)) == 0) {
                    return true;
                }
            }
            return false;
        }

        /**
         * method to return an ordered list of all elements in the tree
         * @return an ordered list of all elements in the tree
         */
        List<K> inOrder() {
            //create list
            List<K> list = new ArrayList<>();
            //return empty list if node is empty
            if (keys.isEmpty()) {
                return list;
            }
            //add all keys into List
            list.addAll(this.keys);
            //return list
            return list;
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
        //check if key is null
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        //check if root is empty
        if (root.keys.isEmpty()) {
            root.insert(key);
            return;
        } else {
            //check if key is already in root
            if (root.contains(key)) {
                return;
            }
            //navigate to leaf
            Node<K> current = this.root;
            while (!current.isLeaf()) {
                //find child to go to
                int i = 0;
                while (i < current.keys.size() && key.compareTo(current.keys.get(i)) > 0) {
                    i++;
                }
                current = current.children.get(i);
            }
            //insert key into leaf
            current.insert(key);
            //check if leaf is overflowing
            if (current.isOverFlow()) {
                //split leaf
                SplitResult<K> split = current.split();
                //create new root
                Node<K> newRoot = new Node<>();
                //add median key to new root
                newRoot.insert(split.key);
                //add old leaf and new sibling to new root
                newRoot.children.add(current);
                newRoot.children.add(split.sibling);
                //set new root as root
                this.root = newRoot;
            }
        }
    }

    /**
     * Check if a key is contained in the tree.
     *
     * @param key The key.
     * @return true, if key is contained in the tree.
     */
    public boolean contains(K key) {
        //check if key is null
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        //check if root is empty
        if (root.keys.isEmpty()) {
            return false;
        }
        //check if key is in root
        if(root.contains(key)){
            return true;
        }
        //navigate to appropriate leaf
        Node<K> current = this.root;
        while (!current.isLeaf()) {
            //find child to go to
            int i = 0;
            while (i < current.keys.size() && key.compareTo(current.keys.get(i)) > 0) {
                i++;
                //check if key is in node
                if(i < current.keys.size() && key.compareTo(current.keys.get(i)) == 0){
                    return true;
                }
            }
            current = current.children.get(i);
        }
        return false;
    }

    /**
     * Get the minimal element in the tree.
     *
     * @return The minimal element in the tree.
     */
    public K getMin() {
        //find minimal element
        Node<K> current = this.root;
        //navigate to minimal leaf
        while (!current.isLeaf()) {
            current = current.children.get(0);
        }
        //return minimal element
        return current.keys.get(0);
    }

    /**
     * Get the maximal element in the tree.
     *
     * @return The maximal element in the tree.
     */
    public K getMax() {
        //find maximal element
        Node<K> current = this.root;
        //navigate to maximal leaf
        while (!current.isLeaf()) {
            current = current.children.get(current.children.size() - 1);
        }
        //return maximal element
        return current.keys.get(current.keys.size() - 1);
    }

    /**
     * Get an ordered list of all elements in the tree.
     *
     * @return An ordered list of all elements in the tree
     */
    public List<K> inOrder() {
        //create list
        ArrayList<K> list = new ArrayList<>();
        //return empty list if root is empty
        if (root.keys.isEmpty()) {
            return list;
        }
        //check if root has children
        if(root.children != null){
            //return list of all elements in tree
            //check if child at pos 0 exists
            for(int i = 0; i < root.children.size(); i++){
                switch (i){
                    case 0:
                        //add all elements in child at pos 0
                        list.addAll(root.children.get(0).inOrder());
                        break;
                    case 1:
                        //add all elements in child at pos 1
                        list.add(root.keys.get(0));
                        list.addAll(root.children.get(1).inOrder());
                        break;
                    case 2:
                        //add all elements in child at pos 2
                        list.add(root.keys.get(1));
                        list.addAll(root.children.get(2).inOrder());
                        break;
                    case 3:
                        //add all elements in child at pos 3
                        list.add(root.keys.get(2));
                        list.addAll(root.children.get(3).inOrder());
                        break;
                }
            }
        } else {
            //add all elements in root
            list.addAll(root.inOrder());
        }
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