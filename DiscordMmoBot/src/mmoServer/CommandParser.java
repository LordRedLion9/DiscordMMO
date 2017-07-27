package mmoServer;

import java.util.*;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandParser {

	public CommandContainer parse(String raw, MessageReceivedEvent event){
		
		
		String beheaded = raw.replaceFirst("~!", ""); //Remove the ~!
		String[] beheadedSplit = beheaded.split(" "); //Split
		
		String invoke = beheadedSplit[0]; //Get invoked command
		String[] args = Arrays.copyOfRange(beheadedSplit, 1, beheadedSplit.length); //Get command arguments
		
		
		
		return new CommandContainer(raw, beheaded, beheadedSplit, invoke, args, event);
		
	}
	
	public class CommandContainer{
		public final String raw;
		public final String beheaded;
		public final String[] splitBeheaded;
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent event;
		
		public CommandContainer(String rw, String bh, String[] sbh, String invoke, String[] args, MessageReceivedEvent event){
			raw = rw;
			beheaded = bh;
			splitBeheaded = sbh;
			
			this.invoke = invoke;
			this.args = args;
			this.event = event;
		}
	}
}


