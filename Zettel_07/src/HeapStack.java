import java.util.PriorityQueue;

public class HeapStack<E> implements SimpleStack<E> {

    class Element<E> {

        private final E element;
        private final int insertion;

        Element(E element, int insertion) {
            this.element = element;
            this.insertion = insertion;
        }
    }
    
    private PriorityQueue<Element<E>> priorityQueue;

    /**
     * Creates a new HeapStack object with the given maximum capacity.
     * @param maximumCapacity
     */
    public HeapStack(int maximumCapacity) {
        //check if maximumCapacity is valid
        if (maximumCapacity < 1) {
            throw new IllegalArgumentException("Maximum capacity must be at least 1.");
        }
        //create priority queue with maximumCapacity
        this.priorityQueue = new PriorityQueue<>(maximumCapacity);
    }

    /**
     * Removes the top element of this stack and returns it.
     * @return The element on top of this stack.
     */
    @Override
    public E pop()  {
        E elem = (E) this.priorityQueue.poll();

        return elem;
    }

    /**
     * Pushes the element on top of this stack.
     * @param element The element to be pushed on top of this stack.
     */
    @Override
    public void push(E element) {
        if (element == null) {
            throw new NullPointerException("Element must not be null.");
        }
        this.priorityQueue.add(new Element(element, 0));
    }

    /**
     * returns the top element of this stack without removing it.
     * @return The element on top of this stack.
     */
    public E peek() {
        E elem = (E) this.priorityQueue.peek();
        return elem;
    }

    /**
     * Removes all elements from this stack.
     */
    @Override
    public void clear() {
        this.priorityQueue.clear();
    }

    /**
     * Tests whether this stack is empty.
     * @return true, if this stack does not contain any elements; false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.priorityQueue.isEmpty();
    }
    /**
     * Returns the maximum capacity of this stack.
     * @return The maximum capacity of this stack.
     */
    @Override
    public int capacity() {
        return this.priorityQueue.size();
    }


}

