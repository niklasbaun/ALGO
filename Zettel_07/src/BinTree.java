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
		if(this.isEmpty()){
			System.out.println("Tree is empty");
		}
		if(this.getLeft() != null){
			this.left.inOrder();
		}
		System.out.print(this.getKey() + " ");
		if(this.getRight() != null){
			this.right.inOrder();
		}
	}

	/**
	 * prints the tree in the Pre-Order representation on the console
	 */
	public void preOrder() {
		if(this.isEmpty()){
			System.out.println("Tree is empty");
		}
		System.out.print(this.getKey() + " ");
		if(this.getLeft() != null){
			this.left.preOrder();
		}
		if(this.getRight() != null){
			this.right.preOrder();
		}
	}

	/**
	 * prints the tree in the Post-Order representation on the console
	 */
	public void postOrder() {
		if(this.isEmpty()){
			System.out.println("Tree is empty");
		}
		if(this.getLeft() != null){
			this.left.postOrder();
		}
		if(this.getRight() != null){
			this.right.postOrder();
		}
		System.out.print(this.getKey() + " ");
	}

	/**
	 * prints the tree in the Level-Order representation on the console
	 */
	public void levelOrder() {
		if (this.isEmpty()) {
			System.out.println("Tree is empty");
		} else {
			int height = this.treeHeight();
			int i;
			for (i = 1; i <= height; i++) {
				printLvl(i, this);
				System.out.println();
			}
		}
    }

	/**
	 * helper method to print all nodes of one level
	 * @param lvl the level to print
	 * @param node the node to print
	 */
	private void printLvl(int lvl, BinTree<T> node){
		if(node == null){
			return;
		}
		if(lvl == 1){
			System.out.print(node.getKey() + " ");
		} else if(lvl > 1){
			printLvl(lvl - 1, node.getLeft());
			printLvl(lvl - 1, node.getRight());
		}
	}

	/**
	 * helper method to calculate the height of the tree
	 * @return the height of the tree
	 */
	private int treeHeight(){
		if(this.isEmpty()){
			return 0;
		}
		int leftHeight = 0;
		int rightHeight = 0;
		if(this.getLeft() != null){
			leftHeight = this.left.treeHeight();
		}
		if(this.getRight() != null){
			rightHeight = this.right.treeHeight();
		}
		//return the bigger height of the two subtrees
		if(leftHeight > rightHeight){
			return leftHeight + 1;
		} else {
			return rightHeight + 1;
		}
	}
	/**
	 * create a specific tree
	 * @param args
	 */
	public static void main(String[] args) {
		//EndNode1
		BinTree<Character> EndNode1 = new BinTree<Character> (null, 'e', null);
		//EndNode2
		BinTree<Character> EndNode2 = new BinTree<Character> (null, 'c', null);
		//EndNode3
		BinTree<Character> EndNode3 = new BinTree<Character> (null, 'b', null);
		//left SubTreeLvl2
		BinTree<Character> l2Tree = new BinTree<Character> (EndNode1, 'f', null);
		//right SubTreeLvl2
		BinTree<Character> r2Tree = new BinTree<Character> (null, 'h', EndNode3);
		//left SubTreeLvl1
		BinTree<Character> l1Tree = new BinTree<Character> (l2Tree, 'a', EndNode2);
		//right SubTreeLvl1
		BinTree<Character> r1Tree = new BinTree<Character> (r2Tree, 'd', null);
		//root Tree
		BinTree<Character> rootTree = new BinTree<Character> (l1Tree, 'g', r1Tree);

		//print the tree in In-Order
		rootTree.inOrder();
		System.out.println();
		//print the tree in Pre-Order
		rootTree.preOrder();
		System.out.println();
		//print the tree in Post-Order
		rootTree.postOrder();
		System.out.println();
		//print the tree in Level-Order
		rootTree.levelOrder();
	}

}