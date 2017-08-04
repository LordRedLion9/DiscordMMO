package mmoGame;

import java.util.*;

//@SuppressWarnings("unused")
public abstract class Character {

	
	private String name;
	public String desc;
	
	private int health;
        private int maxHealth = 20; //Placeholder value, will need discussion
	
	Location currentLocation;
	
	private List<String> inventory = new ArrayList<String>(); //String list only temp, will make item objects later


	public void setLocation(Location l){
		currentLocation = l;
	}
	
	public Location getLocation(){
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
	
	
	
	
}
