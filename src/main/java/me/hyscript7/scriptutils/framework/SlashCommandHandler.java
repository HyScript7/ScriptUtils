package me.hyscript7.scriptutils.framework;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SlashCommandHandler extends ListenerAdapter {

    private final List<SlashCommand> slashCommands;
    private boolean commandsSynced = false;

    @Autowired
    public SlashCommandHandler(List<SlashCommand> slashCommands) {
        this.slashCommands = slashCommands;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        if (commandsSynced) {
            return;
        }
        // Register slash commands with Discord API
        CommandListUpdateAction commandListUpdateAction = event.getJDA().updateCommands();

        for (SlashCommand command : slashCommands) {
            commandListUpdateAction = commandListUpdateAction.addCommands(command.getCommandData());
        }

        commandListUpdateAction.queue();
        commandsSynced = true;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String commandName = event.getName().toLowerCase();

        // Check if the command is registered
        for (SlashCommand command : slashCommands) {
            if (commandName.equals(command.getCommandData().getName())) {
                command.execute(event);
                return;
            }
        }

        event.reply("Unknown command").setEphemeral(true).queue();
    }
}
