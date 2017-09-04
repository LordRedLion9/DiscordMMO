package mmoGame;

import java.util.*;

import mmoServer.ServerMain;

//@SuppressWarnings("unused")
public abstract class Character implements Inspectable, java.io.Serializable {


    private String name = "generic name";
    public String desc = "generic description";
    private String sex;

    private int health;
    private int maxHealth = 20; //Placeholder value, will need discussion

    private int exp = 0;

    private HashMap<String, Integer> attributes = new HashMap<>();
    private HashMap<String, Integer> skills = new HashMap<>();

    Location currentLocation;

    private List<String> inventory = new ArrayList<>(); //String list only temp, will make item objects later

    public Character(String name, String sex) {

        this.name = name;

        this.sex = sex;

        attributes.put("str", 10); //Melee damage
        attributes.put("dex", 10); //To hit
        attributes.put("con", 10); //Hp
        attributes.put("int", 10); //Crit chance

        skills.put("unarmed", 0);
        skills.put("blunt", 0);
        skills.put("stab", 0);
        skills.put("chemical", 0);
        skills.put("slash", 0);
        skills.put("firearm", 0);
        skills.put("improvised", 0);

        skills.put("perception", 0);
        skills.put("charisma", 0);
        skills.put("cooking", 0);
        skills.put("hardware", 0);
        skills.put("software", 0);
        skills.put("diy", 0);
        skills.put("parkour", 0);


    }

    public void setLocation(Location l) {
        currentLocation = l;
    }

    public Location getLocation() {
        return currentLocation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    public void subtractExp(int exp) {
        this.exp -= exp;
    }

    public int getExp() {
        return this.exp;
    }

    public String getSex() {
        return this.sex;
    }

    public String getInfo() {
        String message = "";
        message = "```md\n"
                + "########## Inspecting " + this.getName() + " ##########\n"
                + "\n" + this.desc
                + "```";
        return message;
    }

    public abstract boolean moveCharacter(String locName);


}
