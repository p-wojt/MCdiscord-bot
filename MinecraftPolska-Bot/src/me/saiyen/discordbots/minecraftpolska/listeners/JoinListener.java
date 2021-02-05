package me.saiyen.discordbots.minecraftpolska.listeners;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class JoinListener extends ListenerAdapter {

    public static MessageChannel welcomeChannel;

    private final String []joinMessages = {"Witamy na naszym serwerze nowego członka ", "Siemasz ", "Siemanko ",
    "Hejka ", "Witamy na Minecraft Polska "};



    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        if(welcomeChannel != null){
            User user = event.getUser();
            welcomeChannel.sendMessage(joinMessages[(int) (Math.random()*joinMessages.length)] + user.getAsMention() + "!").queue();
        }
    }
}
