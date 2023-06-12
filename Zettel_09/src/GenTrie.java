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
        // TODO
        return 0;
    }

    /**
     * Translates 0 -> 'A', 1 -> 'C', 2 -> 'G', 3 -> 'T'
     */
    private static char indexToChar(int index) {
        // TODO
        return 'A';
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
        if (seq.length() == 0) {
            throw new IllegalArgumentException("cannot insert an empty sequence");
        }
        // TODO
    }

    /**
     * Check if this trie contains the given gene sequence.
     *
     * @param seq The gene sequence
     * @return true, if this trie contains s
     */
    public boolean contains(String seq) {
        // TODO
        return false;
    }

    /**
     * Remove a gene sequence from the trie.
     *
     * @param seq The sequence.
     */
    public void remove(String seq) {
        // TODO
    }

    /**
     * Print all contained gene sequences in lexicographical order.
     */
    public void print() {
        // TODO
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