package mmoGame;

public class Weapon extends Item{

    int damage;
    String weaponType; //TODO: switch to enum later

    public Weapon(String name, int initDamage, String type){
        super(name);

        damage = initDamage;
        weaponType = type;

    }
}
