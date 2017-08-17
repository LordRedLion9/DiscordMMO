package mmoGame;

public class Item implements Inspectable{

    private String itemName;

    private static int idNum = 0;
    private String itemID;
    private String desc = "A generic, grey cube. The most simple form of item. Wonderful.";
    private Location location; //Items keeping ref to location is perhaps unnecessarily coupled? If its just for admin info, this may need a workaround? #Opinions


    public Item(String name){
        itemName = name;
        itemID = "I" + (++idNum);
        GameMain.Items.add(this);
    }

    public String getItemName() {
        return itemName;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
