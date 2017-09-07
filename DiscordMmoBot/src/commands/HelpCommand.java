package commands;

import mmoServer.ServerMain;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements Command{
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {

        String message = "";

        if (args.length > 0){
            message += ServerMain.commands.get(args[0]).help();
        }else{

            message += help() + "\n";

            for(String s : ServerMain.commands.keySet()){
                message += s + "\n";
            }

        }

        ServerMain.botSay(message, event.getChannel());

    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {



    }

    @Override
    public String help() {
        return "HELP COMMAND: \n" +
                "Usage - ~!help 'command'\n" +
                "Gives a help message for a given command. Gives list of available commands if no command is specified";

    }
}
