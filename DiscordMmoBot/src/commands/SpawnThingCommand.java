package commands;

import com.sun.security.ntlm.Server;
import mmoServer.Command;
import mmoServer.ServerMain;
import mmoGame.Item;
import mmoGame.PlayerCharacter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SpawnThingCommand implements Command {

    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        String userID = event.getAuthor().getId();

        if (!ServerMain.checkLoggedIn(userID)){return false;}
        if (ServerMain.getUser(userID).getChar() == null){return false;}

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {

        //TODO: Extend this so you can spawn NPCs and World Objects with arguments

        String userID = event.getAuthor().getId();
        PlayerCharacter playerC = ServerMain.getUser( userID ).getChar(); //This is lazy

        Item newItem = new Item("Generic Item"); //This is also lazy
        ServerMain.getGame().spawnItem(newItem, playerC.getLocation());

        ServerMain.botSay(playerC.getName() + " snaps his fingers, and a " + newItem.getItemName() + " appears here", event.getChannel());

    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
