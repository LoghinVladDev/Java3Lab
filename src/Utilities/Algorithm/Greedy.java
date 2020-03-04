package Utilities.Algorithm;

import KnapsackItems.Item;
import ProblemObjects.Knapsack;

/**
 * Greedy Class, implementation of Algorithm Interface
 *
 * [OPTIONAL]
 *
 * @author Loghin Vlad
 */
public class Greedy implements Algorithm {
    private long runtime;
    private Knapsack knapsack;
    private Item[] availableItems;

    /**
     * Method sorting items based on profit factor
     */
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

    /**
     * Internal method used to call algorithm's main runtime
     */
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

    /**
     * Constructor
     * @param knapsack pointer to the knapsack given
     * @param items pointer to the item pool
     */
    public Greedy(Knapsack knapsack, Item[] items) {
        runtime = System.nanoTime();
        this.knapsack = knapsack;
        this.availableItems = items;

        this.assignToKnapsack();
        runtime = System.nanoTime() - runtime;
    }

    /**
     * Overridden toString method
     * @return String interpretation of the object
     */
    public String toString(){
        return "\n\nGreedy solution : \n"  + this.knapsack.toString() + "\nAlgorithm ran for "
                + ((double)this.runtime / Math.pow(10,9))
                +" seconds";
    }

    /**
     * Overridden getNanoRuntime
     * @return Algorithm's runtime in nanoseconds
     */
    public long getNanoRuntime(){
        return this.runtime;
    }

    /**
     * Overridden getRuntime
     * @return Algorithm's runtime in seconds
     */
    public double getRuntime(){
        return (double)this.runtime / Math.pow(10,9);
    }
}
