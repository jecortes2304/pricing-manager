package com.cortestudios.pricingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class PricingManagerApplication {

    public static void main(String[] args) {
        loadEnv();
        SpringApplication.run(PricingManagerApplication.class, args);
    }

    private static void loadEnv() {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> {
            if (entry.getKey().startsWith("PM_")){
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }

}
