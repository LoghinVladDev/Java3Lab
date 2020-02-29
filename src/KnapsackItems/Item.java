package KnapsackItems;

public interface Item {
    boolean equals(Item obj);
    float getProfitFactor();
    float getValue();
    int getWeight();
    String getName();
    String toString();
}
