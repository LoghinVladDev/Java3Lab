package Utilities;

import KnapsackItems.Book;
import KnapsackItems.Food;
import KnapsackItems.Item;
import KnapsackItems.Weapon;
import ProblemObjects.Knapsack;

public class ProblemRandomiser {
    private static final int DEFAULT_MAX_ITEMS_COUNT = 1500;
    private static final int DEFAULT_MAX_BOOK_PAGE_COUNT = 1000;
    private static final int DEFAULT_MAX_NAME_LENGTH = 70;
    private static final int DEFAULT_MAX_ITEM_WEIGHT = 10;
    private static final float DEFAULT_MAX_ITEM_VALUE = 50;
    private static final int DEFAULT_MAX_KNAPSACK_CAPACITY = 50000;

    private int maxItemsCount;
    private int maxNameLength;
    private int maxBookPageCount;
    private int maxItemWeight;
    private float maxItemValue;
    private int maxKnapsackCapacity;

    private static String generateRandomName(int maxNameLength){
        char[] name = new char[(int)(Math.random()*maxNameLength)];

        for(int i = 0; i < name.length; i++){
            int letterCase = (int)(Math.random() * 2);
            name[i] = (char)((int)(Math.random() * ('z' - 'a' + 1)) + (letterCase==0 ? 'a' : 'A'));
        }

        return new String(name);
    }

    private Book getRandomBook(){
        return new Book(
                generateRandomName(this.maxNameLength),
                (int)(Math.random()*this.maxBookPageCount),
                (float)(Math.random()*this.maxItemValue)
        );
    }

    private Food getRandomFood(){
        int weight = (int)(Math.random() * this.maxItemWeight);
        return new Food(
                generateRandomName(this.maxNameLength),
                weight,
                weight*2
        );
    }

    private Weapon getRandomWeapon(){
        return new Weapon(
                Weapon.WeaponTypes.values()[(int)(Math.random() * Weapon.WeaponTypes.values().length)]
        );
    }

    private Item getRandomItem(){
        int whichItem = (int)(Math.random() * 3);
        switch (whichItem){
            case 0: return getRandomBook();
            case 1: return getRandomFood();
            case 2: return getRandomWeapon();
            default: return null;
        }
    }

    public Problem generateProblem(){
        Problem result = new Problem();
        result.addKnapsack(new Knapsack((int)(Math.random() * this.maxKnapsackCapacity)));

        int itemsCount = (int)(Math.random() * this.maxItemsCount);

        while(itemsCount-- > 0){
            result.addItems(getRandomItem());
        }
        return result;
    }

    public void setMaxBookPageCount(int maxBookPageCount) {
        this.maxBookPageCount = maxBookPageCount;
    }

    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    public void setMaxItemsCount(int maxItemsCount) {
        this.maxItemsCount = maxItemsCount;
    }

    public void setMaxItemValue(float maxItemValue) {
        this.maxItemValue = maxItemValue;
    }

    public void setMaxItemWeight(int maxItemWeight) {
        this.maxItemWeight = maxItemWeight;
    }

    public void setMaxKnapsackCapacity(int maxKnapsackCapacity) {
        this.maxKnapsackCapacity = maxKnapsackCapacity;
    }

    public ProblemRandomiser(){
        this.maxBookPageCount = DEFAULT_MAX_BOOK_PAGE_COUNT;
        this.maxItemsCount = DEFAULT_MAX_ITEMS_COUNT;
        this.maxItemValue = DEFAULT_MAX_ITEM_VALUE;
        this.maxItemWeight = DEFAULT_MAX_ITEM_WEIGHT;
        this.maxKnapsackCapacity = DEFAULT_MAX_KNAPSACK_CAPACITY;
        this.maxNameLength = DEFAULT_MAX_NAME_LENGTH;
    }

    public ProblemRandomiser(int maxItemsCount,
                             int maxBookPageCount,
                             int maxItemWeight,
                             float maxItemValue,
                             int maxKnapsackCapacity){
        this.maxKnapsackCapacity = maxKnapsackCapacity;
        this.maxItemWeight = maxItemWeight;
        this.maxItemValue = maxItemValue;
        this.maxItemsCount = maxItemsCount;
        this.maxBookPageCount = maxBookPageCount;
    }

    public ProblemRandomiser(int maxItemsCount,
                             int maxItemWeight,
                             float maxItemValue,
                             int maxKnapsackCapacity){
        this.maxKnapsackCapacity = maxKnapsackCapacity;
        this.maxItemsCount = maxItemsCount;
        this.maxItemValue = maxItemValue;
        this.maxItemWeight = maxItemWeight;
        this.maxBookPageCount = this.maxItemWeight * 100;
    }
}
