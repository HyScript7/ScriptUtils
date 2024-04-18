package me.hyscript7.scriptutils.managers.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface SlashCommand {
    void execute(SlashCommandInteractionEvent event);

    CommandData getCommandData();
}
