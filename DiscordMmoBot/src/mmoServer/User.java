package mmoServer;

import mmoGame.PlayerCharacter;
import net.dv8tion.jda.core.entities.*;

public class User implements java.io.Serializable{

    private String name;
    private String ID;
    private boolean loggedIn;
    private boolean admin;
    private PrivateChannel channel;
    


    PlayerCharacter playerChar = null;

    public User(String name, String ID) {

        this.name = name;
        this.ID = ID;
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

    public PrivateChannel getChannel() {
        return channel;
    }

    public void setChannel(PrivateChannel channel) {
        this.channel = channel;
    }

}
