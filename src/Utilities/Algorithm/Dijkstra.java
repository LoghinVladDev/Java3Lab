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

public class Dijkstra implements Algorithm{
    private Map<Node<Integer>, Float> costs;
    private Map<Node<Integer>, Node<Integer>> parent;
    private Set<Node<Integer>> settled;
    private Graph G;
    private Knapsack knapsack;
    private Item[] items;
    private Node<Integer> source;
    private boolean[] solution;

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

    private void initializeCosts(){
        for(Edge<Integer, Float> edge : this.G.getE()){
            if(edge.getFrom().getWeight() == Graph.NO_WEIGHT && edge.getFrom().getItemIndex() == 0)
                this.costs.put(edge.getTo(), edge.getCost());
        }

        for(Node<Integer> key : this.costs.keySet()){
            this.parent.put(key, source);
        }

    }

    public Dijkstra(Graph G, Knapsack knapsack, Item[] items){
        this.G = G;
        this.knapsack = knapsack;
        this.items = items;
        this.costs = new HashMap<>();
        this.parent = new HashMap<>();
        this.source = this.G.getNode(0, Graph.NO_WEIGHT);
        this.settled = new HashSet<>();

        for(int itemNumber = 0; itemNumber <= this.items.length + 1; itemNumber++){
            for(int weight = 0; weight <= this.knapsack.getCapacity(); weight++){
                Node<Integer> node = this.G.getNodeNoCreate(itemNumber, weight);
                if(node != null && node.getItemIndex() != 0)
                    this.costs.put(node, Float.MIN_VALUE);
            }
        }

        this.initializeCosts();
        this.computeDijkstra();
        this.parseUp(this.getMaxValueNode());

    }

    public boolean[] getSolution() throws RuntimeException{
        if(this.solution == null)
            throw new RuntimeException("No solution generated");
        return this.solution;
    }

    private void parseUp(Node<Integer> node){
        this.solution = new boolean[this.items.length];
        Node<Integer> parent = null;
        while(node != null){
            parent = this.parent.get(node);

            if(node.getItemIndex() >= 1 && node.getItemIndex() <= this.items.length && parent.getItemIndex() >= 0 && parent.getItemIndex() <= this.items.length){
                if(parent.getWeight() != node.getWeight())
                    this.solution[node.getItemIndex()-1] = true;
            }

            node = parent;
        }
    }

    public Node<Integer> getMaxValueNode(){
        float maxValue = 0;
        Node<Integer> maxValueNode = null;

        for(Node<Integer> key : this.costs.keySet()){
            if(key.getItemIndex() == 5) {
                if (maxValue < this.costs.get(key)) {
                    maxValue = this.costs.get(key);
                    maxValueNode = key;
                }
            }
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
