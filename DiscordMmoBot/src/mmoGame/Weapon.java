package mmoGame;

public class Weapon extends Item{

    int damage;
    String weaponType; //TODO: switch to enum later (Put it in Constants.java)

    public Weapon(String name, int damage, String type){
        super(name);

        this.damage = damage;
        weaponType = type;

    }
}
