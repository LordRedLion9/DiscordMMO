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
    private static int ID = 0;
    
    public NPC(String name, String sex) {
        super(name, sex);
        NPCID = name + (++ID);
        ServerMain.getGame().NPCs.add(this);
    }

    public String getNPCID() {
        return NPCID;
    }
    public void setNPCID(String charID) {
        this.NPCID = charID;
    }
}
