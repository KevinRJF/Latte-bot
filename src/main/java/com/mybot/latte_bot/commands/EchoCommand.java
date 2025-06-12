package com.mybot.latte_bot.commands;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class EchoCommand implements SCommand {
    @Override
    public String getName() {
        return "echo";
    }

    @Override
    public String getDescription() {
        return "Echoes back the text you provide.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event) {
        OptionMapping textOption = event.getOption("text");
        String textToEcho = "";
        if(textOption != null) {
            textToEcho = textOption.getAsString();
        }
        else {
            textToEcho = "ideay no me dijiste nada";
        }

        event.reply(textToEcho).setEphemeral(false).queue();
    }
}
