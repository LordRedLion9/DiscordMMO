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

    //Might have separate lists for behaviours that rely on time, and ones that don't (such as behaviours triggered by a player?)
    List<NPCBehaviour> behaviours = new ArrayList<>();

    public NPC(String name, String sex) {
        super(name, sex);
        NPCID = name + (++ID);
        ServerMain.getGame().NPCs.add(this);

        behaviours.add(new NPCBehaviour() { //add generic behaviour for testing
            int timer = 0;

            @Override
            public boolean checkActivate() {

                if (timer++ == 10){
                    timer = 0;
                    return true;
                }
                return false;
            }

            @Override
            public void activate() {
                System.out.println(getNPCID() + " activated their generic behaviour");
            }
        });

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

    example for anonymous classes
    https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html

    NPCBehaviour behaviour1 = new NPCBehaviour() {
        @Override
        public boolean checkActivate() {
            return true;
        }

        public void update(){
            doAThing();
        {


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
