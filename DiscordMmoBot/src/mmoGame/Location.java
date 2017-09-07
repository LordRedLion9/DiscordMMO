package mmoGame;

import java.util.*;

import mmoServer.ServerMain;

public class Location implements java.io.Serializable{

    List<PlayerCharacter> Players = new ArrayList<>();
    List<NPC> NPCs = new ArrayList<>();

    public List<Exit> Exits = new ArrayList<>(); // may change this to map

    List<Item> Items = new ArrayList<>();

    private String name = "generic area";

    private static int locIdNum = 0;
    private String locationID;

    private String description = "This area is an empty white expanse stretching for endless miles and miles";

    public Location() {
        this.locationID = "L" + (++locIdNum);
        GameMain.Locations.add(this);
    }

    public void update(){

        for (NPC npc : NPCs){
            npc.update();
        }

    }

    public void addPlayer(PlayerCharacter c) {
        Players.add(c);
    }

    public void removePlayer(PlayerCharacter c) {
        Players.remove(c);
    }

    public void addNPC(NPC npc) {
        NPCs.add(npc);
    }

    public void removeNPC(NPC npc) {
        NPCs.remove(npc);
    }


    public void addExit(Exit l) {
        Exits.add(l);
    }

    public Exit getExit(String name){
        System.out.println("Checking for exit: " + name);
        for(Exit e : Exits){
            if (e.getExitName().equals(name)) {
                System.out.println("Found exit: " + e.getExitName());
                return e;
            }
        }

        System.out.println("Error, no exit in this location by that name");
        return null;
    }


    public Exit getExit(String name, int i){

        List<Exit> exts = new ArrayList<>();
        for(Exit e : Exits){
            if (e.getExitName().equals(name)) {
                exts.add(e);
            }
        }

        if (exts.size() == 0){
            System.out.println("Error, no exits in this location by that name (group mode)");
            return null;
        }

        if (i >= exts.size()){
            System.out.println("Error, no exits in that location by that name and index (group mode)");
            return null;
        }

        return exts.get(i);


    }

    public List<PlayerCharacter> getAllPlayers() {
        return Players;
    }

    public PlayerCharacter getPlayer(PlayerCharacter charac){
        for (PlayerCharacter p : Players){
            if(p.equals(charac)){
                return p;
            }

        }

        System.out.println("ERROR: That player is not in this location");
        return null;

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

    public void saytoLocation(String msg){

        String message = "```md\n" + msg + "```";

        for (PlayerCharacter player : Players){
            ServerMain.botTell(message, player.getOwningUser());
        }

    }

    public String getInfo() { //TODO: Eventually refactor the shite out of this. Maybe. Dunno if I can be bothered.
        String message = "";

        message += "########## Looking around ###########\n";
        message += this.description + "\n \n";

        if (!Exits.isEmpty()){

            message += "Exits available: \n";
            for (Exit e : Exits){
                message += e.getExitName() + "\n";
            }

            message += "\n";

        }

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

        return message;
    }
}
