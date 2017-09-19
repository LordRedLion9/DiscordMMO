package commands;

import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.nio.channels.Channel;

public class SetChannelCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {

        User user = ServerMain.getUser(event.getAuthor().getId());

        if (event.getChannelType() == ChannelType.PRIVATE){
            user.messageToPublic = false;
            ServerMain.botTell("Ok, ill send your game messages privately",user);
        } else{
            user.messageToPublic = true;
            user.setPublicChannel(event.getChannel());
            ServerMain.botSay("OK! Ill send your game messages here!",event.getChannel());
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
