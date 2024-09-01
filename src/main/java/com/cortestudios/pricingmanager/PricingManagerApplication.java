package com.cortestudios.pricingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * The main entry point for the Pricing Manager application.
 */
@SpringBootApplication
public class PricingManagerApplication {

    /**
     * Main method that starts the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        loadEnv();
        SpringApplication.run(PricingManagerApplication.class, args);
    }

    /**
     * Loads environment variables from the .env file and sets them as system properties.
     * Only variables that start with "PM_" are loaded.
     */
    private static void loadEnv() {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> {
            if (entry.getKey().startsWith("PM_")) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }
}
