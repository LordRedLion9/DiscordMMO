package mmoServer;

import java.util.*;

import javax.security.auth.login.LoginException;

import mmoGame.GameMain;
import mmoServer.Command;
import mmoServer.CommandParser;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.RateLimitedException;


public class ServerMain {

	public static JDA jda;
	private BotInfo botInfo;
	
	public static CommandParser parser = new CommandParser(); //The command processing object
	public static HashMap<String, Command> commands = new HashMap<String, Command>(); //A map of user command strings to relevant command objects <Typed Command, Command>
	
	public static HashMap<String, User> registeredUsers = new HashMap<String, User>(); //Registered Users. <User Discord ID Number, User profile object>
	public static HashMap<String, User> loggedInUsers = new HashMap<String, User>(); //Map of users currently logged in. <User Discord ID Number, User profile object>
	
	public GameMain game;
	Thread gameThread;
	
	//MAIN METHOD. STARTS THE PROGRAM (currently starts here in the server)
	public static void main(String[] args) {
		ServerMain s = new ServerMain();
		s.run();
	}
	
	/***
	 * Sets up the JDA and starts the server running
	 */
	public void run(){
		
		setupJDA();
		game = new GameMain();

	}
	
	public static void loginUser(String name){
		
		if (checkRegistered(name)){
			
			User user = registeredUsers.get(name);
			loggedInUsers.put(name, user);

		}
		else{
			
			
			
		}
		
	}
	
	//Method to check if user is registered and has User object associated with them
	public static Boolean checkRegistered(String name){
		
		for (String s : registeredUsers.keySet()){
			if (s.equals(name)){
				return true;
			}
		}
		
		return false; //Is not registered
	}

	private void setupJDA(){
		
		botInfo = new BotInfo();
		botInfo.readInfo();
		
		try {
			jda = new JDABuilder(AccountType.BOT)
					.setToken(BotInfo.botToken)
					.addEventListener(new BotListener())
					.setAutoReconnect(true)
					.setAudioEnabled(true)
					.buildBlocking();
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			System.out.println("Setting up JDA ERROR: ");
			e.printStackTrace();
		}
		
		
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
