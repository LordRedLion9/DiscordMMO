package mmoServer;

import java.util.*;

import javax.security.auth.login.LoginException;

import commands.*;
import mmoGame.GameMain;
import mmoServer.Command;
import mmoServer.CommandParser;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
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
		
		commands.put("register", new RegisterCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		
		game = new GameMain();

	}
	
	public static void registerUser(String ID, String name){
		
		User newUser = new User(name, ID);
		registeredUsers.put(ID, newUser);
		
		System.out.println("New User created. Name: " + newUser.getName() + " ID: " + newUser.getID());
		
	}
	
	public static void loginUser(String ID){
		
		if (checkRegistered(ID)){
			
			User user = registeredUsers.get(ID);
			loggedInUsers.put(ID, user);

			System.out.println("User logged in. Name: " + user.getName() + " ID: " + user.getID());
			
		}
	}
	
	public static void logoutUser(String ID){
		
		if (checkLoggedIn(ID)){
			loggedInUsers.remove(ID);
		}
		
	}
	
	public static boolean checkLoggedIn(String ID){
		
		for (String s : loggedInUsers.keySet()){
			if (s.equals(ID)){
				return true;
			}
		}
		
		return false; //Is not logged in
		
	}
	
	//Method to check if user is registered and has User object associated with them
	public static Boolean checkRegistered(String ID){
		
		for (String s : registeredUsers.keySet()){
			if (s.equals(ID)){
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
			
			Command command = commands.get(cmd.invoke);
			
			//check to see if either the user is logged in, or one of the three login commands. This is to save checking in each command whether they are logged in
			if (cmd.invoke.equals("login") || cmd.invoke.equals("register") || cmd.invoke.equals("logoff") || checkLoggedIn(cmd.event.getAuthor().getId())){				
				
				boolean acvtivate = command.isCalled(cmd.args, cmd.event);
				
				if (acvtivate){
					System.out.println("Command is SAFE");
					command.doAction(cmd.args, cmd.event);
					command.endCommand(acvtivate, cmd.event);
				} else {
					System.out.println("Command is UNSAFE");
					command.endCommand(acvtivate, cmd.event);
				}
			} 
			
			
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
