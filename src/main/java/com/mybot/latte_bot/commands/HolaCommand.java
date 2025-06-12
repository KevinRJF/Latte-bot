package com.mybot.latte_bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HolaCommand implements MCommand {

    @Override
    public String getName() {
        return "hola";
    }

    @Override
    public String getDescription() {
        return "te saludo";
    }

    @Override
    public void executeMsg(MessageReceivedEvent event) {

        String msg = event.getMessage().getContentRaw().toLowerCase();

        if(msg.contains("hola")) {
            event.getMessage().reply("https://tenor.com/boioN.gif").queue();
        }
    }
}
