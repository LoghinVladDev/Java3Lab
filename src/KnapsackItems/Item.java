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
    default float getProfitFactor(){
        return this.getValue() / this.getWeight();
    }
    float getValue();
    int getWeight();
    String getName();
    String toString();
}
