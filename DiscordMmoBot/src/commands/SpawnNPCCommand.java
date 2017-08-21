package commands;

import mmoGame.NPC;
import mmoGame.PlayerCharacter;
import mmoServer.ServerMain;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SpawnNPCCommand implements Command {
    @Override
    public boolean isCalled(String[] args, MessageReceivedEvent event) {

        String userID = event.getAuthor().getId();

        if (!ServerMain.checkLoggedIn(userID)) {
            return false;
        }
        if (ServerMain.getUser(userID).getChar() == null) {
            return false;
        }

        if (args.length < 2) {
            ServerMain.botSay("Error: incorrect number of args. USAGE: ~!spawnnpc \"name\", gender", event.getChannel());
        }

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {

        String userID = event.getAuthor().getId();
        PlayerCharacter playerC = ServerMain.getUser(userID).getChar();

        String npcName = args[0];
        String npcGender = args[1]; //Note. Only 28 genders.

        NPC newNpc = new NPC(npcName, npcGender);
        System.out.println("Spawning " + npcName);

        ServerMain.getGame().spawnNPC(newNpc, playerC.getLocation());
        ServerMain.botSay(playerC.getName() + " opens a wormhole and "
                + npcName + " falls through onto the ground", event.getChannel());

    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
