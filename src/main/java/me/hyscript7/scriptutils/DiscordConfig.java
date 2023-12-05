package me.hyscript7.scriptutils;

import me.hyscript7.scriptutils.commandmng.SlashCommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public JDABuilder jdabuilder(SlashCommandHandler slashCommandHandler) {
        JDABuilder builder = JDABuilder.createDefault(botToken);

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        // Register event listeners
        listeners.forEach(builder::addEventListeners);

        // Register slash command handler
        builder.addEventListeners(slashCommandHandler);
        return builder;
    }

    @Bean
    public JDA jda(JDABuilder jdabuilder) {
        try {
            return jdabuilder.build();
        } catch (InvalidTokenException e) {
            throw new RuntimeException("Could not authenticate!", e);
        }
    }
}
