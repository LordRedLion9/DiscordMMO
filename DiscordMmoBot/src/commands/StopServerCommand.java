package commands;

import mmoServer.GameSaver;
import mmoServer.ServerMain;
import mmoServer.main;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class StopServerCommand implements Command{

    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {
        return ServerMain.isAdmin(event);
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {
        main.stop();
    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {
    }

    @Override
    public String help() {
        return "Stops shit";
    }
    
}
