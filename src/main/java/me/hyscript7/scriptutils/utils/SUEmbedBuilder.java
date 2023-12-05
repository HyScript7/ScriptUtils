package me.hyscript7.scriptutils.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.time.LocalDateTime;

public class SUEmbedBuilder extends EmbedBuilder {
    public SUEmbedBuilder(String title, String description, String thumbnail) {
        this.setTitle(title);
        this.setDescription(description);
        this.setThumbnail(thumbnail);
        this.setTimestamp(LocalDateTime.now());
        this.setAuthor("ScriptUtils");
        this.setColor(0xfc6d00);
    }
    public SUEmbedBuilder setBlame(User user, boolean setThumbnail) {
        this.setFooter(user.getEffectiveName() + " (" + user.getId() + ")");
        if (setThumbnail) {
            this.setThumbnail(user.getAvatarUrl());
        }
        return this;
    }
}
