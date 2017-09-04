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
    private static int ID = 0; //Thought having an incrementing ID number would be a bit better.

    //list of behaviours object (use anonymous implementations?)
    //update can iterate through list of behaviours, running checkActive() to see if it will activate (such as a timer) then activate the behaviour (activate())

    //Might have separate lists for behaviours that rely on time, and ones that don't (such as behaviours triggered by a player?)
    List<NPCBehaviour> behaviours = new ArrayList<>();

    public NPC(String name, String sex) {
        super(name, sex);
        NPCID = name + (++ID);
        GameMain.NPCs.add(this);

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

    public boolean moveCharacter(String locName){

        Exit exit = currentLocation.getExit(locName);
        if (exit == null) {
            System.out.println("Exit not available, move cancelled");
            return false;
        }

        Location newLoc = exit.getOtherLocation(currentLocation);
        currentLocation.removeNPC(this);
        newLoc.addNPC(this);
        currentLocation = newLoc;
        return true;
    }

    public String getNPCID() {
        return NPCID;
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
