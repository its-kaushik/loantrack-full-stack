package com.example.loantrack;
import com.example.loantrack.config.JwtProperties;
import com.example.loantrack.config.OtpProperties;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        JwtProperties.class,
        OtpProperties.class
})
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
