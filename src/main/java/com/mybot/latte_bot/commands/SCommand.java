package com.mybot.latte_bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SCommand {
    String getName();
    String getDescription();
    void executeSlash(SlashCommandInteractionEvent event);
}
