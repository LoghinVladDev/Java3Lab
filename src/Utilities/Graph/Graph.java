package Utilities.Graph;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;
import javafx.util.Pair;

import javax.swing.tree.TreeNode;
import java.net.SocketTimeoutException;
import java.util.*;

public class Graph {
    public static final int NO_WEIGHT = -1;
    private SortedSet<Pair<Integer, SortedSet<Node<Integer>>>> V;
    private Set<Edge<Integer, Float>> E;
    private Map<Node<Integer>, List<Node<Integer>>> adjacencyMap;

    private int knapsackCapacity;
    private Item[] items;
    private Knapsack knapsack;

    private Comparator<Pair<Integer, SortedSet<Node<Integer>>>> comparatorItemSet = Comparator.comparing(Pair::getKey);
    private Comparator<Node<Integer>> comparatorWeightSet = Comparator.comparing(Node::getWeight);

    public SortedSet<Pair<Integer, SortedSet<Node<Integer>>>> getV(){
        return this.V;
    }

    public Map<Node<Integer>, List<Node<Integer>>> getAdjacencyMap(){
        return this.adjacencyMap;
    }

    public Set<Edge<Integer,Float>> getE(){
        return this.E;
    }

    public SortedSet<Node<Integer>> getVSubset(int i) throws RuntimeException{
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == i)
                return subset.getValue();
        throw new RuntimeException("Subset V of i does not exist");
    }

    private void initialiseSets(int itemCount){
        for( int subsetIndex = itemCount + 1; subsetIndex >= 0; subsetIndex-- ){
            this.V.add(new Pair<>(subsetIndex, new TreeSet<>(comparatorWeightSet)));
        }

        for (Pair<Integer, SortedSet<Node<Integer>>> subset : this.V) {
            if (subset.getKey() == 0)
                subset.getValue().add(new Node<Integer>(0,NO_WEIGHT));
            if (subset.getKey() == itemCount + 1)
                subset.getValue().add(new Node<Integer>( itemCount + 1, NO_WEIGHT));
        }

    }

    public Graph(int itemCount){
        V = new TreeSet<>(comparatorItemSet);
        E = new HashSet<>();
        this.adjacencyMap = new HashMap<>();

        this.initialiseSets(itemCount);
    }

    public Graph(Item[] items, Knapsack knapsack){
        V = new TreeSet<>(comparatorItemSet);
        E = new HashSet<>();
        this.adjacencyMap = new HashMap<>();

        this.initialiseSets(items.length);
    }

    public void addNode(int item, int weight) throws RuntimeException{
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item) {
                subset.getValue().add(new Node<Integer>(item,weight));
                return;
            }
        throw new RuntimeException("No such V^i set");
    }

    public void addNode( int item, Node<Integer> node ) throws RuntimeException{
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item) {
                subset.getValue().add(node);
                return;
            }
        throw new RuntimeException("No such V^i set");
    }

    public void addEdge(Edge<Integer, Float> edge){
        this.E.add(edge);
    }

    public void createEdge(Node<Integer> from, Node<Integer> to, float cost){
        this.E.add(new Edge<Integer, Float>(from, to, cost));
    }

    public boolean edgeExists(Edge<Integer, Float> edge ){
        return this.E.contains(edge);
    }

    public boolean nodeExists(int item, Node<Integer> node){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item)
                if(subset.getValue().contains(node))
                    return true;
        return false;
    }

    public Node<Integer> getNode(int item, int weight){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item){
                for( Node<Integer> node : subset.getValue() )
                    if ( node.getWeight() == weight )
                        return node;
                Node<Integer> newNode = new Node<Integer>(item, weight);
                this.addNode(item, newNode);
                return newNode;
            }
        return null;
    }

    public Node<Integer> getNodeNoCreate(int item, int weight){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item)
                for( Node<Integer> node : subset.getValue() )
                    if ( node.getWeight() == weight )
                        return node;
        return null;
    }

    public Edge<Integer, Float> getEdge(Node<Integer> from, Node<Integer> to){
        for(Edge<Integer, Float> edge : this.E){
            if(edge.getFrom().equals(from) && edge.getTo().equals(to))
                return edge;
        }
        return null;
    }
}
