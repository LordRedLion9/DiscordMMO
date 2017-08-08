package mmoGame;

public class Item {

    private String itemName;
    private String desc = "A generic, grey cube. The most simple form of item. Wonderful";



    public Item(String name){
        itemName = name;
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

}
