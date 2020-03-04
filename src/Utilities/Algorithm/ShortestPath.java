package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;
import Utilities.Graph.*;
import javafx.util.Pair;

import java.util.Set;

public class ShortestPath {
    private Graph G;
    private Knapsack knapsack;
    private Item[] items;
    private long nanoStartTime;

    public ShortestPath(){
        G = null;
        this.knapsack = null;
        this.items = null;
    }

    public ShortestPath(Item[] items, Knapsack knapsack){
        this.knapsack = knapsack;
        this.items = items;

        this.generateGraph();
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
        Node<Integer> nextItemNode = this.G.getNode(item, 0);
        this.G.addEdge(new Edge<Integer, Float>(
                from,
                nextItemNode,
                0f
            )
        );

        nextItemNode = this.G.getNode(item, this.items[item].getWeight());
        this.G.addEdge(new Edge<Integer, Float>(
                from,
                nextItemNode,
                this.items[item].getValue()
            )
        );
    }

    private void generateGraphEdges(){
        for( Pair<Integer, Set<Node<Integer>>> subset : this.G.getV()){
            if(subset.getKey() == 0){
                for( Node<Integer> node : subset.getValue() ){
                    this.addEdgeSetForItemI(0, node);
                }
            }
            else if(subset.getKey() == this.items.length){
                for( Node<Integer> node : subset.getValue() ){
                    this.addEdgeSetForItemI(subset.getKey(), node);
                }
            }
        }
    }
}
