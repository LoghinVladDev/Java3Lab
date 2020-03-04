package Utilities.Graph;

public class Edge <T,U> {
    private Node<T> from;
    private Node<T> to;
    private U cost;

    public Edge(Node<T> from, Node<T> to, U cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public U getCost(){
        return this.cost;
    }

    public void setCost(U cost){
        this.cost = cost;
    }

    public Node<T> getFrom(){
        return this.from;
    }

    public Node<T> getTo(){
        return this.to;
    }

    public void setFrom(Node<T> from){
        this.from = from;
    }

    public void setTo(Node<T> to){
        this.to = to;
    }

    public boolean equals (Edge<T,U> edge){
        return this.from.equals(edge.from) &&
               this.to.equals(edge.to) &&
               this.cost == edge.cost;
    }

    public String toString(){
        return "(Edge from " + this.from + " to " + this.to + " with cost " + this.cost + ")";
    }
}
