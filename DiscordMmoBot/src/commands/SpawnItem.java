package commands;

import com.sun.security.ntlm.Server;
import mmoGame.Weapon;
import mmoServer.Command;
import mmoServer.ServerMain;
import mmoGame.Item;
import mmoGame.PlayerCharacter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SpawnItem implements Command {

    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        String userID = event.getAuthor().getId();

        if (!ServerMain.checkLoggedIn(userID)){return false;}
        if (ServerMain.getUser(userID).getChar() == null){return false;}

        if (args.length < 2) {
            ServerMain.botSay("Error: incorrect number of args. USAGE: ~!spawn (ITEM | WEAPON) name", event.getChannel());
        }

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {


        String userID = event.getAuthor().getId();
        PlayerCharacter playerC = ServerMain.getUser( userID ).getChar(); //This is lazy

        Item newItem = null;

        if (args[0].toLowerCase().equals("item")){

            newItem = new Item(args[1]);


        }else if (args[0].toLowerCase().equals("weapon")){

            newItem = new Weapon(args[0], Integer.parseInt(args[1]), args[2]);

        }else{

            ServerMain.botSay("Invalid item type. ITEM or WEAPON", event.getChannel());
            ServerMain.botSay("Item type received was: " + args[0], event.getChannel());
        }

        if (newItem != null) {
            ServerMain.botSay(playerC.getName() + " snaps his fingers, and a " + newItem.getItemName() + " appears here", event.getChannel());
            playerC.getLocation().addItem(newItem);
        }

    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
