package me.hyscript7.scriptutils;

import me.hyscript7.scriptutils.modules.core.listeners.ReadyListener;
import me.hyscript7.scriptutils.framework.SlashCommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DiscordConfig {

    private final List<ListenerAdapter> listeners;

    private final Logger logger = LoggerFactory.getLogger(ReadyListener.class);

    public DiscordConfig(List<ListenerAdapter> listeners) {
        this.listeners = listeners;
    }

    @Value("${bot.token}")
    private String botToken;

    @Bean
    public JDA jdabuilder(SlashCommandHandler slashCommandHandler) {
        JDABuilder builder = JDABuilder.createDefault(botToken);

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        // Register event listeners
        listeners.forEach(builder::addEventListeners);

        // Register slash command handler
        builder.addEventListeners(slashCommandHandler);

        return builder.build();
    }
}
