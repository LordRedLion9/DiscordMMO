package mmoServer;

import java.util.*;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandParser {

	public CommandContainer parse(String raw, MessageReceivedEvent event){
		
		
		String beheaded = raw.replaceFirst("~!", ""); //Remove the ~!

		Scanner cScan = new Scanner(beheaded);
		String invoke = cScan.next();

		boolean parsingString = false;
		String toAdd = "";
		List<String> rawArgs = new ArrayList<>();

		while(cScan.hasNext()){

			String arg = cScan.next();
			System.out.println(arg);

			if (parsingString){ //Things being scanned are part of a string arg

				if (arg.charAt(arg.length() - 1) == '\"'){ //String arg ends
					parsingString = false;
					toAdd += arg;
					rawArgs.add(toAdd.replace("\"", "").trim());
					System.out.println("Added string: \"" + toAdd.replace("\"", "") + "\" as an argument");
					toAdd = "";
				} else {
					toAdd += arg + " ";
				}

			} else { //Not part of a string arg

				if (arg.startsWith("\"")){ //String arg begins
					parsingString = true;
					toAdd += arg + " ";
                                        if (arg.endsWith("\"")){
                                            rawArgs.add(toAdd.replace("\"", "").trim());
                                            System.out.println("Added string: \"" + toAdd.replace("\"", "").trim() + "\" as an argument");
                                            toAdd = "";
                                            parsingString = false;
                                        }
				} else {
					rawArgs.add(arg);
					System.out.println("Added " + arg + " to list of args");
				}
			}
		}

		String[] args = rawArgs.toArray(new String[0]);

		return new CommandContainer(raw, beheaded, invoke, args, event);
		
	}
	
	public class CommandContainer{
		public final String raw;
		public final String beheaded;
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent event;
		
		public CommandContainer(String rw, String bh,  String invoke, String[] args, MessageReceivedEvent event){
			raw = rw;
			beheaded = bh;
			
			this.invoke = invoke;
			this.args = args;
			this.event = event;
		}
	}
}


