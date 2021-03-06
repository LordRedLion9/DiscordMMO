package mmoServer;

import java.util.List;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.*;
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        ServerMain.log("Bot logged on as " + event.getJDA().getSelfUser().getName());
        List<Guild> guilds = event.getJDA().getGuilds();

        for (Guild g : guilds) {
            //g.getPublicChannel().sendMessage("MMOBOT Activated: Ready to serve!").queue();
        }

    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        JDA listenerJda = event.getJDA();

        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();

        String messageContent = message.getContent();
        String userID = event.getAuthor().getId();
        User user = ServerMain.getUser(userID);

        if (event.getAuthor().isBot() & !event.getAuthor().getName().equals("ZBot")) {
            //do nothing
        } else if (messageContent.startsWith("~!")) {
            /*if (event.getChannel() instanceof PrivateChannel) {
                if (user != null) {
                    ServerMain.getUser(userID).setChannel((PrivateChannel) channel);
                    System.out.println("Set users private channel");
                }
            }*/
            ServerMain.handleCommand(ServerMain.parser.parse(messageContent, event));
        }

    }

}
