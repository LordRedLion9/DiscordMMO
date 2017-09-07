package commands;

import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;



public class InspectCommand implements Command{
    
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {
        if (args.length != 1) {
            ServerMain.botSay("Please specifiy a single thing to inspect.", event.getChannel());
            return false;
        }
        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {
        String userID = event.getAuthor().getId();
        
        String message = ServerMain.getGame().inspect(userID, args[0]);
        
        ServerMain.botSay(message, event.getChannel());
    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {
    }
    
    @Override
    public String help(){
        return "INSPECT COMMAND:\n" +
                "Usage - ~!inspect \"item\" | \"character\" \n" +
                "Use to inspect an item or character";
    }
}
