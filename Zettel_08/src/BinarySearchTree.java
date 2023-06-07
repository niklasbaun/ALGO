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
                return true;
            } else {
                //set toDelete to left subtree
                toDelete = toDelete.getLeft();
                return true;
            }
        }
        //if toDelete has two Children look at right subtree for smallest key

        return true;
    }


    private K removeSymmetricPredecessor(BinarySearchTree<K> toDelete) {
        //TODO b)iii
        return null;
    }


    /**
     * returns the min key of a tree
     *
     * @return
     */
    public K getMinKey() {
        //TODO c)
        return null;
    }

    /**
     * returns the max key of a tree
     *
     * @return
     */
    public K getMaxKey() {
        //TODO c)
        return null;
    }

    /**
     * finds the successor of a key
     *
     * @param key the key of the tree node
     * @return subtree of successor
     */
    public BinarySearchTree<K> getSuccessor(BinarySearchTree<K> key) {
        //TODO d)
        return null;
    }

    /**
     * finds the predecessor of a key
     *
     * @param key the key of the tree node
     * @return subtree of predecessor
     */
    public BinarySearchTree<K> getPredecessor(BinarySearchTree<K> key) {
        //TODO d)
        return null;
    }

    /**
     * finds the max key y in the tree such that y<=x
     *
     * @param x
     * @return
     */
    public K getMaxKey(K x) {
        //TODO e)
        return null;
    }


    public static void main(String[] args) {
        //TODO f)
    }
}