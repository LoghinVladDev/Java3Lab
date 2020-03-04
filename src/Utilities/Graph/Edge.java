package Utilities.Graph;

/**
 * Class Edge, used in bonus solution
 *
 * [BONUS]
 * @param <T> Node contained value type
 * @param <U> Cost type
 *
 * @author Loghin Vlad
 */
public class Edge <T,U> {
    private Node<T> from;
    private Node<T> to;
    private U cost;

    /**
     * Constructor
     * @param from pointer to the node from which the edge originates
     * @param to pointer to the node in which the edge arrives
     * @param cost cost of the edge
     */
    public Edge(Node<T> from, Node<T> to, U cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    /**
     * Getter for an edge's cost
     * @return cost
     */
    public U getCost(){
        return this.cost;
    }

    /**
     * Setter for an edge's cost
     * @param cost to be assigned
     */
    public void setCost(U cost){
        this.cost = cost;
    }

    /**
     * Getter for the pointer of the node originating the edge
     * @return pointer from which the edge starts
     */
    public Node<T> getFrom(){
        return this.from;
    }

    /**
     * Getter for the pointer of the node to which the edge indicates
     * @return pointer to which the edge arrives
     */
    public Node<T> getTo(){
        return this.to;
    }

    /**
     * Setter for the node starting the edge
     * @param from node to be assigned
     */
    public void setFrom(Node<T> from){
        this.from = from;
    }

    /**
     * Setter for the node ending the edge
     * @param to node to be assigned
     */
    public void setTo(Node<T> to){
        this.to = to;
    }

    /**
     * Overridden equals method
     * @param edge object to be compared to caller
     * @return true if objects are equal, false otherwise
     */
    public boolean equals (Edge<T,U> edge){
        return this.from.equals(edge.from) &&
               this.to.equals(edge.to) &&
               this.cost == edge.cost;
    }

    /**
     * Overridden toString method
     * @return String interpretation of the current object
     */
    public String toString(){
        return "(Edge from " + this.from + " to " + this.to + " with cost " + this.cost + ")";
    }
}
