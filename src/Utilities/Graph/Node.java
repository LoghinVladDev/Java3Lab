package Utilities.Graph;

import java.util.Objects;

public class Node <T> {
    private T weight;
    private int itemIndex;

    public Node(int itemIndex, T weight){
        this.weight = weight;
        this.itemIndex = itemIndex;
    }

    public T getWeight(){
        return this.weight;
    }

    public int getItemIndex() { return this.itemIndex; }

    public boolean equals(Node<T> node){
        return this.weight.equals(node.weight) && this.itemIndex == node.itemIndex;
    }

    public String toString(){
        return "(Node of item " + this.itemIndex + " and weight " + this.weight + ")";
    }
}
