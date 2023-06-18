import java.util.ArrayList;

public class GenTrie {
    private final GenTrie[] children;
    private boolean end;

    public GenTrie() {
        this.children = new GenTrie[4];
        this.end = false;
    }

    /**
     * Translates 'A' -> 0, 'B' -> 1, 'C' -> 2, 'T' -> 3
     */
    private static int charToIndex(char c) {
        //take the char and translate it to an int
        //check if the char is valid or not
        switch (c) {
            case 'A' -> {
                return 0;
            }
            case 'C' -> {
                return 1;
            }
            case 'G' -> {
                return 2;
            }
            case 'T' -> {
                return 3;
            }
        }
        //if not throw an exception
        throw new IllegalArgumentException("char is not valid");
    }

    /**
     * Translates 0 -> 'A', 1 -> 'C', 2 -> 'G', 3 -> 'T'
     */
    private static char indexToChar(int index) {
        //take the int and translate it to a char
        //check if the int is valid or not
        switch (index) {
            case 0 -> {
                return 'A';
            }
            case 1 -> {
                return 'C';
            }
            case 2 -> {
                return 'G';
            }
            case 3 -> {
                return 'T';
            }
        }
        //if not throw an exception
        throw new IllegalArgumentException("int is not valid");
    }

    /**
     * Check if a node is empty, i.e. it has no children and no end-marker, and should be deleted in the parent.
     *
     * @return true, if the node is empty.
     */
    private boolean isEmpty() {
        if (end) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (children[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Insert a gene sequence into this trie.
     *
     * @param seq The gene sequence.
     */
    public void insert(String seq) {
        //check if seq is null
        if (seq.length() == 0) {
            throw new IllegalArgumentException("cannot insert an empty sequence");
        }
        //create temp trie
        GenTrie temp = this;
        //split String into char array
        char[] seqArray = seq.toCharArray();
        //insert the char array into the trie
        for (int i = 0; i < seqArray.length; i++) {
            //check if the char is valid
            if (charToIndex(seqArray[i]) == -1) {
                throw new IllegalArgumentException("char is not valid");
            }
            //check if the char is already in the trie
            if (temp.children[charToIndex(seqArray[i])] == null) {
                //if not create a new trie
                temp.children[charToIndex(seqArray[i])] = new GenTrie();
            } else {
                //if yes go to that trie
                temp = temp.children[charToIndex(seqArray[i])];
            }
        }
        //set the end marker
        temp.end = true;
    }

    /**
     * Check if this trie contains the given gene sequence.
     *
     * @param seq The gene sequence
     * @return true, if this trie contains s
     */
    public boolean contains(String seq) {
        //check if seq is null
        if (seq.length() == 0) {
            throw new IllegalArgumentException("cannot insert an empty sequence");
        }
        //create temp trie
        GenTrie temp = this;
        //split String into char array
        char[] seqArray = seq.toCharArray();
        //check if the char array is in the trie
        for (int i = 0; i < seqArray.length; i++) {
            //check if the char is valid
            if (charToIndex(seqArray[i]) == -1) {
                throw new IllegalArgumentException("char is not valid");
            }
            //check if the char is already in the trie
            if (temp.children[charToIndex(seqArray[i])] == null) {
                return false;
            } else {
                //if yes go to that trie
                temp = temp.children[charToIndex(seqArray[i])];
            }
        }
        return true;
    }

    /**
     * Remove a gene sequence from the trie.
     *
     * @param seq The sequence.
     */
    public void remove(String seq) {
        //check if seq is null
        if (seq.length() == 0) {
            throw new IllegalArgumentException("cannot insert an empty sequence");
        }
        //create temp trie
        GenTrie temp = this;
        //split String into char array
        char[] seqArray = seq.toCharArray();

    }

    /**
     * Print all contained gene sequences in lexicographical order.
     */
    public void print() {
        //create a list of all the sequences
        ArrayList<String> sequences = new ArrayList<>();
        //create a string to store the sequence
        String seq = "";
        //start from the root and go through all the children till an end marker is found
        for (int i = 0; i < 4; i++) {
            //check if the child is null
            if (children[i] != null) {
                //if not add the char to the sequence
                seq += indexToChar(i);
                //check if the child has an end marker
                if (children[i].end) {
                    //if yes add the sequence to the list
                    sequences.add(seq);
                }
                //if not go to the next child
                children[i].print();
            }
        }
    }

    public static void main(String[] args) {
        // Note: assertions are only enabled if `-ea` is passed to java

        String[] sequences = {"AA", "AACG", "ACG", "ACT", "AG", "ATA", "ATCC", "ATCG", "ATG", "ATT", "CG", "CT", "G", "GTA", "TAG", "TAT", "TC"};

        GenTrie t = new GenTrie();

        for (var s : sequences) {
            t.insert(s);
        }

        for (var s : sequences) {
            assert t.contains(s);
        }

        t.print();

        t.remove(sequences[0]);
        t.remove(sequences[1]);
        assert !t.contains(sequences[0]);
        assert !t.contains(sequences[1]);

        System.out.println();
        t.print();

        for (var s : sequences) {
            t.remove(s);
        }

        assert t.isEmpty();
    }
}