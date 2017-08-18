package mmoServer;

import java.util.*;

import javax.security.auth.login.LoginException;

import commands.*;
import mmoGame.GameMain;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.RateLimitedException;


public class ServerMain {

    private static JDA jda;
    private BotInfo botInfo;

    public static CommandParser parser = new CommandParser(); //The command processing object
    private static HashMap<String, Command> commands = new HashMap<>(); //A map of user command strings to relevant command objects <Typed Command, Command>

    private static HashMap<String, User> registeredUsers = new HashMap<>(); //Registered Users. <User Discord ID Number, User profile object>
    transient private static HashMap<String, User> loggedInUsers = new HashMap<>(); //Map of users currently logged in. <User Discord ID Number, User profile object>

    private static GameMain game;
    private Thread gameThread;

    //MAIN METHOD. STARTS THE PROGRAM (currently starts here in the server)
    public static void main(String[] args) {
        ServerMain s = new ServerMain();
        s.run();
    }

    /***
     * Sets up the JDA and starts the server running
     */
    public void run() {

        setupJDA();

        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());

        commands.put("createchar", new CreateCharacterCommand());
        commands.put("look", new LookCommand());
        commands.put("inspect", new InspectCommand());
        commands.put("spawn", new SpawnItem());

        commands.put("admininfo", new AdminInfoCommand());
        commands.put("spawnnpc", new SpawnNPCCommand());

        startGame();

    }

    public void startGame() {

        game = new GameMain();

        gameThread = new Thread(game);
        gameThread.start();

    }

    public static GameMain getGame() {
        return game;
    }

    public static void registerUser(String ID, String name) {

        User newUser = new User(name, ID);

        registeredUsers.put(ID, newUser);

        System.out.println("New User created. Name: " + newUser.getName() + " ID: " + newUser.getID());

    }

    public static void loginUser(String ID) {

        if (checkRegistered(ID)) {

            User user = registeredUsers.get(ID);
            loggedInUsers.put(ID, user);

            System.out.println("User logged in. Name: " + user.getName() + " ID: " + user.getID());

        }
    }

    public static void logoutUser(String ID) {

        if (checkLoggedIn(ID)) {
            loggedInUsers.remove(ID);
        }

    }

    public static boolean checkLoggedIn(String ID) {

        for (String s : loggedInUsers.keySet()) {
            if (s.equals(ID)) {
                return true;
            }
        }

        return false; //Is not logged in

    }

    public static User getUser(String ID) {
        return loggedInUsers.get(ID);
    }

    //Method to check if user is registered and has User object associated with them
    public static Boolean checkRegistered(String ID) {

        for (String s : registeredUsers.keySet()) {
            if (s.equals(ID)) {
                return true;
            }
        }

        return false; //Is not registered
    }

    private void setupJDA() {

        botInfo = new BotInfo();
        botInfo.readInfo();

        try {
            jda = new JDABuilder(AccountType.BOT)
                    //.setToken(BotInfo.botToken)
                    .setToken("MjQ4NjYzNjY5NDI3MjczNzMw.CxwOTA.gaiXre2CqAHD0T9qK92-3OVib4w")
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
    public static void handleCommand(CommandParser.CommandContainer cmd) {

        System.out.println("Server is handling a command");

        if (commands.containsKey(cmd.invoke)) {

            Command command = commands.get(cmd.invoke);

            //check to see if either the user is logged in, or one of the three login commands. This is to save checking in each command whether they are logged in
            if (cmd.invoke.equals("login") || cmd.invoke.equals("register") || cmd.invoke.equals("logoff") || checkLoggedIn(cmd.event.getAuthor().getId())) {

                boolean activate = command.isCalled(cmd.args, cmd.event);

                if (activate) {
                    System.out.println("Command is SAFE");
                    command.doAction(cmd.args, cmd.event);
                    command.endCommand(activate, cmd.event);
                } else {
                    System.out.println("Command is UNSAFE");
                    command.endCommand(activate, cmd.event);
                }

            }


        } else {

            System.out.println("Invalid command");
            botSay("Invalid Command", cmd.event.getChannel());

        }

    }


    public static void botTell(String msg, User user) {
        user.getChannel().sendMessage(msg).queue();
    }

    public static void botSay(String msg, MessageChannel channel) {
        //send message to channel
        channel.sendMessage(msg).queue();
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

}
