package com.mybot.latte_bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface MCommand {
    String getName();
    String getDescription();
    void executeMsg(MessageReceivedEvent event);
}
