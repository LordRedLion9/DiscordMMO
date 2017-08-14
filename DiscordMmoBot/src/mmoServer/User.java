package mmoServer;

import mmoGame.PlayerCharacter;

public class User {

    private String name;
    private String ID;
    private boolean loggedIn;
    private boolean admin;


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

    public PlayerCharacter getPlayerChar() {
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

}
