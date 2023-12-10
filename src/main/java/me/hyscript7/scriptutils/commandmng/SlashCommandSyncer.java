package me.hyscript7.scriptutils.commandmng;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlashCommandSyncer extends ListenerAdapter {

    private final List<SlashCommand> slashCommands;
    private boolean commandsSynced = false;

    @Autowired
    public SlashCommandSyncer(List<SlashCommand> slashCommands) {
        this.slashCommands = slashCommands;
    }

    @Override
    public void onReady(ReadyEvent event) {
        if (!commandsSynced) {
            commandsSynced = true;
            syncCommands(event);
        }
    }

    private List<List<SlashCommand>> partitionList(List<SlashCommand> list) {
        List<List<SlashCommand>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += 100) {
            int end = Math.min(i + 100, list.size());
            partitions.add(list.subList(i, end));
        }
        return partitions;
    }

    private void syncCommands(ReadyEvent event) {
        for (List<SlashCommand> partition :
                partitionList(slashCommands)) {
            CommandListUpdateAction commandListUpdateAction = event.getJDA().updateCommands();
            for (SlashCommand command : partition) {
                try {
                    commandListUpdateAction = commandListUpdateAction.addCommands(command.getCommandData());
                } catch (Exception e) {
                    commandsSynced = false;
                    throw e;
                }
            }
            commandListUpdateAction.queue();
        }
    }
}
