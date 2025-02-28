package com.example.loantrack;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoantrackApplication {

    public static void main(String[] args) {
        try {
            // Try loading from /app first (Docker environment)
            Dotenv dotenv = Dotenv.configure().directory("/app").load();
            dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        } catch (Exception e) {
            try {
                // If that fails, try loading from the current directory (local environment)
                Dotenv dotenv = Dotenv.configure().load();
                dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
            } catch (Exception innerEx) {
                // Log that .env file couldn't be loaded, but continue startup
                System.out.println("Warning: Could not load .env file. Continuing with environment variables.");
            }
        }
        //Dotenv dotenv = Dotenv.configure().directory("/app").load();
        //dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(LoantrackApplication.class, args);
    }

}
