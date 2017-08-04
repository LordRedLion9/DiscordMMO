package mmoServer;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {

	    //Method to test for correct usage of the command
		public boolean isCalled(String[] args, MessageReceivedEvent event);

	    //Main action of the command
		public void doAction(String[] args, MessageReceivedEvent event);
		
		
		//Always called, even if doAction was not
		public void endCommand(boolean success, MessageReceivedEvent event);
		
		//Returns the help text for the command
		public String help();
		
}
