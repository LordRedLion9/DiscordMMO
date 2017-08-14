package commands;

import mmoServer.Command;
import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AdminInfoCommand implements Command{

    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {
        User user = ServerMain.getUser(event.getAuthor().getId());
        if (user.isAdmin()) {
            return true;
        }
        return false;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {
        String message;
        message = ServerMain.getGame().adminInfo();
        ServerMain.botSay(message, event.getChannel());
    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {
    }

    @Override
    public String help() {
        return "Use to display info on the game.";
    }
    
}
