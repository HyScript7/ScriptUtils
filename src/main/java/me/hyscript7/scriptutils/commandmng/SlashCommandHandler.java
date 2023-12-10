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
    private final ExecutorService executorService;

    @Autowired
    public SlashCommandHandler(List<SlashCommand> slashCommands) {
        this.commands = new HashMap<>(slashCommands.size());
        this.executorService = Executors.newCachedThreadPool();

        // Cache commands
        for (SlashCommand c :
                slashCommands) {
            String name = c.getCommandData().getName();
            commands.put(name, c);
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.isAcknowledged()) {
            return;
        }
        executorService.submit(() -> executeCommand(event));
    }

    private void executeCommand(SlashCommandInteractionEvent event) {
        String commandName = event.getName().toLowerCase();

        // Check if the command is registered
        SlashCommand command = commands.get(commandName);
        if (command == null) {
            logger.info("Attempted to execute an unknown command!");
            event.reply("Unknown command").setEphemeral(true).queue();
            return;
        }
        logger.info("Executing command " + commandName + "!");
        command.execute(event);
    }

    // Make sure we shut down the executor service when the app shuts down
    public void shutdown() {
        logger.info("Shutting down executor service!");
        executorService.shutdown();
    }

    // Hook into ctrl + c and other shutdown events
    @PreDestroy
    public void onShutdown() {
        shutdown();
    }
}
