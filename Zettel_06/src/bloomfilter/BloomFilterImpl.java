package bloomfilter;

import list.RecursiveList;

import java.util.List;
import java.util.Random;

public class BloomFilterImpl<E> implements BloomFilter<E> {
    private Boolean[] bitList;
    private HashFunction<Integer>[] hashFunctions;

    /**
     * Constructor for the BloomFilterImpl
     * @param
     */
    public BloomFilterImpl(int sizeList,int numHashFunctions){
        //create a new bitList with the given size
        bitList = new Boolean[sizeList];
        Random rnd = new Random(1337);
        //create a new array of hashfunctions
        hashFunctions = new HashFunction[numHashFunctions];
        //fill the array with new hashfunctions
        for (int i = 0; i < numHashFunctions; i++) {
            hashFunctions[i] = new HashFunction<Integer>() {
                @Override
                public int hash(Integer e) {
                    return e % rnd.nextInt();
                }
            };
        }
    }

    /**
     * add an element to the BloomFilter
     * @param element the element to add
     */
    @Override
    public void add(E element) {
        //iterate over the hashfunctions
        for (int i = 0; i < hashFunctions.length; i++) {
            //get the hashvalue of the element
            int hashValue = hashFunctions[i].hash((Integer) element);
            //set the bit at the hashvalue to true
            bitList[hashValue] = true;
        }
    }

    /**
     * check if the BloomFilter contains an element
     * @param element the element to check
     * @return true if the element is in the BloomFilter, false otherwise
     */
    @Override
    public boolean contains(E element) {
        //iterate over the hashfunctions
        for (int i = 0; i < hashFunctions.length; i++) {
            //get the hashvalue of the element
            int hashValue = hashFunctions[i].hash((Integer) element);
            //check if the bit at the hashvalue is true
            if(bitList[hashValue] == null || !bitList[hashValue]){
                //if not return false
                return false;
            }
        }
        return true;
    }

    /**
     * empty the BloomFilter
     */
    @Override
    public void empty() {
        //set all bits to false
        for (int i = 0; i < bitList.length; i++) {
            bitList[i] = false;
        }
    }
}
