import java.util.Iterator;
import java.util.Random;

public class RandomSkipListImpl<E extends Comparable<E>> implements RandomSkipList<E> {
    private Node head, tail;
    int level;
    int maxLevel;
    //method to generate a random level
    int randomLevel(){
        Random rand = new Random();
        int level = 0;
        while(rand.nextInt(2) == 1){
            level++;
        }
        return level;
    }

    RandomSkipListImpl(int maxLevel){
        this.maxLevel = maxLevel;
        this.level = 0;
        this.head = new Node(maxLevel+1);
        this.tail = new Node(0);
        tail.item = Integer.MAX_VALUE;
        for(int i = 0; i < maxLevel; i++){
            head.next[i] = tail;
        }
    }

    /**
     * Node class
     */
    private static class Node<T> {
        T item;
        private Node<T>[] next;
        //constructor
        private Node(T element) {
            item = element;
            next = null;
        }

        private Node(T element, Node<T>[] reference) {
            item = element;
            next = reference;
        }
    }



    /**
     * method to add a node to the RandomSkipList
     * @param element
     *            - element to be inserted
     * @return true if the element was inserted into the set; false otherwise
     */
    @Override
    public boolean add(Comparable element) {
        //check if list is empty
        if(head == null){
            head = new Node(element);
            return true;
        }
        // check if the element is already in the set
        if(contains(element)){
            return false;
        }
        //add the element
        Node[] update = new Node[maxLevel+1];
        Node newN, node = head;
        for(int i = level; i >= 0; i--){
            //check if the next node is smaller than the element
            while(node.next[i] != null && node.next[i].item.compareTo(element) < 0){
                node = node.next[i];
            }
            update[i] = node;
        }
        return false;
    }

    @Override
    public boolean remove(Comparable element) {
        if (element == null) {
            return false;
        }
        //check if the element is in the list
        if(contains(element)){
            //remove the element
            //loop through the list until element is found
            Node current = head;
            for(int i = level; i >= 0; i--){
                while(current.next[i] != null && current.next[i].item.compareTo(element) < 0){
                    current = current.next[i];
                }
                //remove the found element

            }

        }
        return false;
    }
    /**
     * method to check if the RandomSkipList contains an element
     * @param element
     *            - element whose presence in this List is to be tested
     * @return true if this List contains the specified element; false otherwise
     */
    @Override
    public boolean contains(Comparable element) {
        if(element == null){
            return false;
        }
        Node node = head;
        for(int i = level; i >= 0; i--){
            while(node.next[i].item.compareTo(element) < 0){
                node = node.next[i];
            }
        }
        node = node.next[0];
        return node.item.compareTo(element) == 0;
    }
}
