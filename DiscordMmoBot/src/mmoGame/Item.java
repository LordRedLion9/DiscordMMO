package mmoGame;

import mmoServer.ServerMain;

public class Item implements Inspectable{

    private String itemName;
    private String itemID;
    private String desc = "A generic, grey cube. The most simple form of item. Wonderful.";
    private Location location;



    public Item(String name){
        itemName = name;
        itemID = "I" + (ServerMain.getGame().Items.size() + 1);
        ServerMain.getGame().Items.add(this);
    }

    public String getItemName() {
        return itemName;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getInfo() {
        String message = "```md\n";
        
        message += "########## Inspecting a " + this.getItemName() + " ##########"
                + "\n" + this.getDesc()
                + "```";
        
        return message;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
