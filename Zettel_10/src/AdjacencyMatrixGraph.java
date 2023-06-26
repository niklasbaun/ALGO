import java.util.List;

public class AdjacencyMatrixGraph<T> implements Graph<T> {
    public static final int INITIAL_CAPACITY = 10;
    private boolean[][] matrix;
    //list of all nodes
    private List<T> nodes;

    public AdjacencyMatrixGraph() {
        matrix = new boolean[INITIAL_CAPACITY][INITIAL_CAPACITY];
    }

    /**
     * Add a node to the graph with the given element.
     *
     * @param element the element which should be represented in the graph as a node
     * @return true, if the element was added; false, if the element already existed.
     */
    @Override
    public boolean addNodeElement(T element) {
        //check if element null
        if(element == null){
            return false;
        }
        //check if element already exists
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).equals(element)){
                return false;
            }
        }
        //check if matrix is full
        if(nodes.size() == matrix.length){
            //create new matrix with double size
            boolean[][] newMatrix = new boolean[matrix.length*2][matrix.length*2];
            //copy old matrix to new matrix
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
            }
            //set new matrix as matrix
            matrix = newMatrix;
        }
        //add element to list
        nodes.add(element);
        return true;
    }

    /**
     * Remove the node with the given element from the graph.
     *
     * @param element the element to remove
     * @return true, if the element was removed; false, if the element did not exist.
     */
    @Override
    public boolean removeNodeElement(T element) {
        //check if element null
        if(element == null){
            return false;
        }
        //check if element exists
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).equals(element)){
                //remove element from list
                nodes.remove(i);
                //remove all edges from and to element
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = false;
                    matrix[j][i] = false;
                }
                //close gaps in matrix
                for (int j = i; j < matrix.length-1; j++) {
                    for (int k = 0; k < matrix.length; k++) {
                        matrix[j][k] = matrix[j+1][k];
                        matrix[k][j] = matrix[k][j+1];
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * add a new edge to the graph
     *
     * @param from the source element of the edge
     * @param to   the destination element of the edge
     * @throws InvalidEdgeException if one of the two elements is not part of the graph
     */
    @Override
    public void addEdge(T from, T to) throws InvalidEdgeException {
        //check if elements null
        if(from == null || to == null){
            throw new InvalidEdgeException("One of the elements is null");
        }
        //check if elements exist
        if (!nodes.contains(from)){
            throw new InvalidEdgeException("The from element does not exist");
        }
        if (!nodes.contains(to)){
            throw new InvalidEdgeException("The to element does not exist");
        }
        //add edge
        matrix[nodes.indexOf(from)][nodes.indexOf(to)] = true;
        matrix[nodes.indexOf(to)][nodes.indexOf(from)] = true;

    }

    /**
     * removes edge with given source and destination from graph
     *
     * @param from the source element of the edge
     * @param to   the destination element of the edge
     * @return true, if the edge was removed; false, if the edge did not exist.
     * @throws InvalidNodeException if one of the two elements is not part of the graph
     */
    @Override
    public boolean removeEdge(T from, T to) throws InvalidNodeException {
        //check if elements null
        if(from == null || to == null){
            throw new InvalidNodeException("One of the elements is null");
        }
        //check if elements exist
        if (!nodes.contains(from)){
            throw new InvalidNodeException("The from element does not exist");
        }
        if (!nodes.contains(to)){
            throw new InvalidNodeException("The to element does not exist");
        }
        //remove edge
        matrix[nodes.indexOf(from)][nodes.indexOf(to)] = false;
        matrix[nodes.indexOf(to)][nodes.indexOf(from)] = false;
        return true;
    }

    /**
     * method to convert tge AdjacencyMatrixGraph to an AdjacencyListGraph
     * @return
     */
    AdjacencyListGraph<T> convert() {
        //create new AdjacencyListGraph
        AdjacencyListGraph<T> listGraph = new AdjacencyListGraph<>();
        //add all nodes
        for (int i = 0; i < nodes.size(); i++) {
            listGraph.addNodeElement(nodes.get(i));
        }
        //add all edges
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if(matrix[i][j]){
                    try {
                        listGraph.addEdge(nodes.get(i), nodes.get(j));
                    } catch (InvalidEdgeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return listGraph;
    }
}
