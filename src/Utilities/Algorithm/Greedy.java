package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;

public class Greedy implements Algorithm {
    private long runtime;
    private Knapsack knapsack;
    private Item[] availableItems;

    private void sortByProfit(){
        for(int i = 0; i < this.availableItems.length - 1; i++)
            for(int j = i + 1; j < this.availableItems.length; j++)
                if(this.availableItems[i].getProfitFactor() > this.availableItems[j].getProfitFactor())
                {
                    Item aux = this.availableItems[i];
                    this.availableItems[i] = this.availableItems[j];
                    this.availableItems[j] = aux;
                }
    }

    private void assignToKnapsack(){
        sortByProfit();
        int weightInKnapsack = 0;

        for(int i = this.availableItems.length - 1; i >= 0; i--){
            if(weightInKnapsack + this.availableItems[i].getWeight() <= this.knapsack.getCapacity()){
                this.knapsack.addItem(this.availableItems[i]);
                weightInKnapsack += this.availableItems[i].getWeight();
            }
        }
    }

    public Greedy(Knapsack knapsack, Item[] items) {
        runtime = System.nanoTime();
        this.knapsack = knapsack;
        this.availableItems = items;

        this.assignToKnapsack();
        runtime = System.nanoTime() - runtime;
    }

    public String toString(){
        return "\n\nGreedy solution : \n"  + this.knapsack.toString() + "\nAlgorithm ran for "
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
