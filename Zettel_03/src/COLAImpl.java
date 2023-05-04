// TODO: Add Interface (3.3 b))

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class COLAImpl<E extends Comparable<E>> implements Insert<E>, Query<E> {

    // TODO: Add data representation (3.3 a))
    //List of arrays to represent the COLA
    private ArrayList<E[]> cola = new ArrayList<E[]>();
    //List of booleans to represent if the array is empty
    private ArrayList<Boolean> empty = new ArrayList<Boolean>();

    public void insertElement(E key) {
        //arrayList to save the overflow
        ArrayList<E> overflow = new ArrayList<E>();
      //check if COLA is empty
      if (this.cola.isEmpty()){
          //add a new array with size 1
          this.cola.add((E[]) new Comparable[1]);
          //insert the key
            this.cola.get(0)[0] = key;
            //set the empty boolean to false for that array
            this.empty.add(false);
      } else{
          //check from top-down if the arrays are full
          //check if the 1st array is full
          if (empty.get(0) == true){
                //if the array is full add the key to the overflow
                overflow.add(key);
            } else {
                //if the array is not full add the key to the array
                this.cola.get(0)[0] = key;
                //set the empty boolean to true for that array
                this.empty.set(0, true);
          }
          //start from beginning of the list
          for(int n = 1; n < cola.size(); n++){
              //if the array is empty or has empty space
              if (cola.get(n)[0] == null || cola.get(n)[cola.get(n).length/2] == null){
                  //mergesort the overflow into the existing array
                  //foreach element in the array add them to the overflow list
                    for(int i = 0; i < overflow.size(); i++){
                        //add to temp
                        overflow.add(cola.get(n)[i]);
                        //delete the element from the array
                        cola.get(n)[i] = null;
                    }
                    //sort them and turn them into an array
                    E[] newArray = sort((E[]) overflow.toArray(), 0, overflow.size()-1);
                    //insert again into COLA
                    for(int i = 0; i < newArray.length; i++){
                        cola.get(n)[i] = newArray[i];
                    }
                    //set the empty boolean to false for that array
                    empty.set(n, false);
                    //stop the loop
                    break;
              } else {
                  //when no empty space is found add the current array to the overflow
                    for (int i = 0; i < cola.get(n).length; i++) {
                        overflow.add(cola.get(n)[i]);
                        //delete the element from the array
                        cola.get(n)[i] = null;
                        //set empty boolean to true
                        empty.set(n, true);
                    }
              }
              //if at the end of the list and no empty space is found create a new array with double the size of the previous array
              if (n == cola.size()){
                  //add new array with double the size of the previous array
                  cola.add((E[]) new Comparable[cola.get(n-1).length*2]);
                  //sort them and turn them into an array
                  E[] newArray = sort((E[]) overflow.toArray(), 0, overflow.size()-1);
                  //insert again into COLA
                  for(int i = 0; i < newArray.length; i++){
                      cola.get(n)[i] = newArray[i];
                  }
                  //set the empty boolean to false for that array
                  empty.add(n, false);
              }
          }
      }
    }


    /**
     * method to sort an array
     * @param arr the array to sort
     * @param l the left index
     * @param r the right index
     */
    private E[] sort(E[] arr, int l, int r) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        if (l >= r) {
            return null;
        } else {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
        return arr;
    }


    /**
     * method to merge two arrays
     * @param arr the array to merge, which will be split
     * @param l the left index
     * @param m the middle index
     * @param r the right index
     */
    void merge(E[] arr, int l, int m, int r) {

        int size = arr.length;
        if (size > 1) {

            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            // Create temp arrays left and right
            E[] L = (E[]) new Comparable[n1];
            E[] R = (E[]) new Comparable[n2];

            //Copy data to temp arrays
            for (int i = 0; i < n1; i++)
                L[i] = arr[l + i];
            for (int j = 0; j < n2; j++)
                R[j] = arr[m + 1 + j];

            // Merge the temp arrays

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarray array
            int k = l;
            while (i < n1 && j < n2) {
                //if the left element is smaller than the right element
                arr[k++] = (L[i].compareTo(R[j]) <= 0) ? L[i++] : R[j++];
            }

            // Copy remaining elements of L[] if any
            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            // Copy remaining elements of R[] if any
            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }
    }

    // TODO: Implement search methods (3.3 b))

    /**
     * Searches the element with a top-down algorithm, i.e.,
     * @param element The element of the element
     * @return instance of this element in the data structure;
     */
    public E searchElement(E element) {
        for (int i = 0; i < cola.size(); i++) {
            int e = Arrays.binarySearch(cola.get(i), element);
            if (e >= 0) {
                return cola.get(i)[e];
            }
        }
        return null;
    }

}
