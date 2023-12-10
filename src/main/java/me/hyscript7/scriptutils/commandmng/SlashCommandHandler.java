package me.hyscript7.scriptutils.commandmng;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SlashCommandHandler extends ListenerAdapter {

    private final Map<String, SlashCommand> commands;

    @Autowired
    public SlashCommandHandler(List<SlashCommand> slashCommands) {
        this.commands = new HashMap<>(slashCommands.size());
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
        executeCommand(event);
    }

    private void executeCommand(SlashCommandInteractionEvent event) {
        String commandName = event.getName().toLowerCase();

        // Check if the command is registered
        SlashCommand command = commands.get(commandName);
        if (command == null) {
            event.reply("Unknown command").setEphemeral(true).queue();
            return;
        }
        command.execute(event);
    }
}
