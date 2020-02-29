package Utilities;

import KnapsackItems.*;
import ProblemObjects.*;

import java.util.Arrays;

public class Problem {
    private Knapsack knapsack;
    private Item[] items;
    private int itemCount;

    public Problem(){
        this.items = null;
        this.itemCount = 0;
    }

    private void settleItemsArray(){
        this.items = Arrays.copyOf(this.items, this.itemCount);
    }

    private void enlargeItemsArray(int requiredSize){
        if(this.items == null)
            this.items = new Item[requiredSize];
        else
            this.items = Arrays.copyOf(this.items, Math.max(2 * this.items.length,this.itemCount + requiredSize));
    }

    public void addItems(Item ... items) throws RuntimeException {
        this.enlargeItemsArray(items.length);

        for (Item item : items) {
            if (this.itemExists(item))
                throw new RuntimeException("Item duplicate");

            this.items[this.itemCount++] = item;
        }

        this.settleItemsArray();
    }

    public void addKnapsack(Knapsack knapsack){
        this.knapsack = knapsack;
    }

    public boolean itemExists(Item item){
        for(Item itemInArray : this.items)
            if(itemInArray != null)
                if(itemInArray.equals(item))
                    return true;
        return false;
    }

    public String toString() throws RuntimeException{
        if(this.knapsack == null)
            throw new RuntimeException("No Knapsack assigned to problem");

        String result = this.knapsack.toString();

        if(this.items == null)
            return result + "\nno items available";

        result = result + "\nitems available :\n";

        for(Item item : this.items){
            result = result + "\t" + item.toString() + "\n";
        }

        return result;
    }

    public Item[] getItems() {
        return this.items;
    }

    public Knapsack getKnapsack() {
        return this.knapsack;
    }
}
