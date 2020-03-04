package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;

import java.util.Arrays;

public class Dynamic implements Algorithm {
    private long runtime;
    private Item[] items;
    private Knapsack knapsack;
    private ItemSet[] itemSets;

    private void buildTable(){
        for(int item = 0; item < this.items.length; item++){
            for(int capacity = this.knapsack.getCapacity(); capacity >= 1; capacity--){
                float valueWithoutCurrentItem = this.itemSets[capacity].getValue();
                float valueWithCurrentItem = 0;

                if(capacity >= this.items[item].getWeight()){
                    valueWithCurrentItem = this.items[item].getValue()
                            + this.itemSets[capacity - this.items[item].getWeight()].getValue();
                }

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

    private void printTable(){
        for(int capacity = 0; capacity <= this.knapsack.getCapacity(); capacity++) {
            System.out.print(this.itemSets[capacity].getValue() + " " + capacity);
            System.out.println(" items inside : " + Arrays.toString(this.itemSets[capacity].getItems()));
        }
    }

    public Dynamic(Knapsack knapsack, Item[] items){
        runtime = System.nanoTime();
        this.knapsack = knapsack;
        this.items = items;
        this.itemSets = new ItemSet[this.knapsack.getCapacity() + 1];

        for(int i = 0; i <= this.knapsack.getCapacity(); i++){
            this.itemSets[i] = new ItemSet(this.items.length);
        }

        this.buildTable();

        //this.printTable();

        for(Item item : this.itemSets[this.knapsack.getCapacity()].getItems()){
            if(item != null)
                this.knapsack.addItem(item);
        }

        runtime = System.nanoTime() - runtime;
    }

    public String toString(){
        return "\n\nDynamic programming solution : \n"+ this.knapsack.toString() + "\nAlgorithm ran for "
                + ((double)this.runtime / Math.pow(10,9))
                +" seconds";
    }

    public long getNanoRuntime(){
        return this.runtime;
    }

    public double getRuntime(){
        return (double)this.runtime / Math.pow(10,9);
    }
}

class ItemSet{
    private Item[] items;
    private float value;

    public ItemSet(int itemCount){
        this.items = new Item[itemCount];
        this.value = 0;
    }

    public Item[] getItems() {
        return this.items;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setItems(Item[] items) {
        this.items = Arrays.copyOf(items, items.length);
    }
}
