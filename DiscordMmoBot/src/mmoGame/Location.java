package mmoGame;

import java.util.*;

import mmoServer.ServerMain;

public class Location {

    List<PlayerCharacter> Players = new ArrayList<>();
    List<NPC> NPCs = new ArrayList<>();

    List<Location> Exits = new ArrayList<Location>(); // may change this to map

    List<Item> Items = new ArrayList<Item>();

    private String name = "generic area";
    private String locationID;

    String description = "This area is an empty white expanse stretching for endless miles and miles";

    public Location() {
        this.locationID = "L" + (ServerMain.getGame().Locations.size() + 1);
        ServerMain.getGame().Locations.add(this);
    }

    public void addChar(PlayerCharacter c) {
        Players.add(c);
    }

    public void removeChar(PlayerCharacter c) {
        Players.remove(c);
    }

    public void addNPC(NPC npc) {
        NPCs.add(npc);
    }

    public void removeNPC(NPC npc) {
        NPCs.remove(npc);
    }

    public void addExit(Location l) {
        Exits.add(l);
    }

    public void addItem(Item i) {
        Items.add(i);
    }

    public List<Item> getAllItems() {
        return Items;
    }

    public void setDesc(String desc) {
        description = desc;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        String message = "```md\n";

        message += "########## Looking around ###########\n";
        message += this.description + "\n";

        if (!Items.isEmpty()) {

            for (int i = 0; i < Items.size(); i++) {

                Item item = Items.get(i);

                if (i == 0) { //If it's the first item
                    message += "There is";
                }else if (i == Items.size() - 1) { //last item
                    message += " and";
                }

                switch (String.valueOf(item.getItemName().charAt(0)).toLowerCase()) {
                    case "u":
                        message += " an ";
                        break;
                    default:
                        message += " a ";
                        break;
                }

                message += item.getItemName();

                if (i == Items.size() - 1) { //If it's the last item
                    message += " on the ground here.";
                } else {
                    if (Items.size() != 2) {
                        message += ",";
                    }
                }

            }
            message += "\n";
        }

        if (!Players.isEmpty()) {
            message += "\nPlayers present: \n";
            for (PlayerCharacter p : Players) {
                message += p.getName() + "\n";
            }
        }

        if (!NPCs.isEmpty()){



            for (int i = 0;i < NPCs.size(); i++){

                String npcName = NPCs.get(i).getName();

                if (i == NPCs.size() - 1) {
                    message += npcName;
                } else {
                    message += npcName + " and ";
                }

            }

            message += " is standing here.";
        }


        message += "```";
        return message;
    }

}
