package me.hyscript7.scriptutils.commandmng;

import jakarta.annotation.PreDestroy;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SlashCommandHandler extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(SlashCommandHandler.class);

    private final Map<String, SlashCommand> commands;
    private final ExecutorService commandExecutor;

    @Autowired
    public SlashCommandHandler(List<SlashCommand> slashCommands) {
        this.commands = new HashMap<>(slashCommands.size());
        this.commandExecutor = Executors.newCachedThreadPool();
        logger.debug("command executor thread pool created");

        // Cache commands
        for (SlashCommand c :
                slashCommands) {
            String name = c.getCommandData().getName();
            commands.put(name, c);
        }
        logger.debug("Command map has been built successfully");
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.isAcknowledged()) {
            return;
        }

        // Find the corresponding command
        String commandName = event.getName().toLowerCase();
        SlashCommand command = commands.get(commandName);

        if (command == null) {
            logger.warn("Unknown command: {}", commandName);
            event.reply("Unknown command").setEphemeral(true).queue();
            return;
        }

        // Execute the command asynchronously
        commandExecutor.submit(() -> executeCommand(command, event));
    }

    private void executeCommand(SlashCommand command, SlashCommandInteractionEvent event) {
        try {
            event.deferReply().queue();

            logger.info("Executing command '{}' for user '{}' on thread {}", command.getCommandData().getName(), event.getUser().getName(), Thread.currentThread().getName());

            command.execute(event);

            logger.info("Command execution completed for '{}'", event.getName());
        } catch (Exception e) {
            logger.error("An error occurred while processing the command", e);
            event.reply("An error occurred while processing the command.").setEphemeral(true).queue();
        }
    }

    // Make sure to shut down the commandExecutor when the application is stopping
    public void shutdown() {
        logger.debug("Shutting down command executor");
        commandExecutor.shutdown();
    }

    // Hook into ctrl + c and other shutdown scenarios
    @PreDestroy
    public void onShutdown() {
        shutdown();
    }
}
