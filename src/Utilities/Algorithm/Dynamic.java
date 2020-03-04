package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;

import java.util.Arrays;

/**
 * Dynamic Class, implementation of Algorithm Interface
 *
 * [OPTIONAL]
 *
 * @author Loghin Vlad
 */
public class Dynamic implements Algorithm {
    private long runtime;
    private Item[] items;
    private Knapsack knapsack;
    private ItemSet[] itemSets;

    /**
     * Main Dynamic Solution, building a line of the table based on current weight and weight - weightOfItem
     */
    private void buildTable(){
        for(int item = 0; item < this.items.length; item++){
            for(int capacity = this.knapsack.getCapacity(); capacity >= 1; capacity--){
                float valueWithoutCurrentItem = this.itemSets[capacity].getValue();
                float valueWithCurrentItem = 0;

                if(capacity >= this.items[item].getWeight())
                    valueWithCurrentItem = this.items[item].getValue()
                            + this.itemSets[capacity - this.items[item].getWeight()].getValue();


                if(valueWithCurrentItem > valueWithoutCurrentItem){
                    this.itemSets[capacity].setValue(valueWithCurrentItem);
                    this.itemSets[capacity].setItems(this.itemSets[capacity - this.items[item].getWeight()].getItems());
                    this.itemSets[capacity].getItems()[item] = this.items[item];
                }
                else{
                    this.itemSets[capacity].setValue(valueWithoutCurrentItem);
                    this.itemSets[capacity].getItems()[item] = null;
                }
            }
        }
    }

    /**
     * Internal test method, used to check items table
     */
    private void printTable(){
        for(int capacity = 0; capacity <= this.knapsack.getCapacity(); capacity++) {
            System.out.print(this.itemSets[capacity].getValue() + " " + capacity);
            System.out.println(" items inside : " + Arrays.toString(this.itemSets[capacity].getItems()));
        }
    }

    /**
     * Constructor
     * @param knapsack Pointer to the available knapsack
     * @param items Pointer to the items array available
     */
    public Dynamic(Knapsack knapsack, Item[] items){
        runtime = System.nanoTime();
        this.knapsack = knapsack;
        this.items = items;
        this.itemSets = new ItemSet[this.knapsack.getCapacity() + 1];

        for(int i = 0; i <= this.knapsack.getCapacity(); i++)
            this.itemSets[i] = new ItemSet(this.items.length);

        this.buildTable();

        for(Item item : this.itemSets[this.knapsack.getCapacity()].getItems())
            if(item != null)
                this.knapsack.addItem(item);

        runtime = System.nanoTime() - runtime;
    }

    /**
     * Overridden toString method
     * @return String interpretation of the object
     */
    public String toString(){
        return "\n\nDynamic programming solution : \n"+ this.knapsack.toString() + "\nAlgorithm ran for "
                + ((double)this.runtime / Math.pow(10,9))
                +" seconds";
    }

    /**
     * Overridden getNanoRuntime
     * @return algorithm's runtime in nanoseconds
     */
    public long getNanoRuntime(){
        return this.runtime;
    }

    /**
     * Overridden getRuntime
     * @return algorithm's runtime in seconds
     */
    public double getRuntime(){
        return (double)this.runtime / Math.pow(10,9);
    }
}

/**
 * Private Class ItemSet, used in Dynamic Solution
 * Contains the set of items for the current weight
 *
 * [OPTIONAL]
 *
 * @author Loghin Vlad
 */
class ItemSet{
    private Item[] items;
    private float value;

    /**
     * Constructor
     * @param itemCount number of items available
     */
    public ItemSet(int itemCount){
        this.items = new Item[itemCount];
        this.value = 0;
    }

    /**
     * Getter for the array of items
     * @return pointer to the array
     */
    public Item[] getItems() {
        return this.items;
    }

    /**
     * Getter for value of the set
     * @return value
     */
    public float getValue() {
        return this.value;
    }

    /**
     * Setter for the value of the set
     * @param value to be set
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Setter for the items array
     * @param items Pointer to the array to be copied
     */
    public void setItems(Item[] items) {
        this.items = Arrays.copyOf(items, items.length);
    }
}
