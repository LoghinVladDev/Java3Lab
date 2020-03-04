package Utilities.Graph;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;
import javafx.util.Pair;

import javax.swing.tree.TreeNode;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
    private static final int NO_WEIGHT = -1;
    private Set<Pair<Integer, Set<Node<Integer>>>>V;
    private Set<Edge<Integer>> E;

    private int itemCount;
    private int knapsackCapacity;
    private Item[] items;
    private Knapsack knapsack;

    private void initialiseSets(int itemCount){
        for( int subsetIndex = 0; subsetIndex <= itemCount + 1; subsetIndex++ ){
            this.V.add(new Pair<>(subsetIndex, new HashSet<>()));
        }

        for (Pair<Integer, Set<Node<Integer>>> subset : this.V) {
            if (subset.getKey() == 0)
                subset.getValue().add(new Node<Integer>(NO_WEIGHT));
            if (subset.getKey() == itemCount + 1)
                subset.getValue().add(new Node<Integer>(NO_WEIGHT));
        }

        this.itemCount = itemCount;
    }

    public Graph(int itemCount){
        V = new HashSet<>();
        E = new HashSet<>();

        this.initialiseSets(itemCount);
    }

    public Graph(Item[] items, Knapsack knapsack){
        this.initialiseSets(items.length);
    }

    public void addNode(int item, int weight) throws RuntimeException{
        for( Pair<Integer, Set<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item) {
                subset.getValue().add(new Node<Integer>(weight));
                return;
            }
        throw new RuntimeException("No such V^i set");
    }

    public void addNode( int item, Node<Integer> node ) throws RuntimeException{
        for( Pair<Integer, Set<Node<Integer>>> subset : this.V )
            if(subset.getKey() == item) {
                subset.getValue().add(node);
                return;
            }
        throw new RuntimeException("No such V^i set");
    }
}
