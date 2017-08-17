/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoGame;

import mmoServer.ServerMain;
import java.util.*;

/**
 *
 * @author PC
 */
public class NPC extends Character{
    
    private String NPCID;
    private static int ID = 0;

    //list of behaviours object (use anonymous implementations?)
    //on update can iterate through list of behaviours, checking time? if time is correct then activate the behaviour (methods inside behaviour)

    List<NPCBehaviour> behaviours = new ArrayList<NPCBehaviour>();

    public NPC(String name, String sex) {
        super(name, sex);
        NPCID = name + (++ID);
        ServerMain.getGame().NPCs.add(this);
    }

    void update(){
        for (NPCBehaviour b : behaviours){
           if (b.checkActivate()){
               b.activate();
           }
        }
    }

    public String getNPCID() {
        return NPCID;
    }
    public void setNPCID(String charID) {
        this.NPCID = charID;
    }


    /*
    NPCBehaviour behaviour1 = new NPCBehaviour() {
        @Override
        public void hello() {
            System.out.println("Hello 1");
        }
    };

    NPCBehaviour behaviour2 = new NPCBehaviour() {
        @Override
        public void hello() {
            System.out.println("Hello 2");

        }
    };*/

    interface NPCBehaviour{

        boolean checkActivate();
        void activate();

    }

}
