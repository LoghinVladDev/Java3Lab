package Utilities.Graph;

import java.util.Objects;

/**
 * Class Node, Used in bonus solution
 *
 * [BONUS]
 *
 * @param <T> Type of value node will contain
 * @author Loghin Vlad
 */
public class Node <T> {
    private T weight;
    private int itemIndex;

    /**
     * Constructor
     * @param itemIndex to which set the node belongs to
     * @param weight what value does the node contain
     */
    public Node(int itemIndex, T weight){
        this.weight = weight;
        this.itemIndex = itemIndex;
    }

    /**
     * Getter for node's weight
     * @return weight
     */
    public T getWeight(){
        return this.weight;
    }

    /**
     * Getter for the set the node belongs to, or the item it represents
     * @return itemIndex
     */
    public int getItemIndex() { return this.itemIndex; }

    /**
     * Overridden equals method
     * @param node object to which the caller is compared
     * @return true if objects are equal, false otherwise
     */
    public boolean equals(Node<T> node){
        return this.weight.equals(node.weight) && this.itemIndex == node.itemIndex;
    }

    /**
     * Overridden toString method
     * @return String interpretation of the current object
     */
    public String toString(){
        return "(Node of item " + this.itemIndex + " and weight " + this.weight + ")";
    }
}
