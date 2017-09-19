package commands;

import mmoGame.NPC;
import mmoGame.PlayerCharacter;
import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SayCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        User user = ServerMain.getUser(event.getAuthor().getId());

        if (user.getChar().isMuted){return false;}

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {

        User user = ServerMain.getUser(event.getAuthor().getId());
        PlayerCharacter charac = user.getChar();


        if (args.length == 0){
            charac.getLocation().saytoLocation(charac.getName()+ " opens their mouth to speak, but no words come out");
        }else if (args.length == 1){
            charac.getLocation().saytoLocation(charac.getName()+ " says: " + args[0]);
        }else{

            for (NPC p : charac.getLocation().NPCs){
                if (p.getName().equals(args[0])){
                    p.sayTo(args[1]);
                    charac.getLocation().saytoLocation(charac.getName() + " says to " + p.getName()+ ": " + args[1]);
                    return;
                }
            }

            for (PlayerCharacter p : charac.getLocation().Players){ //private message
                if (p.getName().equals(args[0])){
                    user.sendPrivateMsg(charac.getName() + " says to you: " + args[1]);
                    return;
                }
            }

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
