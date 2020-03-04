package Utilities.Graph;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;
import javafx.util.Pair;

import javax.swing.tree.TreeNode;
import java.net.SocketTimeoutException;
import java.util.*;

/**
 * Graph Class, used to represent the bonus solution
 *
 * [BONUS]
 *
 * @author Loghin Vlad
 */
public class Graph {
    public static final int NO_WEIGHT = -1;
    private SortedSet<Pair<Integer, SortedSet<Node<Integer>>>> V;
    private Set<Edge<Integer, Float>> E;
    private Map<Node<Integer>, List<Node<Integer>>> adjacencyMap;

    private int knapsackCapacity;
    private Item[] items;
    private Knapsack knapsack;

    /**
     * Comparator functions used for SortedSets of nodes
     */
    private Comparator<Pair<Integer, SortedSet<Node<Integer>>>> comparatorItemSet = Comparator.comparing(Pair::getKey);
    private Comparator<Node<Integer>> comparatorWeightSet = Comparator.comparing(Node::getWeight);

    /**
     * Getter for verticles Sorted Set (V)
     * @return pointer to the set
     */
    public SortedSet<Pair<Integer, SortedSet<Node<Integer>>>> getV(){
        return this.V;
    }

    /**
     * Getter for the adjacency map
     * @return pointer to the map
     */
    public Map<Node<Integer>, List<Node<Integer>>> getAdjacencyMap(){
        return this.adjacencyMap;
    }

    /**
     * Getter for the edges set (E)
     * @return pointer to the set
     */
    public Set<Edge<Integer,Float>> getE(){
        return this.E;
    }

    /**
     * Getter for subset of a specific item
     * @param i item whose subset is requested
     * @return pointer to the subset
     * @throws RuntimeException if item index is invalid
     */
    public SortedSet<Node<Integer>> getVSubset(int i) throws RuntimeException{
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == i)
                return subset.getValue();
        throw new RuntimeException("Subset V of i does not exist");
    }

    /**
     * Internal method called to initialize node sets
     * @param itemCount number of items in the pool
     */
    private void initialiseSets(int itemCount){
        for( int subsetIndex = itemCount + 1; subsetIndex >= 0; subsetIndex-- )
            this.V.add(new Pair<>(subsetIndex, new TreeSet<>(comparatorWeightSet)));

        for (Pair<Integer, SortedSet<Node<Integer>>> subset : this.V) {
            if (subset.getKey() == 0)
                subset.getValue().add(new Node<>(0,NO_WEIGHT));
            if (subset.getKey() == itemCount + 1)
                subset.getValue().add(new Node<>( itemCount + 1, NO_WEIGHT));
        }
    }

    /**
     * Constructor
     * @param itemCount number of items available
     */
    public Graph(int itemCount){
        V = new TreeSet<>(comparatorItemSet);
        E = new HashSet<>();
        this.adjacencyMap = new HashMap<>();

        this.initialiseSets(itemCount);
    }

    /**
     * Constructor
     * @param items pointer to the items array
     * @param knapsack pointer to the knapsack object
     */
    public Graph(Item[] items, Knapsack knapsack){
        V = new TreeSet<>(comparatorItemSet);
        E = new HashSet<>();
        this.adjacencyMap = new HashMap<>();

        this.initialiseSets(items.length);
    }

    /**
     * Method called to add a node to the graph
     * @param item subset in which it will be added
     * @param weight value which it will contain
     * @throws RuntimeException if subset is invalid / does not exist
     */
    public void addNode(int item, int weight) throws RuntimeException{
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item) {
                subset.getValue().add(new Node<>(item,weight));
                return;
            }
        throw new RuntimeException("No such V^i set");
    }

    /**
     * Method called to add an existing node to the graph
     * @param item subset in which it will be added
     * @param node pointer to the node to be added
     * @throws RuntimeException if subset is invalid / does not exist
     */
    public void addNode( int item, Node<Integer> node ) throws RuntimeException{
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item) {
                subset.getValue().add(node);
                return;
            }
        throw new RuntimeException("No such V^i set");
    }

    /**
     * Method called to add an edge to the graph
     * @param edge pointer to the edge
     */
    public void addEdge(Edge<Integer, Float> edge){
        this.E.add(edge);
    }

    /**
     * Method called to create a new edge from verticle "from" to verticle "to" with cost "cost"
     * @param from vertcile from
     * @param to verticle to
     * @param cost cost of the edge
     */
    public void createEdge(Node<Integer> from, Node<Integer> to, float cost){
        this.E.add(new Edge<>(from, to, cost));
    }

    /**
     * Method called to check whether edge already exists [DEPRECATED]
     * @param edge object to be checked
     * @return true if item exists, false otherwise
     */
    public boolean edgeExists(Edge<Integer, Float> edge ){
        return this.E.contains(edge);
    }

    /**
     * Method called to check whether verticle (node) already exists [DEPRECATED]
     * @param item set in which it is checked
     * @param node pointer to the node the program is looking for
     * @return true if node exists, false otherwise
     */
    public boolean nodeExists(int item, Node<Integer> node){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item)
                if(subset.getValue().contains(node))
                    return true;
        return false;
    }

    /**
     * Getter for a specific node
     * @param item in which set is the node
     * @param weight what weight does the node "expect"
     * @return pointer to the node if it exists, pointer to a new node if it does not, null otherwise?
     */
    public Node<Integer> getNode(int item, int weight){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item){
                for( Node<Integer> node : subset.getValue() )
                    if ( node.getWeight() == weight )
                        return node;
                Node<Integer> newNode = new Node<>(item, weight);
                this.addNode(item, newNode);
                return newNode;
            }
        return null;
    }

    /**
     * Getter for a specific node
     * @param item in which set is the node
     * @param weight what weight does the node "expect"
     * @return pointer to the node if it exists, null otherwise
     */
    public Node<Integer> getNodeNoCreate(int item, int weight){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item)
                for( Node<Integer> node : subset.getValue() )
                    if ( node.getWeight() == weight )
                        return node;
        return null;
    }

    /**
     * Getter for an edge
     * @param from node from which the edge originates
     * @param to node to which the edge points
     * @return pointer to the edge if it exists, null otherwise
     */
    public Edge<Integer, Float> getEdge(Node<Integer> from, Node<Integer> to){
        for(Edge<Integer, Float> edge : this.E)
            if(edge.getFrom().equals(from) && edge.getTo().equals(to))
                return edge;
        return null;
    }
}
