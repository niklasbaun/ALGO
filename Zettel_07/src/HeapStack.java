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

    public HeapStack(int maximumCapacity) {
        //TODO
    }

    @Override
    public E pop()  {
        //TODO
        return null;
    }

    @Override
    public void push(E element) {
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

