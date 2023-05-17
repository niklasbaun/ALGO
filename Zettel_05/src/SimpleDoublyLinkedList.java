import java.util.NoSuchElementException;

public class SimpleDoublyLinkedList<E> {

    static class Node<E> {
        final E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    Node<E> head;
    Node<E> tail;

    public void addLast(E element) {
        tail = new Node<>(element, tail, null);
        if (head == null)
            head = tail;
    
        if (tail.prev != null)
            tail.prev.next = tail;
    }

    public void addFirst(E element) {
        if (isEmpty()) {
            addLast(element);
            return;
        }

        head = new Node<>(element, null, head);
        if (head.next != null)
            head.next.prev = head;
    }

    public E removeLast() {
        if (isEmpty())
            throw new IllegalStateException("Empty list");

        if (tail.prev != null)
            tail.prev.next = null;

        E last = tail.element;
        tail = tail.prev;
        return last;
    }

    public E get(int n) {
        if (isEmpty())
            throw new NoSuchElementException();

        Node<E> current;
        for (current = head; current.next != null && n != 0; current = current.next, n--) ;

        if (n == 0)
            return current.element;

        throw new NoSuchElementException();
    }

    boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("(Head) ");
        for (Node<?> curr = head; curr != null; curr = curr.next)
            str.append(curr.element).append(" <--> ");

        str.setLength(str.length() - " <--> ".length());
        return str.append(" (Tail)").toString();
    }
}
