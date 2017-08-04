package mmoGame;

import java.util.*;

public class Location {

	List<Character> chars = new ArrayList<Character>();
	List<Location> exits = new ArrayList<Location>(); // may change this to map

	String description = "This area is an empty white expanse stretching for endless miles and miles";
	
	public void addExit(Location l){
		exits.add(l);
	}
	
	public void setDesc(String desc){
		description = desc;
	}

	public String getDescription() {return description;}
	
}
