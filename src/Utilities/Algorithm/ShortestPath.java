package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;
import Utilities.Graph.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;

/**
 * Class ShortestPath, implementation of Algorithm Interface
 *
 * [BONUS]
 *
 * @author Loghin Vlad
 */
public class ShortestPath implements Algorithm{
    private Graph G;
    private Knapsack knapsack;
    private Item[] items;
    private long nanoStartTime;
    private long nanoEndTime;
    private boolean[] solution;

    /**
     * Default Constructor
     */
    public ShortestPath(){
        G = null;
        this.knapsack = null;
        this.items = null;
    }

    /**
     * Constructor
     * @param knapsack pointer to the available knapsack
     * @param items pointer to the item pool
     */
    public ShortestPath(Knapsack knapsack, Item[] items){
        this.knapsack = knapsack;
        this.items = items;

        this.generateGraph();
        Dijkstra d = new Dijkstra(this.G, this.knapsack, this.items);

        this.solution = d.getSolution();

        this.applySolution();

        this.nanoEndTime = System.nanoTime();
    }

    /**
     * Getter for the generated graph
     * @return pointer to the graph object
     * @throws RuntimeException if graph has not been generated yet
     */
    public Graph getGraph() throws RuntimeException{
        if(this.G == null)
            throw new RuntimeException("No Graph Generated");
        return this.G;
    }

    /**
     * Internal method called when all resources required for its generation are available
     */
    private void generateGraph(){
        this.nanoStartTime = System.nanoTime();

        this.G = new Graph(this.items, this.knapsack);

        this.generateGraphEdges();
    }

    /**
     * Setter for items array pointer
     * @param items pointer to the items array
     */
    public void setItems(Item[] items){
        this.items = items;

        if(this.knapsack != null)
            this.generateGraph();
    }

    /**
     * Setter for the knapsack pointer
     * @param knapsack pointer to the knapsack
     */
    public void setKnapsack(Knapsack knapsack){
        this.knapsack = knapsack;

        if(this.items != null)
            this.generateGraph();
    }

    /**
     * Function used to generate Knapsack Problem edges in a graph, optimised to avoid huge memory consumption
     *
     * Optimised in sets, adds an edge and a node if required by problem's specifications
     *
     * @param item number of the item the set represents
     * @param from node from which edges are being generated
     */
    private void addEdgeSetForItemI(int item, Node<Integer> from){
        this.G.getAdjacencyMap().computeIfAbsent(from, k->new ArrayList<>());

        if(from.getWeight() == Graph.NO_WEIGHT) {
            Node<Integer> nextItemNode = this.G.getNode(item, 0);
            this.G.addEdge(new Edge<>(from, nextItemNode, 0f));
            this.G.getAdjacencyMap().get(from).add(nextItemNode);

            nextItemNode = this.G.getNode(item, this.items[item-1].getWeight());
            this.G.addEdge(new Edge<>(from, nextItemNode, this.items[item-1].getValue() ) );
            this.G.getAdjacencyMap().get(from).add(nextItemNode);
        }
        else{
            Node<Integer> nextItemNode = this.G.getNode(item, from.getWeight());
            this.G.addEdge(new Edge<>(from, nextItemNode, 0f));
            this.G.getAdjacencyMap().get(from).add(nextItemNode);

            if( from.getWeight() + this.items[item-1].getWeight() <= this.knapsack.getCapacity() ){
                nextItemNode = this.G.getNode(item, from.getWeight() + this.items[item-1].getWeight());
                this.G.addEdge(new Edge<>(from, nextItemNode, this.items[item-1].getValue()));
                this.G.getAdjacencyMap().get(from).add(nextItemNode);
            }
        }
    }

    /**
     * Internal method called to start computing graph's edges
     */
    private void generateGraphEdges(){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.G.getV())
            if(subset.getKey() == 0)
                for( Node<Integer> node : subset.getValue() )
                    this.addEdgeSetForItemI(1, node);
            else if(subset.getKey() < this.items.length)
                for( Node<Integer> node : subset.getValue() )
                    this.addEdgeSetForItemI(subset.getKey() + 1, node);
            else if(subset.getKey() == this.items.length)
                for( Node<Integer> node : subset.getValue()) {
                    this.G.addEdge(new Edge<> (node, this.G.getNode(6, Graph.NO_WEIGHT), 0f));
                    this.G.getAdjacencyMap().computeIfAbsent(node, k->new ArrayList<>());
                    this.G.getAdjacencyMap().get(node).add(this.G.getNode(6, Graph.NO_WEIGHT));
                }
        this.G.getAdjacencyMap().computeIfAbsent(this.G.getNode(6, Graph.NO_WEIGHT), k->new ArrayList<>());

    }

    /**
     * Printer method used to display adjacency list
     */
    public void printAdjacencyList(){
        System.out.println("Adjacency list\n");
        for( Pair<Integer, SortedSet<Node<Integer>>> i : this.G.getV())
            for( Node<Integer> j : i.getValue() ) {
                if(j!=null){
                    System.out.print("Col " + i.getKey() + " Line " + j.getWeight() + " neighbours :   ");
                    for (Node<Integer> node : this.G.getAdjacencyMap().get(j))
                        if( node!=null)
                            System.out.print(node + " ");
                    System.out.println("");
                }
            }
    }

    /**
     * Printer method used to display edges
     */
    public void printEdges(){
        System.out.println(this.G.getE());
    }

    /**
     * Overridden method getRuntime
     * @return Algorithm's runtime in seconds
     */
    public double getRuntime() {
        return (double)(this.nanoEndTime - this.nanoStartTime) / (Math.pow(10,9));
    }

    /**
     * Overridden method getNanoRuntime
     * @return Algorithm's runtime in nanoseconds
     */
    public long getNanoRuntime(){
        return this.nanoEndTime - this.nanoStartTime;
    }

    private void applySolution(){

        this.knapsack.clearKnapsack();
        for(int i = 0; i < this.items.length; i++)
            if(this.solution[i])
                knapsack.addItem(this.items[i]);
    }

    /**
     * Overridden toString method
     * @return String interpretation of the current object
     */
    public String toString(){
        String result = "Cost-Wise Graph Solution :\n"
                + this.knapsack
                + "\nAlgorithm ran for "
                + this.getRuntime()
                + " seconds";

        return result;
    }
}
