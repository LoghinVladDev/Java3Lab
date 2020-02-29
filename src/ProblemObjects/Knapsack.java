package ProblemObjects;

import KnapsackItems.Item;

import java.util.Arrays;

public class Knapsack{
    private int capacity;
    private Item[] items;
    private int itemsCount;

    public Knapsack(){
        this.capacity = 0;
        this.items = null;
    }

    public Knapsack(int capacity){
        this.capacity = capacity;
        this.items = null;
    }

    public void clearKnapsack(){
        this.items = null;
        this.itemsCount = 0;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    private void enlargeItemsArray(int requiredSize){
        if(this.items == null)
            this.items = new Item[requiredSize];
        else
            this.items = Arrays.copyOf(this.items, Math.max(2*this.items.length, requiredSize + this.itemsCount));
    }

    private void settleItemsArray(){
        this.items = Arrays.copyOf(this.items, this.itemsCount);
    }

    public void addItem(Item item){
        if(this.items == null)
            this.enlargeItemsArray(4);
        else if(this.itemsCount + 1 > this.items.length)
            this.enlargeItemsArray(1);

        this.items[this.itemsCount++] = item;
    }

    public String toString(){
        String result = "capacity of the knapsack = " + this.capacity;

        if(this.items == null)
            return result
                + "\nno items in knapsack";

        result = result + "\nitems in knapsack :\n";

        this.settleItemsArray();
        for(Item item : this.items){
            result = result + "\t" + item.toString() + "\n";
        }

        return result;
    }
}
