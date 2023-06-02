import java.util.PriorityQueue;

public class HeapQueue<E> implements SimpleFIFOQueue<E> {

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
     * Creates a new HeapQueue object with the given maximum capacity.
     * @param maximumCapacity
     */
    public HeapQueue(int maximumCapacity) {
        //check if maximumCapacity is valid
        if (maximumCapacity < 1) {
            throw new IllegalArgumentException("Maximum capacity must be at least 1.");
        }
        //create priority queue with maximumCapacity
        this.priorityQueue = new PriorityQueue<>(maximumCapacity);
    }

    /**
     * Removes the first element in the queue and returns it.
     * @return The element which, among all elements currently in this queue, was added first.
     */
    @Override
    public E poll() {
        E elem = (E) this.priorityQueue.poll();
        return elem;
    }

    /**
     * Adds the element to this queue.
     * @param element The element to be added to this queue.
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException("Element must not be null.");
        }
        this.priorityQueue.add(new Element(element, this.priorityQueue.size()-1));
    }

    /**
     * Returns the first element in the queue without removing it.
     * @return The element which, among all elements currently in this queue, was added first.
     */
    public E peek() {
        E elem = (E) this.priorityQueue.peek();
        return elem;
    }

    /**
     * Removes all elements from this queue.
     */
    @Override
    public void clear() {
       this.priorityQueue.clear();
    }

    /**
     * Tests whether this queue is empty.
     * @return
     */
    @Override
    public boolean isEmpty() {

        return this.priorityQueue.isEmpty();
    }

    /**
     * Returns the maximum capacity of this queue.
     * @return The maximum capacity of this queue.
     */
    @Override
    public int capacity() {

        return this.priorityQueue.size();
    }

}
