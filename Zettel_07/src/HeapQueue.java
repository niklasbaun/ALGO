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

    public HeapQueue(int maximumCapacity) {
        //TODO
    }


    @Override
    public E poll() {
        // TODO
        return null;
    }

    @Override
    public void add(E element) {
        //TODO
    }

    public E peek() {
        //TODO
        return null;
    }

    @Override
    public void clear() {
        //TODO
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return false;
    }

    @Override
    public int capacity() {
        //TODO
        return 0;
    }

}
