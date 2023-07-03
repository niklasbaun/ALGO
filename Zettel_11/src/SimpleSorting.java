
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.pow;


public class SimpleSorting {
    /**
     * Ceck if a given list is sorted
     *
     * @param list list of generic objects
     * @param comp comparator
     * @param <E>  type
     * @return true if sorted
     */
    static <E> boolean isSorted(List<E> list, Comparator<E> comp) {
        //check if list is null
        if (list == null) {
            return false;
        }
        //check if list is empty
        if (list.isEmpty()) {
            return true;
        }
        //check if list is sorted
        for (int i = 0; i < list.size() - 1; i++) {
            if (comp.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts list with selection sort
     *
     * @param list list of generic objects
     * @param comp comparator
     * @param <E>  type
     */
    static <E> void selectionSort(List<E> list, Comparator<E> comp) {
        //check if list is null
        if (list == null) {
            return;
        }
        //check if list is empty
        if (list.isEmpty()) {
            return;
        }
        //create a new list
        List<E> newList = new ArrayList<>();

        //for each element in the list
        //find the smallest element
        for (int i = 0; i < list.size(); i++) {
            E smallest = list.get(0);
            for (int j = 0; j < list.size(); j++) {
                if (comp.compare(list.get(j), smallest) < 0) {
                    smallest = list.get(j);
                }
            }
            //add the smallest element to the new list
            newList.add(smallest);
            //remove the smallest element from the old list
            list.remove(smallest);
        }
        //add all elements from the new list to the old list
        list.addAll(newList);
    }

    /**
     * Sorts list with insertion sort
     * @param list list of generic objects
     * @param comp comparator
     * @param <E>  type
     */
    static <E> void insertionSort(List<E> list, Comparator<E> comp) {
        //check if list is null
        if (list == null) {
            return;
        }
        //check if list is empty
        if (list.isEmpty()) {
            return;
        }
        //create a new list
        List<E> newList = new ArrayList<>();
        //add the first element to the new list
        newList.add(list.get(0));
        //for each element in the list
        //find the right position in the new list
        for (int i = 1; i < list.size(); i++) {
            E element = list.get(i);
            //remove the element from the old list
            list.remove(element);
            int j = 0;
            while (j < newList.size() && comp.compare(element, newList.get(j)) > 0) {
                j++;
            }
            //add the element to the new list
            newList.add(j, element);
        }
        //add all elements from the new list to the old list
        list.addAll(newList);
    }


    public static void main(String[] args) throws Exception {
        //test both sorting methods with different sized lists
        for (int i = 0; i < 5; i++){
            //create list of n random integers
            List<Integer> unsortedlist = new ArrayList<>();
            int n = (int) pow(10, i);
            for (int j = 0; j < n; j++) {
                unsortedlist.add((int) (Math.random() * n));
            }
            //create new list
            List<Integer> sortedList = new ArrayList<>();
            //add elements from the unsortedlist to the new list, but sorted
            for (int a = 0; a < unsortedlist.size(); a++) {
                int smallest = unsortedlist.get(0);
                for (int b = 0; b < unsortedlist.size(); b++) {
                    if (unsortedlist.get(b) < smallest) {
                        smallest = unsortedlist.get(b);
                    }
                }
                sortedList.add(smallest);
                unsortedlist.remove(smallest);
            }
            //run both sorting methods and compare the time
            long start = System.nanoTime();
            selectionSort(unsortedlist, Integer::compareTo);
            long end = System.nanoTime();
            //convert nanoseconds to milliseconds with three decimal places
            long time = (end - start);
            //check if the list is sorted
            System.out.println("Selection sort is sorted? (unsortedList): " + isSorted(unsortedlist, Integer::compareTo));
            System.out.println("Selection sort (unsortedList): " + time + "ns");
            start = System.nanoTime();
            selectionSort(sortedList, Integer::compareTo);
            end = System.nanoTime();
            time = (end - start);
            //check if the list is sorted
            System.out.println("Selection sort is sorted? (sortedList): " + isSorted(sortedList, Integer::compareTo));
            System.out.println("Selection sort (sortedList): " + time + "ns");

            start = System.nanoTime();
            insertionSort(unsortedlist, Integer::compareTo);
            end = System.nanoTime();
            time = (end - start);
            //check if the list is sorted
            System.out.println("Insertion sort is sorted? (unsortedList): " + isSorted(unsortedlist, Integer::compareTo));
            System.out.println("Insertion sort (unsortedList): " + time + "ns");
            start = System.nanoTime();
            insertionSort(sortedList, Integer::compareTo);
            end = System.nanoTime();
            time = (end - start);
            //check if the list is sorted
            System.out.println("Insertion sort is sorted? (sortedList): " + isSorted(sortedList, Integer::compareTo));
            System.out.println("Insertion sort (sortedList): " + time + "ns");
        }
        }

}
