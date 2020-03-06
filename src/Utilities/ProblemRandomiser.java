package Utilities;

import KnapsackItems.Book;
import KnapsackItems.Food;
import KnapsackItems.Item;
import KnapsackItems.Weapon;
import ProblemObjects.Knapsack;

/**
 * Class ProblemRandomiser
 * Generates a random Problem object
 *
 * [OPTIONAL]
 *
 * @author Loghin Vlad
 */
public class ProblemRandomiser {
    private static final int DEFAULT_MAX_ITEMS_COUNT = 100;
    private static final int DEFAULT_MAX_BOOK_PAGE_COUNT = 1000;
    private static final int DEFAULT_MAX_NAME_LENGTH = 70;
    private static final int DEFAULT_MAX_ITEM_WEIGHT = 10;
    private static final float DEFAULT_MAX_ITEM_VALUE = 50;
    private static final int DEFAULT_MAX_KNAPSACK_CAPACITY = 1000;

    private int maxItemsCount;
    private int maxNameLength;
    private int maxBookPageCount;
    private int maxItemWeight;
    private float maxItemValue;
    private int maxKnapsackCapacity;

    /**
     * Method called to generate a random object name
     * @param maxNameLength maximum name length
     * @return String containing generated name
     */
    private static String generateRandomName(int maxNameLength){
        char[] name = new char[(int)(Math.random()*maxNameLength)];

        for(int i = 0; i < name.length; i++){
            int letterCase = (int)(Math.random() * 2);
            name[i] = (char)((int)(Math.random() * ('z' - 'a' + 1)) + (letterCase==0 ? 'a' : 'A'));
        }

        return new String(name);
    }

    /**
     * Getter for a random book
     * @return pointer to the new item
     */
    private Book getRandomBook(){
        return new Book(
                generateRandomName(this.maxNameLength),
                (int)(Math.random()*this.maxBookPageCount) + 100,
                (float)(Math.random()*this.maxItemValue)
        );
    }

    /**
     * Getter for a random food item
     * @return pointer to the new item
     */
    private Food getRandomFood(){
        int weight = (int)(Math.random() * this.maxItemWeight) + 1;
        return new Food(
                generateRandomName(this.maxNameLength),
                weight,
                weight*2
        );
    }

    /**
     * Getter for a random weapon
     * @return pointer to the new item
     */
    private Weapon getRandomWeapon(){
        return new Weapon(
                Weapon.WeaponTypes.values()[(int)(Math.random() * Weapon.WeaponTypes.values().length)]
        );
    }

    /**
     * Getter for a random item, selects from three getters at random
     * @return pointer to the new item
     */
    private Item getRandomItem(){
        int whichItem = (int)(Math.random() * 3);
        switch (whichItem){
            case 0: return getRandomBook();
            case 1: return getRandomFood();
            case 2: return getRandomWeapon();
            default: return null;
        }
    }

    /**
     * Method called to return a newly generated problem
     * @return pointer to the problem object
     */
    public Problem generateProblem(){
        Problem result = new Problem();
        result.addKnapsack(new Knapsack((int)(Math.random() * this.maxKnapsackCapacity)));

        int itemsCount = (int)(Math.random() * this.maxItemsCount);

        while(itemsCount-- > 0){
            result.addItems(getRandomItem());
        }
        return result;
    }

    /**
     * Setter for the maximum page count
     * @param maxBookPageCount value to be assigned
     */
    public void setMaxBookPageCount(int maxBookPageCount) {
        this.maxBookPageCount = maxBookPageCount;
    }

    /**
     * Setter for the maximum name length
     * @param maxNameLength value to be assigned
     */
    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    /**
     * Setter for the maximum items to be generated
     * @param maxItemsCount value to be assigned
     */
    public void setMaxItemsCount(int maxItemsCount) {
        this.maxItemsCount = maxItemsCount;
    }

    /**
     * Setter for the maximum value an item can have
     * @param maxItemValue value to be assigned
     */
    public void setMaxItemValue(float maxItemValue) {
        this.maxItemValue = maxItemValue;
    }

    /**
     * Setter for the maximum weight an item can have
     * @param maxItemWeight value to be assigned
     */
    public void setMaxItemWeight(int maxItemWeight) {
        this.maxItemWeight = maxItemWeight;
    }

    /**
     * Setter for the maximum weight a knapsack can contain
     * @param maxKnapsackCapacity value to be assigned
     */
    public void setMaxKnapsackCapacity(int maxKnapsackCapacity) {
        this.maxKnapsackCapacity = maxKnapsackCapacity;
    }

    /**
     * Constructor
     */
    public ProblemRandomiser(){
        this.maxBookPageCount = DEFAULT_MAX_BOOK_PAGE_COUNT;
        this.maxItemsCount = DEFAULT_MAX_ITEMS_COUNT;
        this.maxItemValue = DEFAULT_MAX_ITEM_VALUE;
        this.maxItemWeight = DEFAULT_MAX_ITEM_WEIGHT;
        this.maxKnapsackCapacity = DEFAULT_MAX_KNAPSACK_CAPACITY;
        this.maxNameLength = DEFAULT_MAX_NAME_LENGTH;
    }

    /**
     * Constructor
     * @param maxItemsCount given maximum items count
     * @param maxBookPageCount given maximum page count
     * @param maxItemWeight given maximum weight an item can have
     * @param maxItemValue given maximum value an item can have
     * @param maxKnapsackCapacity given maximum capacity a knapsack can have
     */
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

    /**
     *
     * @param maxItemsCount given maximum items count
     * @param maxItemWeight given maximum weight an item can have
     * @param maxItemValue given maximum value an item can have
     * @param maxKnapsackCapacity given maximum capacity a knapsack can have
     */
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
