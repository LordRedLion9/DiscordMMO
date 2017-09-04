package commands;

import mmoGame.PlayerCharacter;
import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MoveCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {
        if (args.length != 1){
            ServerMain.botSay("You did not specify an exit", event.getChannel());
            return false;
        }

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {

        String ID = event.getAuthor().getId();
        PlayerCharacter charac = ServerMain.getUser(ID).getChar();

        if(charac.moveCharacter(args[0]) == false){
            ServerMain.botSay("Invalid move. See console for details", event.getChannel());
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
