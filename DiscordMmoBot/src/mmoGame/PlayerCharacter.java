package mmoGame;

import mmoServer.ServerMain;
import mmoServer.User;

public class PlayerCharacter extends Character {
    
    private String playerID;
    private User owningUser;

    public boolean isMuted = false;

    public PlayerCharacter(String name, String sex, User user){
        super(name, sex);
        owningUser = user;
        setPlayerID("P" + (ServerMain.getGame().Players.size() + 1));
        ServerMain.getGame().Players.add(this);
        
        this.setExp(15);//currently unused
    }
    

    public String getPlayerID() {
        return playerID;
    }
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public User getOwningUser() { return owningUser; }

    public boolean moveCharacter(String locName){
        Exit exit = currentLocation.getExit(locName);
        if (exit == null) {
            System.out.println("Exit not available, move cancelled");
            return false;
        }

        currentLocation.saytoLocation(getName() + " walks off towards the " + exit.getExitName());

        Location newLoc = exit.getOtherLocation(currentLocation);
        currentLocation.removePlayer(this);
        newLoc.addPlayer(this);
        currentLocation = newLoc;
        currentLocation.saytoLocation(getName() + " comes in from the " + exit.getExitName());

        return true;

    }

    @Override
    public void sayTo(String msg) {
        ServerMain.botTell(msg, owningUser);
    }

}
