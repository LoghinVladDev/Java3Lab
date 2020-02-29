package KnapsackItems;

public class Book implements Item {
    String name;
    int weight;
    float value;

    public Book(String name, int pageCount, float value){
        this.name = name;
        this.value = value;
        this.weight = (pageCount)/100;
    }

    public float getProfitFactor(){
        return this.value/this.weight;
    }

    public String getName(){
        return this.name;
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
