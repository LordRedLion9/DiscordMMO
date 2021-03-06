package commands;

import mmoServer.ServerMain;
import mmoServer.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LoginCommand implements Command{

	@Override
	public boolean isCalled(String[] args, MessageReceivedEvent event) {
		
		String userId = event.getAuthor().getId();
		
		if(ServerMain.checkLoggedIn(userId)){
			ServerMain.botSay("You are already logged in! Use the ~!logout command to log out", event.getChannel());
			return false;
		}
		
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
		ServerMain.botSay("You have logged into the server. Welcome!", event.getChannel());

		User user = ServerMain.getUser(userId);
                
                if (user.getChar() == null) {
                    ServerMain.botSay("You don't have a character yet! Create one with ~!createchar", event.getChannel());
                } else {
                    String areaDesc = user.getChar().getLocation().getDescription();
                    ServerMain.botSay(areaDesc, event.getChannel()); //Say description for area
                }

	}

	@Override
	public void endCommand(boolean success, MessageReceivedEvent event) {
		// nope
		
	}

	@Override
	public String help() {
		return "LOGIN COMMAND \n" +
				"Usage ~!login \n" +
				"Use to log into the game server";
	}

}
