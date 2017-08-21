package commands;

import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CreateCharacterCommand implements Command{

    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {
        User user = ServerMain.getUser(event.getAuthor().getId());
        if (user.getChar() != null) {
            ServerMain.botSay("You already have a character.", event.getChannel());
            return false;
        }
        if (args.length != 1) {
            ServerMain.botSay("Invalid number of arguments. Please specify \"male\" or \"female\".", event.getChannel());
            return false;
        }
        if (!args[0].equals("male") & !args[0].equals("female")){
            ServerMain.botSay("Invalid argument. Please specify \"male\" or \"female\".", event.getChannel());
            return false;
        }
        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {
        String userID = event.getAuthor().getId();
        User user = ServerMain.getUser(userID);
        
        ServerMain.getGame().createNewChar(user, args[0]);
        ServerMain.botSay("Created the " + args[0] + " character " + user.getName() + "!", event.getChannel());
    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {
        
    }

    @Override
    public String help() {
        return "Use to create a new character.";
    }
    
}
