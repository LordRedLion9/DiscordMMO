package commands;

import com.sun.security.ntlm.Server;
import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageMeCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        User user = ServerMain.getUser(event.getAuthor().getId());

        if (user.getChannel() == null){
            ServerMain.botSay("You do not have a private channel set with me", event.getChannel());
            return false;
        }

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {
        User user = ServerMain.getUser(event.getAuthor().getId());
        System.out.println("SENDING PRIVATE MESSAGE");
        ServerMain.botTell("Hello there!", user);
    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
