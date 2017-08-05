package commands;

import mmoServer.Command;
import mmoServer.ServerMain;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LogoutCommand implements Command {

	@Override
	public boolean isCalled(String[] args, MessageReceivedEvent event) {
		
		String ID = event.getAuthor().getId();
		
		if (!ServerMain.checkLoggedIn(ID)){
			ServerMain.botSay("You are not logged in!", event.getChannel());
			return false;
		}
		
		return true;
	}

	@Override
	public void doAction(String[] args, MessageReceivedEvent event) {
		
		String ID = event.getAuthor().getId();
		ServerMain.logoutUser(ID);
		
		ServerMain.botSay("You are now logged out! Thank you for playing!", event.getChannel());

	}

	@Override
	public void endCommand(boolean success, MessageReceivedEvent event) {
		// nope

	}

	@Override
	public String help() {
		// TODO Fill in Logout command help
		return null;
	}

}
