package KnapsackItems;

import javafx.util.Pair;

/**
 * Weapon Class
 *
 * [COMPULSORY]
 *
 * @author Loghin Vlad
 */
public class Weapon implements Item {

    /**
     * Enum WeaponTypes
     *
     * Describes weapon types
     */
    public enum WeaponTypes{
        SWORD,
        DAGGER,
        BOW,
        CROSSBOW,
        AXE,
        MACE
    }

    /**
     * Method used to get a weapon's properties
     * @param weaponType type of the current weapon
     * @return Pair containing Weight and Value
     * @throws RuntimeException if given bad enum value
     */
    public static Pair<Integer, Float> getWeaponProperties(Weapon.WeaponTypes weaponType) throws RuntimeException{
        switch(weaponType){
            case AXE: return new Pair<>(7, 6f);
            case BOW: return new Pair<>(2, 20f);
            case MACE: return new Pair<>(8, 15f);
            case SWORD: return new Pair<>(5, 10f);
            case DAGGER: return new Pair<>(1, 5f);
            case CROSSBOW: return new Pair<>(3, 25f);
            default: throw new RuntimeException("Unknown weapon type");
        }
    }

    private WeaponTypes weaponType;
    private int weight;
    private float value;

    /**
     * Constructor
     * @param weaponType type of constructed weapon
     */
    public Weapon(Weapon.WeaponTypes weaponType){
        this.weaponType = weaponType;

        Pair<Integer, Float> weaponProperties = getWeaponProperties(this.weaponType);

        this.weight = weaponProperties.getKey();
        this.value = weaponProperties.getValue();
    }

    /**
     * Constructor
     * Custom Weapon
     * @param weaponType type of selected weapon ( name )
     * @param weight weight of the weapon
     * @param value value of the weapon
     */
    public Weapon(Weapon.WeaponTypes weaponType, int weight, float value){
        this.weaponType = weaponType;
        this.weight = weight;
        this.value = value;
    }

    /**
     * Getter for item's name
     * @return String containing weapon name
     * @throws RuntimeException if given bad enum at construction
     */
    public String getName() throws RuntimeException{
        switch(weaponType){
            case AXE: return "Axe";
            case SWORD: return "Sword";
            case DAGGER: return "Dagger";
            case BOW: return "Bow";
            case CROSSBOW: return "Crossbow";
            case MACE: return "Mace";
            default : throw new RuntimeException("Unknown weapon type");
        }
    }

    /**
     * Getter for profit factor
     * @return profit factor
     */
    public float getProfitFactor(){
        return this.value/this.weight;
    }

    /**
     * Getter for Value
     * @return value
     */
    public float getValue(){
        return this.value;
    }

    /**
     * Getter for Weight
     * @return weight
     */
    public int getWeight(){
        return this.weight;
    }

    /**
     * [DISABLED FOR RANDOM SOLUTIONS]
     * Overridden equals method
     * @param obj to be compared to caller
     * @return true if objects are equal, false otherwise
     */
    public boolean equals(Item obj){
        return false;
    }

    /**
     * Overridden toString method
     * @return String interpretation of object
     */
    public String toString(){
        return this.getName()
                + ", weight = "
                + this.weight
                + ", value = "
                + this.value
                + ", profit factor "
                + this.getProfitFactor();
    }
}
