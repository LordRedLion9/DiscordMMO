package commands;

import mmoServer.Command;
import mmoServer.ServerMain;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RegisterCommand implements Command {

	@Override
	public boolean isCalled(String[] args, MessageReceivedEvent event) {
		
		String ID = event.getAuthor().getId();
		
		if (!ServerMain.checkRegistered(ID)){
			return true;
		}
		else{
			ServerMain.botSay("You are alredy registered. Please use the ~!login command to log in", event.getChannel());
			return false;
		}
		
	}

	@Override
	public void doAction(String[] args, MessageReceivedEvent event) {
		
		String ID = event.getAuthor().getId();
		String name = event.getAuthor().getName();
		
		ServerMain.registerUser(ID, name);
		ServerMain.botSay("Great! You are now registered. Please use the ~!login command to log in", event.getChannel());
		
	}

	@Override
	public void endCommand(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
