package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;
import Utilities.Graph.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;

public class ShortestPath implements Algorithm{
    private Graph G;
    private Knapsack knapsack;
    private Item[] items;
    private long nanoStartTime;
    private long nanoEndTime;
    private boolean[] solution;

    public ShortestPath(){
        G = null;
        this.knapsack = null;
        this.items = null;
    }

    public ShortestPath(Knapsack knapsack, Item[] items){
        this.knapsack = knapsack;
        this.items = items;

        this.generateGraph();
        Dijkstra d = new Dijkstra(this.G, this.knapsack, this.items);

        this.solution = d.getSolution();

        this.nanoEndTime = System.nanoTime();
    }

    public Graph getGraph() throws RuntimeException{
        if(this.G == null)
            throw new RuntimeException("No Graph Generated");
        return this.G;
    }

    private void generateGraph(){
        this.nanoStartTime = System.nanoTime();

        this.G = new Graph(this.items, this.knapsack);

        this.generateGraphEdges();
    }

    public void setItems(Item[] items){
        this.items = items;

        if(this.knapsack != null){
            this.generateGraph();
        }
    }

    public void setKnapsack(Knapsack knapsack){
        this.knapsack = knapsack;

        if(this.items != null){
            this.generateGraph();
        }
    }

    private void addEdgeSetForItemI(int item, Node<Integer> from){
        this.G.getAdjacencyMap().computeIfAbsent(from, k->new ArrayList<>());

        if(from.getWeight() == -1) {
            Node<Integer> nextItemNode = this.G.getNode(item, 0);
            this.G.addEdge(new Edge<Integer, Float>(from, nextItemNode, 0f));
            this.G.getAdjacencyMap().get(from).add(nextItemNode);

            nextItemNode = this.G.getNode(item, this.items[item-1].getWeight());
            this.G.addEdge(new Edge<Integer, Float>(from, nextItemNode, this.items[item-1].getValue() ) );
            this.G.getAdjacencyMap().get(from).add(nextItemNode);
        }
        else{
            Node<Integer> nextItemNode = this.G.getNode(item, from.getWeight());
            this.G.addEdge(new Edge<Integer, Float>(from, nextItemNode, 0f));
            this.G.getAdjacencyMap().get(from).add(nextItemNode);

            if( from.getWeight() + this.items[item-1].getWeight() <= this.knapsack.getCapacity() ){
                nextItemNode = this.G.getNode(item, from.getWeight() + this.items[item-1].getWeight());
                this.G.addEdge(new Edge<Integer, Float>(from, nextItemNode, this.items[item-1].getValue()));
                this.G.getAdjacencyMap().get(from).add(nextItemNode);
            }
        }
    }

    private void generateGraphEdges(){
        for( Pair<Integer, SortedSet<Node<Integer>>> subset : this.G.getV()){
            if(subset.getKey() == 0){
                for( Node<Integer> node : subset.getValue() ){
                    this.addEdgeSetForItemI(1, node);
                }
            }
            else if(subset.getKey() < this.items.length){
                for( Node<Integer> node : subset.getValue() ){
                    this.addEdgeSetForItemI(subset.getKey() + 1, node);
                }
            }
            else if(subset.getKey() == this.items.length){
                for( Node<Integer> node : subset.getValue() ){
                    this.G.addEdge(new Edge<Integer,Float> (node, this.G.getNode(6, Graph.NO_WEIGHT), 0f));
                    this.G.getAdjacencyMap().computeIfAbsent(node, k->new ArrayList<>());
                    this.G.getAdjacencyMap().get(node).add(this.G.getNode(6, Graph.NO_WEIGHT));
                }
            }
        }
        this.G.getAdjacencyMap().computeIfAbsent(this.G.getNode(6, Graph.NO_WEIGHT), k->new ArrayList<>());

    }

    public void printAdjacencyList(){
        System.out.println("Adjacency list\n");
        for( Pair<Integer, SortedSet<Node<Integer>>> i : this.G.getV())
            for( Node<Integer> j : i.getValue() ) {
                if(j!=null){
                    System.out.print("Col " + i.getKey() + " Line " + j.getWeight() + " neighbours :   ");
                    for (Node<Integer> node : this.G.getAdjacencyMap().get(j)) {
                        if( node!=null)
                            System.out.print(node + " ");
                    }
                    System.out.println("");
                }
            }
    }

    public void printEdges(){
        System.out.println(this.G.getE());
    }

    public double getRuntime() {
        return (double)(this.nanoEndTime - this.nanoStartTime) / (Math.pow(10,9));
    }

    public long getNanoRuntime(){
        return this.nanoEndTime - this.nanoStartTime;
    }

    public String toString(){
        String result = "Cost-Wise Graph Solution :\ncapacity of the knapsack = "
                + this.knapsack.getCapacity()
                + "\nitems in knapsack : \n";
        for(int i = 0; i < this.items.length; i++)
            if(this.solution[i])
                result += "\t" + this.items[i].toString() + "\n";
        return result;
    }
}
