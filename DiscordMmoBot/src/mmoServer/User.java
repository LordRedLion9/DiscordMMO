package mmoServer;

import mmoGame.PlayerCharacter;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.requests.restaction.pagination.MessagePaginationAction;

public class User implements java.io.Serializable{

    private String name;
    private String ID;
    private boolean loggedIn;
    private boolean admin;

    private PrivateChannel privateChannel;
    private MessageChannel publicChannel;

    public boolean messageToPublic = false;
    


    PlayerCharacter playerChar = null;

    public User(String name, String ID, PrivateChannel channel) {


        this.name = name;
        this.ID = ID;
        this.privateChannel = channel;
        for (String s : Constants.Admins) {
            if (s.equals(ID)) {
                this.admin = true;
            }
        }

    }

    public void setChar(PlayerCharacter charac) {
        playerChar = charac;
    }

    public PlayerCharacter getChar(){
        return playerChar;
    }

    public void setLoginStatus(boolean loginStatus) {
        loggedIn = loginStatus;
    }

    public boolean getLoginStatus() {
        return loggedIn;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    public MessageChannel getPublicChannel() {
        return publicChannel;
    }

    public PrivateChannel getPrivateChannel() {
        return privateChannel;
    }

    public void setPrivateChannel(PrivateChannel channel) {
        this.privateChannel = channel;
    }

    public void setPublicChannel(MessageChannel channel) {
        this.publicChannel = channel;
    }

    public void sendMsg(String msg) { //say to whatever channel is selected

        if (messageToPublic){
            ServerMain.botSay(msg, getPublicChannel());
        }else{
            ServerMain.botTell(msg, this);
        }

    }

    public void sendPrivateMsg(String msg) { //send specifically to private messaging
        ServerMain.botTell(msg, this);
    }

}
