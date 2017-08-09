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

        String itemMsg;

        if (items.size() < 1){

            itemMsg = "There are no items here.";

        }else {
            

            itemMsg = "There is a ";
            for (int i = 0; i < items.size(); i++) {
                
                if (i == items.size()){itemMsg += " and a ";} //If it's the last item

                itemMsg += items.get(i).getItemName();
                
                if (i < items.size()-1){itemMsg += ", ";} //If it's not the last item

            }
            itemMsg += " on the ground here.";
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
