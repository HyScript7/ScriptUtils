package me.hyscript7.scriptutils.modules.core.listeners;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReadyListener extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(ReadyListener.class);

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA().getPresence().setPresence(OnlineStatus.ONLINE, Activity.watching(" your server"));
        logger.info("ScriptUtils Ready!");
    }
}
