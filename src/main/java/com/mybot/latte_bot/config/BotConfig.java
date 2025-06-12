package com.mybot.latte_bot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BotConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotConfig.class);
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try(InputStream input = BotConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if(input == null) {
                LOGGER.error("Configuration file {} not found", CONFIG_FILE);
            }
            else {
                PROPERTIES.load(input);
                LOGGER.info("Configuration loaded from {}", CONFIG_FILE);
            }
        } catch(IOException e) {
            LOGGER.error("Error loading configuration file: ", e);
        }
    }

    public static String getBotToken() {
        return PROPERTIES.getProperty("botToken");
    }
}