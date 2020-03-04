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
        Node<Integer> nextItemNode = new Node<>(0);
        this.G.addEdge(new Edge<Integer, Float>(
                from,
                nextItemNode,
                0f
            )
        );
        this.G.addNode(item, nextItemNode);

        nextItemNode = new Node<> (this.items[item].getWeight());
        this.G.addEdge(new Edge<Integer, Float>(
                from,
                nextItemNode,
                this.items[item].getValue()
            )
        );

        this.G.addNode(item, nextItemNode);
    }

    private void generateGraphEdges(){
        for( Pair<Integer, Set<Node<Integer>>> subset : this.G.getV()){
            Node<Integer> nextItemNode;
            switch(subset.getKey()){
                case 0:
                    for( Node<Integer> node : subset.getValue()){
                        this.addEdgeSetForItemI(1, node);
                    }
            }
        }
    }
}
