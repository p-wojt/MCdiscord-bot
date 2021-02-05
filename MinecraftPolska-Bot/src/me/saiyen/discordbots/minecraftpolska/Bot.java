package me.saiyen.discordbots.minecraftpolska;

import me.saiyen.discordbots.minecraftpolska.listeners.JoinListener;
import me.saiyen.discordbots.minecraftpolska.listeners.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    public static JDA jda;

    public static void main(String[] args) {
        JDABuilder jdaBuilder = JDABuilder.createDefault("-");
        jdaBuilder.setActivity(Activity.watching("Minecraft"));
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        try{
            jda = jdaBuilder.build();
            jda.addEventListener(new MessageListener());
            jda.addEventListener(new JoinListener());
            jda.awaitReady();
        }catch(LoginException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
