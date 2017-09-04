package mmoGame;

import mmoServer.ServerMain;

public class PlayerCharacter extends Character {
    
    private String playerID;

    public PlayerCharacter(String name, String sex){
        super(name, sex);
        
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

    public void moveCharacter(String locName){
        Exit exit = currentLocation.getExit(locName);
        if (exit == null) {
            System.out.println("Exit not available, move cancelled");
            return;
        }

        Location newLoc = exit.getOtherLocation(currentLocation);
        currentLocation.removePlayer(this);
        newLoc.addPlayer(this);
        currentLocation = newLoc;

    }

}
