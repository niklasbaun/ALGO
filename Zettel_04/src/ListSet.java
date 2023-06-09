import java.util.Iterator;

public class ListSet<E extends Comparable<E>> implements Set<E>, Iterable<E> {

	/**
	 * Node class
	 */
	private static class SetNode<T> {
		private T item;
		private SetNode<T> next;

		/**
		 * constructor to build a node with no successor
		 * 
		 * @param element
		 *            the value to be stored by this node
		 */
		private SetNode(T element) {
			item = element;
			next = null;
		}

		/**
		 * constructor to build a node with specified (maybe null) successor
		 * 
		 * @param element
		 *            the value to be stored by this node
		 * @param reference
		 *            the next field for this node
		 */
		private SetNode(T element, SetNode<T> reference) {
			item = element;
			next = reference;
		}
	}

	// This is the start of the linked list representing this set.
	// If the set is empty, it is null
	private SetNode<E> head = null;

	/**
	 * method to add an element to the ListSet
	 * @param element
	 *            - element to be inserted
	 * @return the state of the operation if the element was inserted into the set (true) or not (false)
	 */
	@Override
	public boolean add(E element) {
		// check if the element is already in the set
		if( contains(element)){
			return false;
		}
		// add the element to the front of the list
		head = new SetNode<E>(element, head);
		return true;
	}

	/**
	 * method to remove an element from the ListSet
	 * @param element
	 *            - the element to be removed
	 * @return the state of the operation if the element was removed from the set (true) or not (false)
	 */
	@Override

	public boolean remove(E element) {
		//if the set is empty nothing can be removed
		if( head == null ){
			return false;
		}
		//if the element is the first element in the list, remove it
		if( head.item.compareTo(element) == 0 ) {
			head = head.next;
			return true;
		}
		//work through the list until the element is found and remove it
		SetNode<E> node = head;
		while( node.next != null ) {
			if( node.next.item.compareTo(element) == 0 ) {
				node.next = node.next.next;
				return true;
			}
			node = node.next;
		}
		//if the element was not found, return false
		return false;
	}


	/**
	 * Returns true if this set contains the specified element.
	 * @param element
	 *            - element whose presence in this set is to be tested
	 * @return true if the element can be found else false
	 */
	@Override
	public boolean contains(E element) {
		return contains(head, element);
	}

	/**
	 * recursive private method, called by the public wrapper method
	 * 
	 * @param node
	 *            the head of the remaining list, maybe null
	 * @param element
	 *            the value to be searched
	 * @return search is succesful
	 */
	private boolean contains(SetNode<E> node, E element) {
		if (node == null)
			return false;
		return node.item.compareTo(element) == 0 || contains(node.next, element);
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * method to unit two sets, one inserted and one given
	 * @param set
	 *            - the given set.
	 */
	@Override
	public void union(Set<E> set) {
		//if the existing set is empty just copy the given set
		if(head == null){
			head = ((ListSet<E>) set).head;
			return;
		}
		//if the given set is empty do nothing
		else if(set.isEmpty()){
			return;
		}
		//if both contain elements compare and insert elements not already in the existing set
		else{
			SetNode<E> node = ((ListSet<E>) set).head;
			while(node != null){
				if(!contains(node.item)){
					add(node.item);
				}
				node = node.next;
			}
		}
	}

	/**
	 * method to unit two sets, one inserted and one given with O(n+m)
	 * @param set
	 */
	public void union2(Set<E> set){
		if(head == null){
			head = ((ListSet<E>) set).head;
			return;
		}
	}

	/**
	 * method to find the intersection of two sets, one inserted and one given
	 * @param set
	 *            - the given set.
	 */

	@Override
	public void intersect(Set<E> set) {
		//if the existing set is empty do nothing
		if(head == null){
			return;
		}
		//if the given set is empty clear the existing set
		else if(set.isEmpty()){
			head = null;
			return;
		}
		//else compare and remove elements not in the given set
		else{
			SetNode<E> node = head;
			while(node != null){
				if(!set.contains(node.item)){
					remove(node.item);
				}
				node = node.next;
			}
		}
	}

	/**
	 * method to remove all elements of the given set from the existing set
	 * @param set
	 *            - the given set.
	 */
	@Override
	public void subtract(Set<E> set) {
		//if the existing  or the given is empty do nothing
		if(head == null || set.isEmpty()){
			return;
		}
		//else remove
		else{
			SetNode<E> node = head;
			while(node != null){
				if(set.contains(node.item)){
					remove(node.item);
				}
				node = node.next;
			}
		}
	}

	/**
	 * recursive private method, called by the public wrapper method
	 * 
	 * @param node the head of the list (may be null if we are at the end)
	 * @return the string representing the list
	 */
	private String toString(SetNode<E> node) {
		if (node == null) {
			return "";
		}
		if (node.next == null) {
			return node.item.toString();
		}
		return node.item.toString() + " -> " + toString(node.next);
	}

	@Override
	public String toString() {
		return toString(head);
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			SetNode<E> node = head;

			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public E next() {
				E element = node.item;
				node = node.next;
				return element;
			}
		};
	}
}