import org.junit.jupiter.api.MethodOrderer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SortVariants {
    /**
     * Sorts list with naive quick sort (always the right most element as pivot)
     *
     * @param list       list of generic objects
     * @param comparator comparator
     * @param left       left index
     * @param right      right index
     * @param <E>        type
     */
    private static <E> void naiveQuickSort(List<E> list, Comparator<E> comparator, int left, int right) {
        //check if list is null or empty
        if (list == null || list.isEmpty()) {
            return;
        }
        //
        if(right-left <= 0) {
            return;
        } else {
            int partition = partition(list, comparator, left, right, right);
            //call with each halfs of the list
            naiveQuickSort(list, comparator, left, partition - 1);
            naiveQuickSort(list, comparator, partition + 1, right);
        }
    }

    /**
     * Sorts list with random quick sort (random pivot)
     *
     * @param list       list of generic objects
     * @param comparator comparator
     * @param left       left index
     * @param right      right index
     * @param <E>        type
     */
    private static <E> void randomQuickSort(List<E> list, Comparator<E> comparator, int left, int right) {
        //check if list is null or empty
        if (list == null || list.isEmpty()) {
            return;
        }
        //check if left index is smaller than right index
        if(left >= right) {
            return;
        } else {
            //calculate random pivot
            Random random = new Random();
            int pivotIndex = random.nextInt(right-left) + left;
            int partition = partition(list, comparator, left, right, pivotIndex);
            //call with each halfs of the list
            naiveQuickSort(list, comparator, left, partition - 1);
            naiveQuickSort(list, comparator, partition + 1, right);
        }


    }

    /**
     * helper method partition for quick sort
     * @param list list of generic objects
     * @param comparator comparator
     * @param <E> type
     */
    private static <E> int partition(List<E> list, Comparator<E> comparator, int left, int right, int pivot){
        //move left index to the right
        int leftIndex = left-1;
        //move right index to the left
        int rightIndex = right;
        //set pivot index
        int pivotIndex = pivot;

        for(int i=left; i < right; i++){
            //if element at index i is smaller than pivot
            if(comparator.compare(list.get(i), list.get(pivotIndex)) < 0){
                //move left index to the right
                leftIndex++;
                //swap elements at index i and left index
                E temp = list.get(i);
                list.set(i, list.get(leftIndex));
                list.set(leftIndex, temp);
            }
        }
        //swap elements at left index and pivot index
        E temp = list.get(leftIndex+1);
        list.set(leftIndex+1, list.get(pivotIndex));
        list.set(pivotIndex, temp);
        return leftIndex+1;
    }

    public static <E> void naiveQuickSort(List<E> list, Comparator<E> comparator) {
        naiveQuickSort(list, comparator, 0, list.size() - 1);
    }

    public static <E> void randomQuickSort(List<E> list, Comparator<E> comparator) {
        randomQuickSort(list, comparator, 0, list.size() - 1);
    }

    /**
     * Sorts list with new recursive sort
     * compares first and last element and swaps them if necessary; 2nd and 2nd last element and so on
     *
     * @param list       list of generic objects
     * @param comparator comparator
     * @param <E>        type
     */
    public static <E> void newRecursiveSort(List<E> list, Comparator<E> comparator) {
        //check if list is null or empty or has one or less elements
        if (list == null || list.isEmpty() || list.size() <= 1) {
            return;
        }
        //bool to check if sth was swapped
        boolean swapped = true;
        int start = 0;
        int end = list.size() - 1;
        //while sth was swapped iterate through list
        while(swapped){
            swapped = false;

            //iterate through list from left to the middle
            for (int i = start; i < end/2; i++){
                //if element at index i with element at index end-i
                if(comparator.compare(list.get(i), list.get(end - i)) > 0){
                    //swap elements at index i and i+1
                    E temp = list.get(i);
                    list.set(i, list.get(end - i));
                    list.set(end- i, temp);
                    //set swapped to true
                    swapped = true;
                }
            }
            //if nothing was swapped break
            if(!swapped){
                break;
            }
        }
        //call method with first half of the list
        newRecursiveSort(list.subList(start, end/2), comparator);
        //call method with second half of the list
        newRecursiveSort(list.subList(end/2, end), comparator);
    }

    /**
     * sorts list with fisher yates shuffle
     * @param list list of generic objects
     * @param <E> type
     */
    public static <E> void shuffle(List<E> list) {
        //create Random obj
        Random random = new Random();

        //starting from last element in the list, add random element from old list to temp list
        for (int i = list.size() - 1; i >= 0; i--) {
            //get random index
            int randomIndex = random.nextInt(i + 1);
            //swap element from random index with element at i
            E temp = list.get(randomIndex);
            list.set(randomIndex, list.get(i));
            list.set(i, temp);
        }

    }

    /**
     * Sorts list with bogo sort
     * @param list list of generic objects
     * @param comparator comparator
     * @param <E> type
     */
    public static <E> void bogoSort(List<E> list, Comparator<E> comparator) {
        //check if list is null or empty
        if (list == null || list.isEmpty()) {
            return;
        }
        while (!isSorted(list, comparator)) {
            shuffle(list);
        }
    }

    /**
     * checks if list is sorted
     * @param list list of generic objects
     * @param comparator comparator
     * @param <E> type
     * @return true if list is sorted, false if not
     */
    private static <E> boolean isSorted(List<E> list, Comparator<E> comparator) {
        //for each element in the list check if the next element is bigger than the current element
        for (int i = 0; i < list.size() - 1; i++) {
            if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //create list with random numbers without duplicates
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 100);
            if (!list.contains(random)) {
                list.add(random);
            }
        }
        //copy list
        List<Integer> listNaive = new ArrayList<>(list);
        List<Integer> listRandom = new ArrayList<>(list);
        List<Integer> listNewRecursive = new ArrayList<>(list);
        List<Integer> listBogo = new ArrayList<>(list);
        //create comparator
        Comparator<Integer> comparator = Comparator.naturalOrder();
        //use sorting methods and measure time
        //Naive Quick Sort
        long start = System.nanoTime();
        naiveQuickSort(listNaive, comparator, 0, listNaive.size() - 1);
        long end = System.nanoTime();
        System.out.println("Naive Quick Sort: " + (end - start) + " ns");
        //check if list is sorted
        System.out.println("Naive Quick Sort: " + isSorted(listNaive, comparator));
        //print each element of the list
        for (Integer integer : listNaive) {
            System.out.print(integer + "; ");
        }
        //new line
        System.out.println("\n");

        //Random Quick Sort
        start = System.nanoTime();
        randomQuickSort(listRandom, comparator, 0, listRandom.size() - 1);
        end = System.nanoTime();
        System.out.println("Random Quick Sort: " + (end - start) + " ns");
        //check if list is sorted
        System.out.println("Random Quick Sort: " + isSorted(listRandom, comparator));
        //print each element of the list
        for (Integer integer : listRandom) {
            System.out.print(integer + "; ");
        }
        //new line
        System.out.println("\n");

        //New Recursive Sort
        start = System.nanoTime();
        newRecursiveSort(listNewRecursive, comparator);
        end = System.nanoTime();
        end = System.nanoTime();
        System.out.println("New Recursive Sort: " + (end - start) + " ns");
        //check if list is sorted
        System.out.println("New Recursive Sort: " + isSorted(listNewRecursive, comparator));
        //print each element of the list
        for (Integer integer : listNewRecursive) {
            System.out.print(integer + "; ");
        }
        //new line
        System.out.println("\n");

        //Bogo Sort
        start = System.nanoTime();
        bogoSort(listBogo, comparator);
        end = System.nanoTime();
        System.out.println("Bogo Sort: " + (end - start) + " ns");
        //check if list is sorted
        System.out.println("Bogo Sort: " + isSorted(listBogo, comparator));
        //print each element of the list
        for (Integer integer : listBogo) {
            System.out.print(integer + "; ");
        }
    }
}
