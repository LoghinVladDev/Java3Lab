package ProblemObjects;

import KnapsackItems.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Knapsack Class
 *
 * [COMPULSORY]
 *
 * @author Loghin Vlad
 */
public class Knapsack{
    private int capacity;
    private Item[] items;
    private int itemsCount;

    /**
     * Default Constructor
     */
    public Knapsack(){
        this.capacity = 0;
        this.items = null;
    }

    /**
     * Constructor
     * @param capacity given knapsack capacity
     */
    public Knapsack(int capacity){
        this.capacity = capacity;
        this.items = null;
    }

    /**
     * Method called to clear the contents of the knapsack
     */
    public void clearKnapsack(){
        this.items = null;
        this.itemsCount = 0;
    }

    /**
     * Getter for knapsack's current capacity
     * @return capacity
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * Setter for knapsack's capacity
     * @param capacity value to be set
     */
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    /**
     * Internal method used to allocate memory to items array
     * @param requiredSize required size
     */
    private void enlargeItemsArray(int requiredSize){
        if(this.items == null)
            this.items = new Item[requiredSize];
        else
            this.items = Arrays.copyOf(this.items, Math.max(2*this.items.length, requiredSize + this.itemsCount));
    }

    /**
     * Internal method used to de-allocate extra memory to items array
     */
    private void settleItemsArray(){
        this.items = Arrays.copyOf(this.items, this.itemsCount);
    }

    /**
     * Method called to add an item to the knapsack
     * @param item Pointer of the Item to be added
     */
    public void addItem(Item item){
        if(this.items == null)
            this.enlargeItemsArray(4);
        else if(this.itemsCount + 1 > this.items.length)
            this.enlargeItemsArray(1);

        this.items[this.itemsCount++] = item;
    }

    Comparator<Item>  itemComparator = Comparator.comparing(Item::getName);

    private ArrayList<Item> getListOfItems(){
        ArrayList<Item> result = new ArrayList<>(Arrays.asList(this.items));
        result.sort(itemComparator);
        return result;
    }

    /**
     * Overridden toString method
     * @return String interpretation of the object
     */
    public String toString(){
        String result = "capacity of the knapsack = " + this.capacity;
        if(this.items == null)
            return result
                + "\nno items in knapsack";

        result = result + "\nitems in knapsack :\n";

        this.settleItemsArray();
        ArrayList<Item> sortedItems = this.getListOfItems();
        for(Item item : sortedItems)
            result = result + "\t" + item.toString() + "\n";

        return result;
    }
}
