package KnapsackItems;

/**
 * Food Class
 *
 * [COMPULSORY]
 *
 * @author loghin Vlad
 */
public class Food implements Item {
    private String name;
    private int weight;
    private float value;

    /**
     * Constructor
     * @param name String containing food name
     * @param weight weight of said food
     */
    public Food(String name, int weight){
        this.name = name;
        this.weight = weight;
        this.value = this.weight * 2;
    }

    /**
     * Constructor
     * @param name String containing food name
     * @param weight weight of said food
     * @param value value of said food
     * @throws RuntimeException if value != 2 * weight
     */
    public Food(String name, int weight, float value) throws RuntimeException {
        this.name = name;
        this.value = value;
        this.weight = weight;

        if(this.weight * 2 != this.value)
            throw new RuntimeException("Food invalid, value is not 2 * weight");
    }

    /**
     * Getter for food's name String
     * @return String containing object's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the item's profit factor
     * @return profit factor
     */
    public float getProfitFactor(){
        return this.value/this.weight;
    }

    /**
     * Getter for the item's value
     * @return value
     */
    public float getValue(){
        return this.value;
    }

    /**
     * Getter for the item's weight
     * @return weight
     */
    public int getWeight(){
        return this.weight;
    }

    /**
     * [DISABLED FOR RANDOM SOLUTION]
     * Overridden equals method
     * @param obj to be compared to caller object
     * @return true if objects are equal, false otherwise
     */
    public boolean equals(Item obj){
        return false;
    }

    /**
     * Overridden toString method
     * @return String interpreting current object
     */
    public String toString(){
        return this.name
                + ", weight = "
                + this.weight
                + ", value = "
                + this.value
                + ", profit factor "
                + this.getProfitFactor();
    }
}
