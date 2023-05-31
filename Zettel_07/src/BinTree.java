/**
 * This class implements a binary tree
 *
 * @param <T>
 */
public class BinTree<T> {

	protected BinTree<T> left, right;
	protected T key;

	/**
	 * creates a new instance
	 * @param l the left subtree
	 * @param k the key of the tree node 
	 * @param r the right subtree
	 */
	public BinTree(BinTree<T> l, T k, BinTree<T> r) {
		this.left = l;
		this.key = k;
		this.right = r;
	}
	
	/**
	 * returns the left subtree
	 */
	public BinTree<T> getLeft() {
		return left;
	}

	/**
	 * returns the left subtree
	 */
	public BinTree<T> getRight() {
		return right;
	}

	/**
	 * returns the key
	 */
	public T getKey() {
		return key;
	}

	/**
	 * checks if the tree is empty
	 * @return true, if tree is empty, else false
	 */
	public boolean isEmpty() {
		return ((left == null) && (right == null) && (key == null));
	}

	/**
	 * prints the tree in the In-Order representation on the console
	 */
	public void inOrder() {
		//TODO
	}

	/**
	 * prints the tree in the Pre-Order representation on the console
	 */
	public void preOrder() {
		//TODO
	}

	/**
	 * prints the tree in the Post-Order representation on the console
	 */
	public void postOrder() {
		//TODO
	}

	/**
	 * prints the tree in the Level-Order representation on the console
	 */
	public void levelOrder() {
		//TODO
    }

	public static void main(String[] args) {
		//TODO
	}

}