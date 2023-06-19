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

    AdjacencyMatrixGraph<T> convert() {
        // TODO b)
        return null;
    }

    public AdjacencyListGraph<T> undirected() {
        // TODO: c)
        return null;
    }

    public boolean undirectedGraphIsBipartit() {
        // TODO: d)
        return false;
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
