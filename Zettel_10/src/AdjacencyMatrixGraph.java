public class AdjacencyMatrixGraph<T> implements Graph<T> {
    public static final int INITIAL_CAPACITY = 10;
    private boolean[][] matrix;

    public AdjacencyMatrixGraph() {
        matrix = new boolean[INITIAL_CAPACITY][INITIAL_CAPACITY];
    }

    @Override
    public boolean addNodeElement(T element) {
        // TODO a)
        return false;
    }

    @Override
    public boolean removeNodeElement(T element) {
        // TODO a)
        return false;
    }

    @Override
    public void addEdge(T from, T to) throws InvalidEdgeException {
        // TODO a)

    }

    @Override
    public boolean removeEdge(T from, T to) throws InvalidNodeException {
        // TODO a)
        return false;
    }

    AdjacencyListGraph<T> convert() {
        // TODO b)
        return null;
    }
}
