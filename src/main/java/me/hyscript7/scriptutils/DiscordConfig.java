package me.hyscript7.scriptutils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;
import java.util.List;

@Configuration
public class DiscordConfig {

    private final List<ListenerAdapter> listeners;
    @Value("${bot.token}")
    private String botToken;

    public DiscordConfig(List<ListenerAdapter> listeners) {
        this.listeners = listeners;
    }

    @Bean
    public JDABuilder jdabuilder() {
        JDABuilder builder = JDABuilder.createDefault(botToken);

        builder.enableIntents(EnumSet.allOf(GatewayIntent.class)); // TODO: Specify only truly required intents in release version

        // Register event listeners
        listeners.forEach(builder::addEventListeners);

        return builder;
    }

    @Bean
    public JDA jda(JDABuilder jdabuilder) {
        try {
            return jdabuilder.build(); // Run the bot
        } catch (InvalidTokenException e) {
            throw new RuntimeException("Could not authenticate!", e);
        }
    }
}
