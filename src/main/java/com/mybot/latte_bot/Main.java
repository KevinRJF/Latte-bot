package com.mybot.latte_bot;

import com.mybot.latte_bot.config.BotConfig;
import com.mybot.latte_bot.listeners.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static JDA jda;

    public static void main(String[] args) {
        String botToken = BotConfig.getBotToken();

        if(botToken == null || botToken.isEmpty()) {
            LOGGER.error("Bot token not found in config.properties.");
            return;
        }

        try {
            jda = JDABuilder.createDefault(botToken)
                    .enableIntents(EnumSet.allOf(GatewayIntent.class))
                    .addEventListeners(new CommandListener())
                    .build();
            jda.awaitReady();
            LOGGER.info("Latte va sobre");

            registerSlashCommands();

        } catch (Exception e) {
            LOGGER.error("Error al despertar a Latte: ", e);
        }
    }

    private static void registerSlashCommands() {
        if (jda == null) {
            LOGGER.error("JDA instance is not initialized. Can't register slash commands.");
            return;
        }

        LOGGER.info("Registering slash commands...");
        jda.updateCommands()
                .addCommands(
                        Commands.slash("ping", "Mira la latencia del gateway de Discord."),
                        Commands.slash("info", "Te hablo un poco acerca de mi."),
                        Commands.slash("echo", "Respondo con tu mensaje.")
                            .addOption(OptionType.STRING, "texto", "Lo que voy a decir")
                ).queue(success -> LOGGER.info("Se registraron bien {} comandos.", success.size()),
                        failure -> LOGGER.error("Fallé en registrar los comandos: ", failure));
    }
}