package com.mybot.latte_bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;

public class InfoCommand implements SCommand {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Displays information about the bot.";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Latte information");
        embedBuilder.setDescription("This is a simple Discord bot created for entertainment purposes.");
        embedBuilder.setColor(new Color(0, 191, 255));
        embedBuilder.addField("Author", "KevinRJF", false);
        embedBuilder.addField("Language", "Java", true);
        embedBuilder.addField("Library", "JDA", true);
        embedBuilder.setFooter("Created with <3");

        MessageEmbed embed = embedBuilder.build();
        event.replyEmbeds(embed).queue();
    }
}
