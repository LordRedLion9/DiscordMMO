package commands;

import mmoServer.Command;
import mmoServer.ServerMain;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LoginCommand implements Command{

	@Override
	public boolean isCalled(String[] args, MessageReceivedEvent event) {
		
		String userId = event.getAuthor().getId();
		
		if (ServerMain.checkRegistered(userId)){
			return true;
		}
		else{
			ServerMain.botSay("You are not Registered. Please register using the ~!command", event.getChannel());
			return false;
		}
	}

	@Override
	public void doAction(String[] args, MessageReceivedEvent event) {
		
		String userId = event.getAuthor().getId();
		ServerMain.loginUser(userId);
		
	}

	@Override
	public void endCommand(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help() {
		return "Use to log into the game server";
	}

}