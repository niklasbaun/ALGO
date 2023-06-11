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
        if(toDelete.getLeft() != null && toDelete.getRight() != null){
            //find the biggest key in the left subtree
            K newKey = toDelete.removeSymmetricPredecessor(toDelete);
            //set new key
            toDelete.key = newKey;
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
        //find biggestkey in left subtree
        K biggestKey = toDelete.getLeft().getMaxKey();
        return biggestKey;
    }


    /**
     * returns the min key of a tree
     *
     * @return the min key
     */
    public K getMinKey() {
        //check if the key has subtree
        if(this.left == null && this.right == null){
            return this.key;
        }
        //check if the key has left subtree
        if (this.left != null) {
            //call method again on left subtree
            BinarySearchTree<K> left = this.getLeft();
            return left.getMinKey();
        }
        return null;
    }

    /**
     * returns the max key of a tree
     *
     * @return the max key
     */
    public K getMaxKey() {
        //check if the key has subtree
        if(this.left == null && this.right == null){
            return this.key;
        }
        //check if the key has right subtree
        if (this.right != null) {
            //call method again on right subtree
            BinarySearchTree<K> right = this.getRight();
            return right.getMaxKey();
        }
        return null;
    }

    /**
     * finds the successor of a key
     *
     * @param key the key of the tree node
     * @return subtree of successor
     */
    public BinarySearchTree<K> getSuccessor(BinarySearchTree<K> key) {
        //key is null
        if(key == null){
            throw new IllegalArgumentException("key must not be null");
        }
        //check if key has right subtree
        if(key.getRight() != null){
            return key.getRight();
        }
        //check if key has no right subtree
        return null;
    }

    /**
     * finds the predecessor of a key
     *
     * @param key the key of the tree node
     * @return subtree of predecessor
     */
    public BinarySearchTree<K> getPredecessor(BinarySearchTree<K> key) {
        //key is null
        if(key == null){
            throw new IllegalArgumentException("key must not be null");
        }
      
        return null;
    }

    /**
     * finds the max key y in the tree such that y<=x
     *
     * @param x
     * @return the key
     */
    public K getMaxKey(K x) {
        //check if key is null
        if(x == null){
            throw new IllegalArgumentException("key must not be null");
        }
        //check if node is smaller than x
        if(this.key.compareTo(x) < 0){
            //check if right subtree is null
            if(this.right == null){
                return this.key;
            }else{
                //call method again on right subtree
                BinarySearchTree<K> right = this.getRight();
                return right.getMaxKey(x);
            }
        }
        //check if node is bigger than x
if(this.key.compareTo(x) > 0){
            //check if left subtree is null
            if(this.left == null){
                return null;
            }else{
                //call method again on left subtree
                BinarySearchTree<K> left = this.getLeft();
                return left.getMaxKey(x);
            }
        }
        return null;
    }


    public static void main(String[] args) {
        //TODO f)
    }
}