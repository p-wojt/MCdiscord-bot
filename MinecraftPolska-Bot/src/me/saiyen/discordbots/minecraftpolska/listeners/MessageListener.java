package me.saiyen.discordbots.minecraftpolska.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class MessageListener extends ListenerAdapter {

    public static String prefix = "mp ";

    private final String []badWords = {"donuts"};
    private final String []inform = {"Brzydko, brzydko ", "Nie przeklinamy ", "Nie używamy wulgaryzmów "};
    private final String []immuneIDs = {"395653949052420118"};

    private Message message;



    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        message = event.getMessage();
        String msg = message.getContentDisplay();
        if(Arrays.stream(immuneIDs).noneMatch(e -> e.equals(message.getAuthor().getId()))){
            if(Arrays.stream(badWords).anyMatch(e -> msg.contains(e.toLowerCase()))){
                message.delete().queue();
                channel.sendMessage(inform[(int) (Math.random()*inform.length)] + event.getAuthor().getAsMention() + "!").queue();
            }
        }else{
            if(msg.contains(prefix)){
                String []command = msg.split(" ");
                if(command[1].equalsIgnoreCase("welcomeChannel") && command.length == 3){
                    try{
                        TextChannel textChannel = event.getGuild().getTextChannelById(command[2]);
                        if(textChannel != null){
                            JoinListener.welcomeChannel = textChannel;
                            channel.sendMessage("Kanał pomyślnie ustawiony!").queue();
                        }
                    }catch (NumberFormatException exception){
                        channel.sendMessage("Niepoprawne id kanału!").queue();
                    }

                }
            }
        }
    }
}
