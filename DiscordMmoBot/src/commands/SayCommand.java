package commands;

import mmoGame.PlayerCharacter;
import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SayCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {

        User user = ServerMain.getUser(event.getAuthor().getId());
        PlayerCharacter charac = user.getChar();

        if (args.length == 0){
            charac.getLocation().saytoLocation(charac.getName()+ " opens their mouth to speak, but no words come out");
        }else{

            charac.getLocation().saytoLocation(charac.getName()+ " says: " + args[0]);

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
