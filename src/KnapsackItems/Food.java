package KnapsackItems;

public class Food implements Item {
    private String name;
    private int weight;
    private float value;

    public Food(String name, int weight){
        this.name = name;
        this.weight = weight;
        this.value = this.weight * 2;
    }

    public Food(String name, int weight, float value) throws RuntimeException {
        this.name = name;
        this.value = value;
        this.weight = weight;

        if(this.weight * 2 != this.value)
            throw new RuntimeException("Food invalid, value is not 2 * weight");
    }

    public String getName(){
        return this.name;
    }

    public float getProfitFactor(){
        return this.value/this.weight;
    }

    public float getValue(){
        return this.value;
    }

    public int getWeight(){
        return this.weight;
    }

    public boolean equals(Item obj){
        return false;
    }

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
