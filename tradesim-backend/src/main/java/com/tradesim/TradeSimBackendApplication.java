package com.tradesim;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.tradesim.model.User;
import com.tradesim.repository.UserRepository;

@SpringBootApplication
public class TradeSimBackendApplication {

	public static void main(String[] args) {
        SpringApplication.run(TradeSimBackendApplication.class, args);
    }

    // This runs automatically when the server starts
    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User user = new User("TraderJoe", 10000.00); // Give him $10k
                userRepository.save(user);
                System.out.println("✅ Created Default User: TraderJoe (ID: 1) with $10,000");
            }
        };
    }

}
