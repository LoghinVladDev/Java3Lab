package Utilities;

import KnapsackItems.*;
import ProblemObjects.*;

import java.util.Arrays;

/**
 * Problem Class
 *
 * [COMPULSORY]
 *
 * @author Loghin Vlad
 */
public class Problem {
    private Knapsack knapsack;
    private Item[] items;
    private int itemCount;

    /**
     * Default Constructor
     */
    public Problem(){
        this.items = null;
        this.itemCount = 0;
    }

    /**
     * Internal method called to free up extra space in items array
     */
    private void settleItemsArray(){
        this.items = Arrays.copyOf(this.items, this.itemCount);
    }

    /**
     * Internal method called to add extra space in items array
     * @param requiredSize required size
     */
    private void enlargeItemsArray(int requiredSize){
        if(this.items == null)
            this.items = new Item[requiredSize];
        else
            this.items = Arrays.copyOf(this.items, Math.max(2 * this.items.length,this.itemCount + requiredSize));
    }

    /**
     * Method used to add a set of items to the item pool of the problem
     * @param items set of items to be added
     * @throws RuntimeException if items duplicate [UNUSED IN RANDOM SOLUTIONS]
     */
    public void addItems(Item ... items) throws RuntimeException {
        this.enlargeItemsArray(items.length);

        for (Item item : items) {
            if (this.itemExists(item))
                throw new RuntimeException("Item duplicate");

            this.items[this.itemCount++] = item;
        }

        this.settleItemsArray();
    }

    /**
     * Setter for the knapsack
     * @param knapsack pointer to the knapsack available
     */
    public void addKnapsack(Knapsack knapsack){
        this.knapsack = knapsack;
    }

    /**
     * Boolean method called to check an item's existence in the item pool
     * @param item the item in question
     * @return true if item exists, false otherwise
     */
    public boolean itemExists(Item item){
        for(Item itemInArray : this.items)
            if(itemInArray != null)
                if(itemInArray.equals(item))
                    return true;
        return false;
    }

    /**
     * Overridden toString method
     * @return String interpretation of the object
     * @throws RuntimeException if no knapsack is assigned to the problem
     */
    public String toString() throws RuntimeException{
        if(this.knapsack == null)
            throw new RuntimeException("No Knapsack assigned to problem");

        String result = this.knapsack.toString();

        if(this.items == null)
            return result + "\nno items available";

        result = result + "\nitems available :\n";

        for(Item item : this.items)
            result = result + "\t" + item.toString() + "\n";

        return result;
    }

    /**
     * Getter for items array
     * @return pointer to the items array
     */
    public Item[] getItems() {
        return this.items;
    }

    /**
     * Getter for the knapsack object
     * @return pointer to the knapsack object
     */
    public Knapsack getKnapsack() {
        return this.knapsack;
    }
}
