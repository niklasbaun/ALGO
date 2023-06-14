import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * This class represents a binary heap
 *
 * @param <T> The heap's element type
 */

public class Heap<T> {
    /**
     * Constant: The initial capacity, if not set
     */
    public static int DEFAULT_CAPACITY = 16;

    /**
     * Holds the elements of this heap
     */
    private T[] elements;

    /**
     * The number of elements stored in this heap
     */
    private int number;

    /**
     * The element comparator
     */
    private final Comparator<T> comparator;

    /**
     * @param comparator Comparator for elements
     */
    @SuppressWarnings("unchecked")
    public Heap(Comparator<T> comparator) {
        this.number = 0;
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    /**
     * Creates a heap from the given array. This method assumes, that the given
     * array is completely filled with elements
     *
     * @param fromArray  the values to fill this heap with
     * @param comparator Comparator for elements
     */
    @SuppressWarnings("unchecked")
    public Heap(T[] fromArray, Comparator<T> comparator) {
        this.number = fromArray.length;
        this.comparator = comparator;
        // Set the array length as the next power of 2 greater than the number of
        // elements
        int internalSize = Integer.highestOneBit(fromArray.length) << 1;
        this.elements = (T[]) new Object[internalSize];
        // Copy array, leaving first position empty
        System.arraycopy(fromArray, 0, this.elements, 1, fromArray.length);

        //heapify the array to restore the heap property
        for (int i = this.number >> 1; i > 0; i--) {
            this.downheap(i);
        }
        

    }

    /**
     * @return the number of elements in this heap
     */
    public int size() {
        return this.number;
    }

    /**
     * @return true, if this heap contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.number == 0;
    }

    /**
     * Retrieves the top element of this heap, but does not remove it
     *
     * @return the top element of this heap
     */
    public T peek() {
        if (this.number == 0)
            throw new NoSuchElementException("Heap is empty");

        return this.elements[1];
    }

    /**
     * Adds the given elemet to this heap and restores the heap property
     *
     * @param element the element to add
     */
    public void add(T element) {
        if (++this.number >= this.elements.length)
            this.resize();

        this.elements[this.number] = element;
        this.upheap(this.number);
    }

    /**
     * Removes the top element of this heap (if any)
     */
    public void remove() {
        if (this.number <= 0)
            throw new NoSuchElementException("Heap is empty");

        this.elements[1] = this.elements[this.number--];
        this.downheap(1);
    }

    /**
     * Doubles the capacity of the storage array
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] freshElements = (T[]) new Object[this.elements.length << 1];
        System.arraycopy(this.elements, 0, freshElements, 0, this.elements.length);
        this.elements = freshElements;
    }

    /**
     * Bubbles the element at the given position up the heap into its correct position
     *
     * @param index the start position
     */
    private void upheap(int index) {
        if (index == 1)
            return;

        if (this.comparator.compare(this.elements[index], this.elements[index / 2]) < 0) {
            this.elements[0] = this.elements[index];
            this.elements[index] = this.elements[index / 2];
            this.elements[index / 2] = this.elements[0];
            this.upheap(index / 2);
        }
    }

    /**
     * Bubbles the element at the given position down the heap into its correct position
     *
     * @param index the start position
     */
    private void downheap(int index) {
        if (!((2 * index > this.number || this.comparator.compare(this.elements[index], this.elements[2 * index]) <= 0) &&
                (2 * index + 1 > this.number || this.comparator.compare(this.elements[index], this.elements[2 * index + 1]) <= 0))) {
            int i = 2 * index; // Try left child first
            if (i + 1 <= this.number && this.comparator.compare(this.elements[i], this.elements[i + 1]) > 0)
                i++; // Right child smaller than left

            // Swap and continue
            this.elements[0] = this.elements[index];
            this.elements[index] = this.elements[i];
            this.elements[i] = this.elements[0];
            this.downheap(i);
        }
    }

    /**
     * Finds the k-th smallest element using naive heap construction
     *
     * @param elements integer array of elements
     * @param k       the k-th smallest element to find
     * @return k smallest element.
     */
    private static Integer findKSmallestNaive(Integer[] elements, int k) {
        if (k <= 0)
            throw new IllegalArgumentException("k must be >= 1");

        //insert all elements into a heap
        Heap<Integer> heap = new Heap<>(Integer::compare);
        for (Integer element : elements) {
            heap.add(element);
        }
        //loop k times and remove the top element
        for (int i = 0; i < k - 1; i++) {
            heap.remove();
        }
        //return the top element
        return heap.peek();
    }

    /**
     * Finds the k-th smallest element using efficient heap construction
     *
     * @param elems
     * @param k
     * @return k smallest element.
     */
    private static Integer findKSmallestHeapify(Integer[] elems, int k) {
        if (k <= 0)
            throw new IllegalArgumentException("k must be >= 1");

        //use the heap constructor to build a heap from the array
        Heap<Integer> heap = new Heap<>(elems, Integer::compare);
        //loop k times and remove the top element
        for (int i = 0; i < k - 1; i++) {
            heap.remove();
        }
        //return the top element
        return heap.peek();
    }

    /**
     * Finds the k-th smalles element using a max heap
     *
     * @param elems
     * @param k
     * @return k smallest element.
     */
    private static Integer findKSmallestMaxHeap(Integer[] elems, int k) {
        if (k <= 0)
            throw new IllegalArgumentException("k must be >= 1");

        //use max heap method to build a heap from the array
        //insert k elements into the heap
        Heap<Integer> heap = new Heap<>(Integer::compare);
        for (int i = 0; i < k; i++) {
            heap.add(elems[i]);
        }
        //loop through the rest of the array foreach insert remove largest element
        for (int i = k; i < elems.length; i++) {
            if (elems[i] < heap.peek()) {
                heap.remove();
                heap.add(elems[i]);
            }
        }
        //return the top element
        return heap.peek();
    }

    public static void main(String[] args) {
        final int NUM_VALUES = 100_000_000;

        System.out.println("Building array.");
        Random rand = new Random(System.nanoTime());
        Integer[] values = new Integer[NUM_VALUES];

        for (int i = 0; i < NUM_VALUES; i++)
            values[i] = rand.nextInt(NUM_VALUES * 20);

        for (int k = 1; k < 10; k++) {
            System.out.println("k = " + k + ":");
            // Naive
            {
                long time = -System.currentTimeMillis();
                Integer v = findKSmallestNaive(values, k);
                time += System.currentTimeMillis();
                System.out.println("Naive (" + v + "): " + time);
            }

            // Heapify
            {
                long time = -System.currentTimeMillis();
                Integer v = findKSmallestHeapify(values, k);
                time += System.currentTimeMillis();
                System.out.println("Heapify (" + v + "): " + time);
            }

            // max heap
            {
                long time = -System.currentTimeMillis();
                Integer v = findKSmallestMaxHeap(values, k);
                time += System.currentTimeMillis();
                System.out.println("MaxHeap (" + v + "): " + time);
            }
        }
    }
}
