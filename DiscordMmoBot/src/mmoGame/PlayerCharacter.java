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
	
}
