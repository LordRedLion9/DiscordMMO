package mmoGame;

import java.util.*;

public class Location {

	List<Character> chars = new ArrayList<Character>();
	List<Location> exits = new ArrayList<Location>(); // may change this to map
    List<Item> items = new ArrayList<Item>();

	String description = "This area is an empty white expanse stretching for endless miles and miles";

	public void addChar(Character c){
		chars.add(c);
	}

	public void removeChar(Character c){
		chars.remove(c);
	}
	
	public void addExit(Location l){
		exits.add(l);
	}

	public void addItem(Item i){items.add(i);}

	public List<Item> getAllItems(){
	    return items;
    }
	
	public void setDesc(String desc){
		description = desc;
	}

	public String getDescription() {return description;}
	
}
