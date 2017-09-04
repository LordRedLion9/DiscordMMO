package mmoGame;

import java.util.ArrayList;

import mmoServer.ServerMain;
import mmoServer.User;

public class GameMain implements Runnable {

    private boolean running = false;

    public static ArrayList<PlayerCharacter> Players = new ArrayList<>(); //List of ALL the Players currently spawned in the game
    public static ArrayList<NPC> NPCs = new ArrayList<>(); //List of ALL the NPCs currently spawned in the game
    public static ArrayList<Item> Items = new ArrayList<>(); //List of ALL the items currently spawned in the game
    public static ArrayList<Location> Locations = new ArrayList<>(); //List of ALL the locations in the game

    Location spawn;


    public void run() {

        running = true;

        spawn = new Location();
        spawn.setName("The Testing Zone");
        spawn.setDesc(
                "The blank white expanse of the testing zone stretches infinitely all around you."
        );

        Location testGarden = new Location();
        testGarden.setName("Testing Garden");
        testGarden.setDesc(
                "A nice white garden area, with nice white trees, plants and some nice white reclining chairs"
        );

        Exit e1 = new Exit(spawn, testGarden);
        spawn.addExit(e1);
        testGarden.addExit(e1);

        GameRunner runner = new GameRunner(this);
        Thread t = new Thread(runner);
        t.start();

    }


    public void gameUpdate() {
        //Game tick update stuff here. Things that happen from this should be time based things.

        for (Location l : Locations){
            l.update();
        }
    }

    //OTHER METHODS

    public void teleportChar(Character c, Location l) {

        //straight up change of location
        //remove char from current loc
        //set Chars location to l
        //add char to l

    }

    public void createNewChar(User user, String sex) {
        System.out.println("Creating new Character");
        System.out.println("User: " + user.getName());
        System.out.println("Sex: " + sex);
        PlayerCharacter newChar = new PlayerCharacter(user.getName(), sex);
        user.setChar(newChar);

        newChar.setLocation(spawn);
        spawn.addChar(newChar);

    }

    public void spawnItem(Item i, Location l) {
        l.addItem(i);
    }

    private Inspectable getInspectable(Location loc, String input) {
        Inspectable result = null;
        String name = input.toLowerCase().trim();
        for (PlayerCharacter pc : loc.Players) {
            if (pc.getName().toLowerCase().trim().equals(name)) {
                result = pc;
            }
        }
        for (NPC npc : loc.NPCs) {
            if (npc.getName().toLowerCase().trim().equals(name)) {
                result = npc;
            }
        }
        for (Item item : loc.Items) {
            if (item.getItemName().toLowerCase().trim().equals(name)) {
                result = item;
            }
        }
        return result;
    }


    public void spawnNPC(NPC npc, Location loc){
        loc.addNPC(npc);
    }

    public String inspect(String playerID, String target) {
        PlayerCharacter c = ServerMain.getUser(playerID).getChar();
        Inspectable i = getInspectable(c.getLocation(), target);
        if (i != null) {
            return i.getInfo();
        } else {
            return "No such thing in sight.";
        }
    }

    public String adminInfo() {
        String message = "```md\n";

        message += "### Displaying game info: ###\n";

        if (!Players.isEmpty()) {
            message += "\n<Players>:";
            for (PlayerCharacter p : Players) {
                message += "\n(ID:" + p.getPlayerID() + ")[" + p.getName() + "] is in " + p.getLocation().getName();
            }
            message += "\n";
        }
        if (!NPCs.isEmpty()) {
            message += "\n<NPCs>:";
            for (NPC npc : NPCs) {
                message += "\n(ID:" + npc.getNPCID() + ")[" + npc.getName() + "] is in " + npc.getLocation().getName();
            }
            message += "\n";
        }
        if (!Items.isEmpty()) {
            message += "\n<NPCs>:";
            for (Item item : Items) {
                message += "\n(ID:" + item.getItemID() + ")[" + item.getItemName() + "] is in " + item.getLocation().getName();
            }
            message += "\n";
        }
        if (!Locations.isEmpty()) {
            message += "\n<Locations>:";
            for (Location loc : Locations) {
                message += "\n(ID:" + loc.getLocationID() + ")[" + loc.getName() + "]";
            }
            message += "\n";
        }

        message += "```";
        return message;
    }

}

class GameRunner implements Runnable, java.io.Serializable {

    private GameMain main;
    public boolean isRunning = false;

    public GameRunner(GameMain gameMain) {
        main = gameMain;
        isRunning = true;
    }

    @Override
    public void run() {

        long lastTime = System.currentTimeMillis();

        do{
            long nowTime = System.currentTimeMillis();
            long unprocessedTicks = (nowTime - lastTime) / 1000; //One unprocessed tick for every second

            if (unprocessedTicks >= 1) {
                main.gameUpdate();
                unprocessedTicks = 0;
                lastTime = nowTime;
            }
        }while(isRunning);
    }

}