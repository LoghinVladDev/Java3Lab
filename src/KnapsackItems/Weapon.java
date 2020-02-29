package KnapsackItems;

import javafx.util.Pair;

public class Weapon implements Item {
    public enum WeaponTypes{
        SWORD,
        DAGGER,
        BOW,
        CROSSBOW,
        AXE,
        MACE
    }

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

    public Weapon(Weapon.WeaponTypes weaponType){
        this.weaponType = weaponType;

        Pair<Integer, Float> weaponProperties = getWeaponProperties(this.weaponType);

        this.weight = weaponProperties.getKey();
        this.value = weaponProperties.getValue();
    }

    public Weapon(Weapon.WeaponTypes weaponType, int weight, float value){
        this.weaponType = weaponType;
        this.weight = weight;
        this.value = value;
    }

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
        return this.getName()
                + ", weight = "
                + this.weight
                + ", value = "
                + this.value
                + ", profit factor "
                + this.getProfitFactor();
    }
}
