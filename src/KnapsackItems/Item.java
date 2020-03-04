package KnapsackItems;

/**
 * Interface Item
 *
 * [COMPULSORY]
 *
 * @author Loghin Vlad
 */
public interface Item {
    boolean equals(Item obj);
    float getProfitFactor();
    float getValue();
    int getWeight();
    String getName();
    String toString();
}
