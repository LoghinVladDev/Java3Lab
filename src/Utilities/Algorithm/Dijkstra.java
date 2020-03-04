package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;
import Utilities.Graph.Edge;
import Utilities.Graph.Graph;
import Utilities.Graph.Node;
import javafx.util.Pair;

import javax.print.DocFlavor;
import javax.print.attribute.HashPrintJobAttributeSet;
import java.awt.event.KeyAdapter;
import java.util.*;

/**
 * Dijkstra Class, Implementation of Algorithm Interface
 *
 * Extension required by ShortestPath Algorithm
 *
 * [BONUS]
 *
 * @author Loghin Vlad
 */
public class Dijkstra implements Algorithm{
    private Map<Node<Integer>, Float> costs;
    private Map<Node<Integer>, Node<Integer>> parent;
    private Set<Node<Integer>> settled;
    private Graph G;
    private Knapsack knapsack;
    private Item[] items;
    private Node<Integer> source;
    private boolean[] solution;

    /**
     * Internal method that computes maximum costs for all available nodes
     */
    private void computeDijkstra(){
        Node<Integer> selected = null;
        this.settled.add(source);
        while(true){
            float maximumDistance = Float.MIN_VALUE;
            for( Node<Integer> key : this.costs.keySet() ){
                if(!settled.contains(key) && maximumDistance < this.costs.get(key) ){
                    maximumDistance = this.costs.get(key);
                    selected = key;
                }
            }
            if(maximumDistance != Float.MIN_VALUE){
                settled.add(selected);
                for(Node<Integer> key : this.costs.keySet()){
                    Edge<Integer, Float> edge = this.G.getEdge(selected, key);
                    if(edge != null)
                        if(!settled.contains(key) && this.costs.get(key) < this.costs.get(selected) + edge.getCost()){
                            this.costs.put(key, this.costs.get(selected) + edge.getCost());
                            this.parent.put(key, selected);
                        }
                }
            }
            else break;
        }
    }

    /**
     * Internal method used to initialize Dijkstra's Algorithm structures
     */
    private void initializeCosts(){
        for(Edge<Integer, Float> edge : this.G.getE()){
            if(edge.getFrom().getWeight() == Graph.NO_WEIGHT && edge.getFrom().getItemIndex() == 0)
                this.costs.put(edge.getTo(), edge.getCost());
        }

        for(Node<Integer> key : this.costs.keySet()){
            this.parent.put(key, source);
        }

    }

    /**
     * Constructor
     * @param G Pointer to the Graph to be run through Dijkstra's
     * @param knapsack Pointer to the Knapsack available
     * @param items Pointer to the current items
     */
    public Dijkstra(Graph G, Knapsack knapsack, Item[] items){
        this.G = G;
        this.knapsack = knapsack;
        this.items = items;
        this.costs = new HashMap<>();
        this.parent = new HashMap<>();
        this.source = this.G.getNode(0, Graph.NO_WEIGHT);
        this.settled = new HashSet<>();

        for(int itemNumber = 0; itemNumber <= this.items.length + 1; itemNumber++)
            for(int weight = 0; weight <= this.knapsack.getCapacity(); weight++){
                Node<Integer> node = this.G.getNodeNoCreate(itemNumber, weight);
                if(node != null && node.getItemIndex() != 0)
                    this.costs.put(node, Float.MIN_VALUE);
            }

        this.initializeCosts();
        this.computeDijkstra();
        this.parseUp(this.getMaxValueNode());

    }

    /**
     * Getter for the algorithm's solution
     * @return pointer to the boolean array containing info whether an item should or shouldn't be taken
     * @throws RuntimeException if solution was not generated
     */
    public boolean[] getSolution() throws RuntimeException{
        if(this.solution == null)
            throw new RuntimeException("No solution generated");
        return this.solution;
    }

    /**
     * Internal method used to compute solution once maximal path has been computed
     * @param node maximal node from which parsing starts
     */
    private void parseUp(Node<Integer> node){
        this.solution = new boolean[this.items.length];
        Node<Integer> parent;
        while(node != null){
            parent = this.parent.get(node);

            if(node.getItemIndex() >= 1 && node.getItemIndex() <= this.items.length && parent.getItemIndex() >= 0 && parent.getItemIndex() <= this.items.length)
                if(!parent.getWeight().equals(node.getWeight()))
                    this.solution[node.getItemIndex()-1] = true;

            node = parent;
        }
    }

    /**
     * Getter for maximal node
     * @return Pointer to the node whose back-traced route is maximal
     */
    public Node<Integer> getMaxValueNode(){
        float maxValue = 0;
        Node<Integer> maxValueNode = null;

        for(Node<Integer> key : this.costs.keySet())
            if(key.getItemIndex() == 5)
                if (maxValue < this.costs.get(key)) {
                    maxValue = this.costs.get(key);
                    maxValueNode = key;
                }
        return maxValueNode;
    }

    @Override
    public double getRuntime() {
        return 0;
    }

    @Override
    public String toString() {
        return "nothing to see here";
    }

    @Override
    public long getNanoRuntime() {
        return 0;
    }
}
