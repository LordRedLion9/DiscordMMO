package commands;

import mmoServer.ServerMain;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RegisterCommand implements Command {

	@Override
	public boolean isCalled(String[] args, MessageReceivedEvent event) {
		
		String ID = event.getAuthor().getId();

		if (event.getChannelType() != ChannelType.PRIVATE){
		    ServerMain.botSay( "You need to register in a private message to me!" ,event.getChannel());
		    return false;
        }

		if (!ServerMain.checkRegistered(ID)){
			return true;
		} else {
			ServerMain.botSay("You are alredy registered. Please use the ~!login command to log in", event.getChannel());
			return false;
		}
		
	}

	@Override
	public void doAction(String[] args, MessageReceivedEvent event) {
		
		String ID = event.getAuthor().getId();
		String name = event.getAuthor().getName();
        PrivateChannel channel = (PrivateChannel) event.getChannel();

		ServerMain.registerUser(ID, name, channel);

		ServerMain.botSay("Great! You are now registered. In future, use the ~!login command to log in.", event.getChannel());
                
        ServerMain.handleCommand(ServerMain.parser.parse("~!login", event)); //Janky asf, but it should work for now
            
		
	}

	@Override
	public void endCommand(boolean success, MessageReceivedEvent event) {
	}

	@Override
	public String help() {
		// TODO Fill in help for Register command
		return null;
	}

}
