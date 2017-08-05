package commands;

import java.util.*;

import mmoServer.Command;
import mmoServer.ServerMain;
import mmoGame.Item;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LookCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        String userID = event.getAuthor().getId();

        if (!ServerMain.checkLoggedIn(userID)){
            //ServerMain.botSay("You are not logged in");
            return false;
        }

        if (ServerMain.getUser(userID).getChar() == null){
            ServerMain.botSay("You have no character", event.getChannel());
            return false;
        }

        return true;

    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {
        //get games desc, extend this to be able to look at items given the right arguments
        String userID = event.getAuthor().getId();

        ServerMain.botSay(ServerMain.getUser(userID).getChar().getLocation().getDescription(), event.getChannel()); //Say location desc

        List<Item> items = ServerMain.getUser(userID).getChar().getLocation().getAllItems();

        String itemMsg = "fuk";

        if (items.size() < 1){

            itemMsg = "There are no items here.";

        }else {
            
        ServerMain.botSay("There are " + items.size() + " items", event.getChannel());

            for (int i = 0; i < items.size(); i++) {
                
                if (i == 0) {
                    ServerMain.botSay("Start", event.getChannel());
                    itemMsg = "There is a " + items.get(i).getItemName();
                } else if (i == items.size() - 1){
                    ServerMain.botSay("End", event.getChannel());
                    itemMsg += items.get(i).getItemName() + " on the ground here.";
                } else {
                    ServerMain.botSay("Item", event.getChannel());
                    itemMsg += ", " + items.get(i).getItemName();
                }
            }
        }

        ServerMain.botSay(itemMsg, event.getChannel());

    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {
        //nothings
    }

    @Override
    public String help() {
        return null;
    }
}
