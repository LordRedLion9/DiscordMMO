package mmoServer;

import java.util.HashMap;

import javax.security.auth.login.LoginException;

import mmoServer.Command;
import mmoServer.CommandParser;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class ServerMain {

	public static JDA jda;
	private BotInfo botInfo;
	
	public static CommandParser parser = new CommandParser();
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	
	//MAIN METHOD. STARTS THE SERVER
	public static void main(String[] args) {
		ServerMain s = new ServerMain();
		
		s.run();
	}
	
	/***
	 * Sets up the JDA and starts the server running
	 */
	public void run(){
		
		try {
			setupJDA();
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			System.out.println("Error setting up the JDA");
			e.printStackTrace();
		}

	}

	private void setupJDA() throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		
		botInfo = new BotInfo();
		botInfo.readInfo();
		
		jda = new JDABuilder(AccountType.BOT)
				.setToken(BotInfo.botToken)
				.addEventListener(new BotListener())
				.setAutoReconnect(true)
				.setAudioEnabled(true)
				.buildBlocking();
		
		
	}
	
	/***
	 * 
	 * When a command is recognized and received, this method checks and handles the command
	 * 
	 * @param cmd
	 * The command container send by the command parser
	 * 
	 */
	public static void handleCommand(CommandParser.CommandContainer cmd){
		
		System.out.println("Server is handling a command");
		
		if (commands.containsKey(cmd.invoke)){
			
			//do stuff
			
		} else{
			
			System.out.println("Invalid command");
			botSay("You Fail", cmd.event.getChannel());
			
		}
		
	}
	
	public static void botTell(String msg, User user){	
		//private message user with message
	}
	
	public static void botSay(String msg, MessageChannel channel){	
		//send message to channel
		channel.sendMessage(msg).queue();
	}
	
	public static void log(String msg) {
		System.out.println(msg);	
	}
	
}
