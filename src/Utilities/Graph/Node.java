package Utilities.Graph;

import java.util.Objects;

public class Node <T> {
    private T weight;

    public Node(T weight){
        this.weight = weight;
    }

    public T getWeight(){
        return this.weight;
    }
}
