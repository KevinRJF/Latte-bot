package com.mybot.latte_bot.listeners;

import com.mybot.latte_bot.commands.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class CommandListener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandListener.class);
    private final Map<String, SCommand> SCOMMANDS = new HashMap<>();
    private final Map<String, MCommand> MCOMMANDS = new HashMap<>();

    public CommandListener() {
        SCOMMANDS.put("ping", new PingCommand());
        SCOMMANDS.put("info", new InfoCommand());
        SCOMMANDS.put("echo", new EchoCommand());
        LOGGER.info("Registered {} slash commands.", SCOMMANDS.size());
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        LOGGER.info("JDA is ready. Logged in as {}#{}",
                event.getJDA().getSelfUser().getName(),
                event.getJDA().getSelfUser().getDiscriminator());
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String commandName = event.getName();
        SCommand command = SCOMMANDS.get(commandName);

        if(command != null) {
            LOGGER.debug("Executing slash command: {} from user: {}", commandName, event.getUser().getName());
            command.executeSlash(event);
        }
        else {
            LOGGER.warn("Unknown slash command: {} from user: {}", commandName, event.getUser().getName());
            event.reply("No conozco ese comando :(").setEphemeral(true).queue();
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }

        String msg = event.getMessage().getContentRaw();
        handlePrefixCommand(event, msg);

        KeywordTriggerHandler keywordHandler = new KeywordTriggerHandler();
        keywordHandler.handle(event);
    }

    private void handlePrefixCommand(MessageReceivedEvent event, String msg) {
        String prefix = "latte ";

        if(!msg.toLowerCase().startsWith(prefix)) { return; }

        String commandName = msg.substring(prefix.length()).trim(); // removes prefix to isolate command
        MCommand command = MCOMMANDS.get(commandName.toLowerCase()); // ensures command matches case insensitive

        if (command != null) {
            LOGGER.debug("Executing msg command: {} from user: {}", msg, event.getAuthor().getName());
            command.executeMsg(event);
        } else {
            LOGGER.warn("Unknown msg command: {} from user: {}", msg, event.getAuthor().getName());
            event.getMessage().reply("No conozco ese comando :(").queue();
        }
    }
}
