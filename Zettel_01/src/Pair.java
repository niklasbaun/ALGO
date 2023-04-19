/**
 * A generic implementation of pair consisting out of two elements.
 * @param <T> The type of the elements.
 */
public class Pair<T> {

    /**
     * The immutable first element of this pair.
     */
    private final T element1;
    /**
     * The immutable second element of this pair.
     */
    private final T element2;

    public Pair(T element1, T element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    /**
     * Returns the first element of this pair.
     * @return The first element.
     */
    public T getElement1() {
        return element1;
    }

    /**
     * Returns the second element of this pair.
     * @return The second element.
     */
    public T getElement2() {
        return element2;
    }
}
