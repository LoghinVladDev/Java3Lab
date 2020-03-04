package KnapsackItems;

/**
 * Book Class
 *
 * [Compulsory Class]
 *
 * @author Loghin Vlad
 */
public class Book implements Item {
    String name;
    int weight;
    float value;

    /**
     * Constructor
     * @param name String containing book name
     * @param pageCount book page count
     * @param value value of the book
     */
    public Book(String name, int pageCount, float value){
        this.name = name;
        this.value = value;
        this.weight = (pageCount)/100;
    }

    /**
     * Computes item's profit factor
     * @return profit factor
     */
    public float getProfitFactor(){
        return this.value/this.weight;
    }

    /**
     * Getter for book's name String
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for book's value
     * @return value
     */
    public float getValue(){
        return this.value;
    }

    /**
     * Getter for book's weight
     * @return weight
     */
    public int getWeight(){
        return this.weight;
    }

    /**
     * [DISABLED FOR RANDOM SOLUTION]
     * Overridden equals method
     * @param obj compared to object
     * @return true if objects are equal, false otherwise
     */
    public boolean equals(Item obj){
        return false;
    }

    /**
     * Overridden toString method
     * @return String interpreting the book object
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
