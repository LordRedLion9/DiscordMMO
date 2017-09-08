package commands;

import java.util.*;

import mmoServer.ServerMain;
import mmoGame.Item;
import mmoServer.User;
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
        
        String userID = event.getAuthor().getId();
        User user = ServerMain.getUser(userID);

        ServerMain.botTell("```md\n" + user.getChar().getLocation().getInfo() + "```", user); //maybe too coupled?

    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {
        //nothings
    }

    @Override
    public String help() {
        return "LOOK ACTION \n" +
                "Usage ~!look \n" +
                "Get a description of your surroundings. Have a good look around, see whats about!";
    }
}
