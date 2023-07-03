import java.util.*;

public class AdjacencyListUndirectedWeightedGraph<T> implements UndirectedWeightedGraph<T> {

    protected Map<T, List<Edge<T>>> map;

    /**
     * Constructor
     */
    public AdjacencyListUndirectedWeightedGraph() {
        map = new HashMap<>();
    }

    /**
     * method to add an element to the graph
     * @param element the element which should be represented in the graph as a node
     * @return true if inserted successfully
     */
    @Override
    public boolean addNodeElement(T element) {
        //check if element is null
        if (element == null) {
            return false;
        }
        //check if element is already in the graph
        if (map.containsKey(element)) {
            return false;
        }
        //add element to the graph
        map.put(element, new ArrayList<>());
        return true;
    }

    /**
     * method to remove an element from the graph
     * @param element the element to remove
     * @return true if removed successfully
     */
    @Override
    public boolean removeNodeElement(T element) {
        //check if element is null
        if (element == null) {
            return false;
        }
        //check if element is in the graph
        if (!map.containsKey(element)) {
            return false;
        }
        //remove element from the graph
        map.remove(element);
        return true;
    }

    /**
     * method to add an edge between two elements
     * @param t1     one element connected by the edge
     * @param t2     the other element connected by the edge
     * @param weight the weight of the new edge
     * @throws InvalidEdgeException
     */
    @Override
    public void addEdge(T t1, T t2, int weight) throws InvalidEdgeException {
        //check sth is null
        if (t1 == null || t2 == null) {
            throw new InvalidEdgeException("One of the elements is null");
        }
        //check if elements are in the graph
        if (!map.containsKey(t1) || !map.containsKey(t2)) {
            throw new InvalidEdgeException("One of the elements is not in the graph");
        }
        //check if edge already exists
        if (map.get(t1).contains(new Edge<>(t1, t2, weight))) {
            throw new InvalidEdgeException("Edge already exists");
        }
        //add edge to the graph
        map.get(t1).add(new Edge<>(t1, t2, weight));
        map.get(t2).add(new Edge<>(t2, t1, weight));
    }

    /**
     * method to remove an edge between two elements
     * @param t1 one element connected by the edge
     * @param t2 the other element connected by the edge
     * @return true if removed successfully
     * @throws InvalidNodeException
     */
    @Override
    public boolean removeEdge(T t1, T t2) throws InvalidNodeException {
        //check sth is null
        if (t1 == null || t2 == null) {
            throw new InvalidNodeException("One of the elements is null");
        }
        //check if elements are in the graph
        if (!map.containsKey(t1) || !map.containsKey(t2)) {
            throw new InvalidNodeException("One of the elements is not in the graph");

        }
        //check if edge exists
        if (!map.get(t1).contains(new Edge<>(t1, t2, 0))) {
            return false;
        }
        //remove edge from the graph
        map.get(t1).remove(new Edge<>(t1, t2, 0));
        map.get(t2).remove(new Edge<>(t2, t1, 0));
        return true;
    }

    /**
     * method to implement the algorithm of Kruskal and print the result
     */
    public void kruskal() {
        //create a map to save the sets
        Map<T, Set<T>> sets = new HashMap<>();
        //create a new list of edges
        List<Edge<T>> result = new ArrayList<>();
        //put all edges into a PriorityQueue
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(map.size(), Comparator.comparingInt(Edge::getWeight));
        for (T t : map.keySet()) {
            for (Edge<T> edge : map.get(t)) {
                pq.add(edge);
            }
        }
        //create a set for each node
        for (T t : map.keySet()) {
            Set<T> set = new HashSet<>();
            set.add(t);
            sets.put(t, set);
        }
        //while there are edges left
        while (!pq.isEmpty()) {
            //get the edge with the lowest weight
            Edge<T> edge = pq.poll();
            //check if the nodes are in the same set
            if (!sets.get(edge.getT1()).equals(sets.get(edge.getT2()))) {
                //add the edge to the result
                result.add(edge);
                //merge the sets
                sets.get(edge.getT1()).addAll(sets.get(edge.getT2()));
                //remove the second set
                sets.remove(edge.getT2());
            }
        }
        //print the result
        for (Edge<T> edge : result) {
            System.out.println(edge.getT1() + " - " + edge.getT2() + " : " + edge.getWeight());
        }
    }

    public static void main(String[] args) throws InvalidEdgeException {
        AdjacencyListUndirectedWeightedGraph<Character> graph = new AdjacencyListUndirectedWeightedGraph<>();
        //add nodes
        graph.addNodeElement('A');
        graph.addNodeElement('B');
        graph.addNodeElement('C');
        graph.addNodeElement('D');
        graph.addNodeElement('E');
        graph.addNodeElement('F');
        graph.addNodeElement('G');
        graph.addNodeElement('H');

        //add edges
        graph.addEdge('A', 'B', 12);
        graph.addEdge('A', 'C', 8);
        graph.addEdge('A', 'G', 4);
        graph.addEdge('B', 'G', 3);
        graph.addEdge('C', 'G', 5);
        graph.addEdge('C', 'D', 10);
        graph.addEdge('D', 'F', 12);
        graph.addEdge('D', 'H', 6);
        graph.addEdge('F', 'H', 2);

        //use Kruskal on graph
        graph.kruskal();
    }
}