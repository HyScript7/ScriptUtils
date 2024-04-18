package me.hyscript7.scriptutils.modules.core.commands;

import me.hyscript7.scriptutils.managers.commands.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.stereotype.Component;

@Component
public class PingCommand implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.getHook().editOriginal("[Pong](https://hooriahic.github.io/pingponggame/)!").queue();
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("ping", "Ping pong!");
    }
}
