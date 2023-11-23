package me.hyscript7.scriptutils.modules.core.commands;

import me.hyscript7.scriptutils.framework.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.stereotype.Component;

@Component
public class HelloCommand implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.isAcknowledged()) {
            return;
        }
        String name;
        OptionMapping nameOption = event.getOption("name");
        name = nameOption == null ? event.getUser().getName() : nameOption.getAsString();
        event.reply("Hello, " + name + "!").queue();
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("hello", "Say hello to the bot").addOption(OptionType.STRING, "name", "The person to greet", false);
    }
}
