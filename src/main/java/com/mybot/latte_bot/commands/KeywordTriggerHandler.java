package com.mybot.latte_bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class KeywordTriggerHandler {
    public void handle(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw().toLowerCase();

        if(msg.contains("pato")) {
            event.getMessage().reply("https://tenor.com/boioN.gif").queue();
        }
    }
}
