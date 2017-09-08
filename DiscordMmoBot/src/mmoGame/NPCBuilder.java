package mmoGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPCBuilder {

    public enum NPCType {
        EMPTY, GENERIC, SHOPKEEP
    }


    public NPC buildNPC(NPCType type, String name, String gender){

        NPC newNpc = new NPC(name, gender);


        switch (type){

            case EMPTY:
                return newNpc;




            case GENERIC:

                newNpc.behaviours.add(new NPCBehaviour() {
                    int timer = 0;
                    Random r = new Random();

                    @Override
                    public boolean checkActivate() {

                        int timerLimit = 15;

                        if (timer++ == timerLimit) {
                            timer = 0;
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public void activate() {

                        List<String> phrases = new ArrayList<>();

                        phrases.add(newNpc.getName() + " stands around idly...");
                        phrases.add(newNpc.getName() + " whistles an idle tune.");
                        phrases.add(newNpc.getName() + " picks his nose. Yum.");

                        String msg = phrases.get(r.nextInt(phrases.size() - 1));
                        newNpc.currentLocation.saytoLocation(msg);

                    }
                });

                newNpc.reactions.add(new NPCReactBehaviour() {


                    public boolean checkActivate(String msg) {
                        return true;
                    }


                    public void activate(String msg) {

                        newNpc.currentLocation.saytoLocation(newNpc.getName() + " says: Huh? What? Im sorry, cant quite hear what you're saying there sonny.");

                    }
                });

                return  newNpc;




            default:
                return newNpc;

        }

    }


}
