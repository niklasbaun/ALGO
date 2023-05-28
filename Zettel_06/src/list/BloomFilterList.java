package list;

import bloomfilter.BloomFilterImpl;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BloomFilterList<E> implements List<E>{

    private static class Node<T> {
        private T item;
        private RecursiveList.ListNode<T> next;

        /**
         * constructor to build a node with no successor
         *
         * @param element the value to be stored by this node
         */
        private Node(T element) {
            item = element;
            next = null;
        }

        /**
         * constructor to build a node with specified (maybe null) successor
         *
         * @param element   the value to be stored by this node
         * @param reference the next field for this node
         */
        private Node(T element, RecursiveList.ListNode<T> reference) {
            item = element;
            next = reference;
        }
    }

    private list.List<E> list;
    private BloomFilterImpl<E> bloomFilter;
    private Node<E> head;


    public BloomFilterList(List<E> list, BloomFilterImpl<E> bloomFilter) {
        this.list = list;
        this.bloomFilter = bloomFilter;
    }


    /**
     * get the size of the list
     * @return the size of the list
     */
    @Override
    public int getSize() {
        return this.getSize();
    }

    /**
     * check if the list is empty
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return List.super.isEmpty();
    }

    /**
     * add an element to the list
     * @param elem the element to add
     */
    @Override
    public void add(E elem) {
        //add the element to the list
        list.add(elem);
        //add the element to the bloomfilter
        bloomFilter.add(elem);
    }

    /**
     * get an element from the list at the given index
     *
     * @param index The index of the element to retrieve.
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        //check if index maybe out of bounds
        if(index < 0 || index > getSize())
            throw new IndexOutOfBoundsException();
        //get the element from the list

        return null;
    }

    @Override
    public boolean contains(E elem) {
        //check if the element is in the bloomfilter
        if(bloomFilter.contains(elem)){
            //check if the element is in the list
            if(list.contains(elem)){
                return true;
            }
        }
        
        return false;
    }

    /**
     * method to remove an element by the index
     * @param index The index of the element to remove.
     * @return the element
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        //check if index out of bounds
        if (index < 0 || index > this.getSize()){
            throw new IndexOutOfBoundsException();
        }
        E temp = list.get(index);
        list.remove(index);

        return temp;
    }

    /**
     * iterator for the list
     * @return the iterator
     */
    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    /**
     * forEach for the list
     * @param action the action to perform
     */
    @Override
    public void forEach(Consumer action) {
        List.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return List.super.spliterator();
    }
}
