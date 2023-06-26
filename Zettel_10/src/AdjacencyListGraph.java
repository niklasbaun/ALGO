import java.util.*;

public class AdjacencyListGraph<T> implements Graph<T> {

    private enum NodeColor {
        UNCOLORED,
        RED,
        GREEN
    }

    private NodeColor complement(NodeColor color) {
        // this should never be called with NodeColor.UNCOLORED
        return color == NodeColor.RED ? NodeColor.GREEN : NodeColor.RED;
    }

    Map<T, List<T>> map;

    public AdjacencyListGraph() {
        map = new HashMap<>();
    }

    @Override
    public boolean addNodeElement(T element) {
        if (map.containsKey(element)) {
            return false;
        }
        map.put(element, new LinkedList<>());
        return true;
    }

    @Override
    public boolean removeNodeElement(T element) {
        if (map.remove(element) == null) {
            return false;
        }
        for (List<T> list : map.values()) {
            list.remove(element);
        }
        return true;
    }

    @Override
    public void addEdge(T from, T to) throws InvalidEdgeException {
        if (!map.containsKey(from) || !map.containsKey(to)) {
            throw new InvalidEdgeException();
        }
        List<T> list = map.get(from);
        if (!list.contains(to)) {
            list.add(to);
        }
    }

    @Override
    public boolean removeEdge(T from, T to) throws InvalidNodeException {
        if (!map.containsKey(from) || !map.containsKey(to)) {
            throw new InvalidNodeException();
        }
        return map.get(from).remove(to);
    }

    /**
     * method to convert tge AdjacencyListGraph to an AdjacencyMatrixGraph
     * @return the converted AdjacencyMatrixGraph
     */
    AdjacencyMatrixGraph<T> convert() throws InvalidEdgeException {
        //create new AdjacencyMatrixGraph
        AdjacencyMatrixGraph<T> matrixGraph = new AdjacencyMatrixGraph<>();
        //add all nodes
        for (T node : map.keySet()) {
            matrixGraph.addNodeElement(node);
        }
        //add all edges
        for (T node : map.keySet()) {
            for (T other : map.get(node)) {
                matrixGraph.addEdge(node, other);
            }
        }
        return null;
    }

    /**
     * method to convert an AdjacencyListGraph to an undirected AdjacencyListGraph
     * @return the converted undirected AdjacencyListGraph
     */
    public AdjacencyListGraph<T> undirected() throws InvalidEdgeException {
        //create new AdjacencyListGraph
        AdjacencyListGraph<T> undirectedGraph = new AdjacencyListGraph<>();
        //add all nodes
        for (T node : map.keySet()) {
            undirectedGraph.addNodeElement(node);
        }
        //add all  existing edges
        //for each edge in the graph, also add the edge in the other direction
        for (T node : map.keySet()) {
            for (T other : map.get(node)) {
                undirectedGraph.addEdge(node, other);
                undirectedGraph.addEdge(other, node);
            }
        }
        return undirectedGraph;
    }

    /**
     * method to check if the AdjacencyListGraph is bipartit
     * bipartit means that the graph can be colored with two colors, so that no two adjacent nodes have the same color
     * @return true if the graph is bipartit, false if not
     */
    public boolean undirectedGraphIsBipartit() {
        //create array to store the color of each node
        NodeColor[] colors = new NodeColor[map.size()];
        //initialize all colors with UNCOLORED
        Arrays.fill(colors, NodeColor.UNCOLORED);
        //start at source node with one color
        colors[0] = NodeColor.RED;
        //create queue to store the nodes that have to be colored
        Queue<T> queue = new LinkedList<>();
        //add source node to queue
        queue.add((T) map.keySet().toArray()[0]);
        //while there are nodes to be colored
        while (!queue.isEmpty()) {
            //get next node
            T node = queue.poll();
            //get color of node
            NodeColor color = colors[getIndex(node)];
            //get complement color
            NodeColor complementColor = complement(color);
            //for each neighbor of the node
            for (T neighbor : map.get(node)) {
                //get index of neighbor
                int index = getIndex(neighbor);
                //if the neighbor has the same color as the node, the graph is not bipartit
                if (colors[index] == color) {
                    return false;
                }
                //if the neighbor has not been colored yet
                if (colors[index] == NodeColor.UNCOLORED) {
                    //color the neighbor with the complement color
                    colors[index] = complementColor;
                    //add the neighbor to the queue
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * helper method to get the index of a node
     * @param node
     * @return the index of the node
     */
    private int getIndex(T node) {
        int index = 0;
        for (T other : map.keySet()) {
            if (other.equals(node)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    void print() {
        for (T node : map.keySet()) {
            for (T other : map.get(node)) {
                System.out.println(node + " -> " + other);
            }
        }
    }

    public static void main(String[] args) throws InvalidEdgeException, InvalidNodeException {
        var graph = new AdjacencyListGraph<Character>();

        graph.addNodeElement('1');
        graph.addNodeElement('2');
        graph.addNodeElement('3');
        graph.addNodeElement('4');
        graph.addNodeElement('5');
        graph.addNodeElement('A');
        graph.addNodeElement('B');
        graph.addNodeElement('C');
        graph.addNodeElement('E');

        graph.addEdge('1', 'A');
        graph.addEdge('1', 'E');
        graph.addEdge('2', 'C');
        graph.addEdge('3', 'A');
        graph.addEdge('3', 'B');
        graph.addEdge('4', 'C');
        graph.addEdge('4', 'E');
        graph.addEdge('5', 'E');

        graph.print();
    }
}
