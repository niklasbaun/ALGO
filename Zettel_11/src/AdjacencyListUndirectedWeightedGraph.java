import java.util.*;

public class AdjacencyListUndirectedWeightedGraph<T> implements UndirectedWeightedGraph<T> {

    protected Map<T, List<Edge<T>>> map;

    public AdjacencyListUndirectedWeightedGraph() {
        map = new HashMap<>();
    }

    @Override
    public boolean addNodeElement(T element) {
        // TODO 2.a)
        return false;
    }

    @Override
    public boolean removeNodeElement(T element) {
        // TODO 2.a)
        return false;
    }

    @Override
    public void addEdge(T t1, T t2, int weight) throws InvalidEdgeException {
        // TODO 2.a)
    }

    @Override
    public boolean removeEdge(T t1, T t2) throws InvalidNodeException {
        // TODO 2.a)
        return false;
    }

    public void kruskal() {
        // TODO: 2.b)
    }

    public static void main(String[] args) throws InvalidEdgeException {
        AdjacencyListUndirectedWeightedGraph<Character> graph = new AdjacencyListUndirectedWeightedGraph<>();
        // TODO: 2.b)
    }
}