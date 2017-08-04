package commands;

import mmoServer.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LookCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {
        // is logged in
        // has character
        return false;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {
        //get games desc
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
