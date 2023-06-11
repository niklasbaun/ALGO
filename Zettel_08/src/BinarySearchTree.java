public class BinarySearchTree<K extends Comparable<? super K>> extends BinaryTree<K> {
    /**
     * creates a new instance
     *
     * @param left  the left subtree
     * @param key   the key of the tree node
     * @param right the right subtree
     */
    public BinarySearchTree(BinarySearchTree<K> left, K key, BinarySearchTree<K> right) {
        super(left, key, right);
    }
    /**
     * creates a new instance
     *
     * @param key   the key of the tree node
     */
    public BinarySearchTree(K key) {
        super(null, key, null);
    }


    /**
     * returns the left subtree
     */
    @Override
    public BinarySearchTree<K> getLeft() {
        //find leftsubtree and return as BinarySearchTree
        BinarySearchTree<K> left = (BinarySearchTree<K>) super.getLeft();
        return left;
    }

    /**
     * returns the right subtree
     */
    @Override
    public BinarySearchTree<K> getRight() {
        //find rightsubtree and return as BinarySearchTree
        BinarySearchTree<K> right = (BinarySearchTree<K>) super.getRight();
        return right;
    }

    /**
     * adds a key to the tree
     *
     * @param key the key of the tree node
     */
    public void add(K key) {
        //check if key is null
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        //check if key is smaller than current key
        if (key.compareTo(this.key) < 0) {
            //check if left subtree is null
            if (this.left == null) {
                //create new BinarySearchTree with key
                BinarySearchTree<K> newTree = new BinarySearchTree<K>(key);
                //set left subtree to new tree
                this.left = newTree;
            } else {
                //call method again on leftsubtree
                BinarySearchTree<K> left = this.getLeft();
                left.add(key);
            }
        } else {
            //if the key is bigger, it will be added to the right subtree
            //check if right subtree is null
            if (this.right == null) {
                //create new BinarySearchTree with key
                BinarySearchTree<K> newTree = new BinarySearchTree<K>(key);
                //set right subtree to new tree
                this.right = newTree;

            } else {
                //call method again on rightsubtree
                BinarySearchTree<K> right = this.getRight();
                right.add(key);
            }
        }
    }

    /**
     * searches a key in the tree
     *
     * @param key the key of the tree node
     * @return subtree where key is found or null
     */
    public BinarySearchTree<K> search(K key) {
        //check if key is null
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        //check if key is found e.g. key == this.key
        if(key.compareTo(this.key) == 0){
            return this;
        }
        //check if key is bigger or smaller than the node
        if(key.compareTo(this.key) < 0){
            //check if left subtree is null
            if(this.left == null){
                return null;
            }else{
                //call method again on left subtree
                BinarySearchTree<K> left = this.getLeft();
                return left.search(key);
            }
        }else{
            //check if right subtree is null
            if(this.right == null){
                return null;
            }else{
                //call method again on right subtree
                BinarySearchTree<K> right = this.getRight();
                return right.search(key);
            }
        }
    }

    /**
     * deletes a key from tree
     *
     * @param key the key of the tree node
     * @return true if found and deleted, false otherwise
     */
    public boolean remove(K key) {
        //check if key is null
        if(key == null){
            throw new IllegalArgumentException("key must not be null");
        }
        //search for key
        BinarySearchTree<K> toDelete = this.search(key);
        //if toDelete is null, key is not in tree
        if(toDelete == null){
            return false;
        }
        //if toDelete has no children, it can be deleted
        if(toDelete.getLeft() == null && toDelete.getRight() == null){
            //remove
            toDelete = null;
            return true;
        }
        //if toDelete has one child, it can be replaced by the child
        if(toDelete.getLeft() == null || toDelete.getRight() == null){
            //check if left subtree is null
            if (toDelete.getLeft() == null) {
                //set toDelete to right subtree
                toDelete = toDelete.getRight();
                toDelete.right = null;
                return true;
            } else {
                //set toDelete to left subtree
                toDelete = toDelete.getLeft();
                toDelete.left = null;
                return true;
            }
        }
        //if toDelete has two Children look at right subtree for smallest key
        //and replace toDelete with it
        if(toDelete.getLeft() != null && toDelete.getRight() != null){
            //find smallest key in right subtree
            K minKey = toDelete.getRight().getMinKey();
            //remove smallest key from right subtree
            toDelete.getRight().remove(minKey);
            //set toDelete to minKey
            toDelete.key = minKey;
            return true;
        }
        return true;
    }

    /**
     * removes the symmetric predecessor of a node
     *
     * @param toDelete the node to delete
     * @return the key of the symmetric predecessor
     */
    private K removeSymmetricPredecessor(BinarySearchTree<K> toDelete) {
        //check if left subtree is null
        if(toDelete.getLeft() == null){
            return null;
        }
        K predecessor = toDelete.getLeft().getMaxKey();
        //remove predecessor
        remove(predecessor);
        return predecessor;
    }


    /**
     * returns the min key of a tree
     *
     * @return the min key
     */
    public K getMinKey() {
        //look for smallest key in a tree
        //check if left subtree is null
        if(this.left == null){
            return this.key;
        }else{
            //call method again on left subtree
            BinarySearchTree<K> left = this.getLeft();
            return left.getMinKey();
        }
    }

    /**
     * returns the max key of a tree
     *
     * @return the max key
     */
    public K getMaxKey() {
        //look for biggest key in a tree
        //check if right subtree is null
        if(this.right == null){
            return this.key;
        }else{
            //call method again on right subtree
            BinarySearchTree<K> right = this.getRight();
            return right.getMaxKey();
        }
    }

    /**
     * finds the successor of a key
     *
     * @param key the key of the tree node
     * @return subtree of successor
     */
    public BinarySearchTree<K> getSuccessor(BinarySearchTree<K> key) {
        //look for symetric successor
        // if right subtree is not null, look for min key in right subtree
        if(key.getRight() != null){
            return (BinarySearchTree<K>) key.getRight().getMinKey();
        }
        return null;
    }

    /**
     * finds the predecessor of a key
     *
     * @param key the key of the tree node
     * @return subtree of predecessor
     */
    public BinarySearchTree<K> getPredecessor(BinarySearchTree<K> key) {
        //check for symetric predecessor
        //if left subtree is not null, look for max key in left subtree
        if(key.getLeft() != null){
            return (BinarySearchTree<K>) key.getLeft().getMaxKey();
        }
        return null;
    }

    /**
     * finds the max key y in the tree such that y<=x
     *
     * @param x the key to compare to
     * @return max key y such that y<=x
     */
    public K getMaxKey(K x) {
        //look for biggest key in a tree that is smaller or equal to x
        return getMaxKey(x, this);
    }

    /**
     * helper method for getMaxKey
     * @param x the key to compare to
     * @param tempNode
     * @return
     */
    private K getMaxKey(K x, BinaryTree<K> tempNode) {
        //check if tempNode is null
        if(tempNode == null){
            return null;
        }
        //check if root is equal to x
        if(tempNode.getKey().compareTo(x) == 0){
            return tempNode.getKey();
            //check if root is smaller than x
        } else if (tempNode.getKey().compareTo(x) < 0) {
            K temp = getMaxKey(x, tempNode.getRight());
            //check if temp is null
            if (temp == null) {
                return tempNode.getKey();
            } else {
                return temp;
            }
            //check if root is bigger than x
        } else if (tempNode.getKey().compareTo(x) > 0) {
            return getMaxKey(x, tempNode.getLeft());
        }
        return null;
    }


    public static void main(String[] args) {
        //create new tree
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(5);
        //add nodes
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        //search for example nodes
        System.out.println(tree.search(5));
        System.out.println(tree.search(3));
        //remove example nodes
        tree.remove(5);
        tree.remove(3);
        //search for example nodes
        System.out.println(tree.search(5));
        System.out.println(tree.search(3));




    }
}