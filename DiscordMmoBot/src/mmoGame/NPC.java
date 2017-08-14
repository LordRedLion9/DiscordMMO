/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoGame;

import mmoServer.ServerMain;

/**
 *
 * @author PC
 */
public class NPC extends Character{
    
    private String NPCID;
    
    public NPC(String name, String sex) {
        super(name, sex);
        setNPCID("NPC" + (ServerMain.getGame().NPCs.size() + 1));
        ServerMain.getGame().NPCs.add(this);
    }

    public String getNPCID() {
        return NPCID;
    }
    public void setNPCID(String charID) {
        this.NPCID = charID;
    }
}
