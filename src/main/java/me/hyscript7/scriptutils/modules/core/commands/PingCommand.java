package me.hyscript7.scriptutils.modules.core.commands;

import me.hyscript7.scriptutils.framework.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.stereotype.Component;

@Component
public class PingCommand implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.isAcknowledged()) {
            return;
        }
        event.reply("Ping!").queue();
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("ping", "Ping pong!");
    }
}
