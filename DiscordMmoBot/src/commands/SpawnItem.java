package commands;

import com.sun.security.ntlm.Server;
import mmoGame.Weapon;
import mmoServer.Command;
import mmoServer.ServerMain;
import mmoGame.Item;
import mmoGame.PlayerCharacter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SpawnItem implements Command {

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
            ServerMain.botSay("Error: incorrect number of args. USAGE: ~!spawn (ITEM | WEAPON) name", event.getChannel());
        }

        return true;
    }

    @Override
    public void doAction(String[] args, MessageReceivedEvent event) {


        String userID = event.getAuthor().getId();
        PlayerCharacter playerC = ServerMain.getUser(userID).getChar(); //This is lazy (it's exactly how I did mine)
        String pronoun = (playerC.getSex().equals("male")) ? "his" : "her";
        String message = "";

        Item newItem = null;

        String spawnCommand = args[0].toLowerCase();
        switch (spawnCommand) {
            case "item":
                newItem = new Item(args[1]);

                if (args.length == 3) {
                    newItem.setDesc(args[2]);
                }
                break;

            case "weapon":

                newItem = new Weapon(args[1], Integer.parseInt(args[2]), args[3]);

                if (args.length == 5) {
                    newItem.setDesc(args[4]);
                }
                break;


            default:


                ServerMain.botSay("Invalid item type. ITEM or WEAPON", event.getChannel());
                ServerMain.botSay("Item type received was: " + args[0], event.getChannel());
                return;
        }

        if (newItem != null) {
            message += playerC.getName() + " snaps " + pronoun + " fingers, and";

            switch (String.valueOf(newItem.getItemName().charAt(0)).toLowerCase()) {
                case "a":
                case "e":
                case "i":
                case "o":
                case "u":
                    message += " an ";
                    break;
                default:
                    message += " a ";
                    break;
            }
            message += newItem.getItemName() + " appears.";

            ServerMain.botSay(message, event.getChannel());
            newItem.setLocation(playerC.getLocation());
            playerC.getLocation().addItem(newItem);
        }

    }

    @Override
    public void endCommand(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
