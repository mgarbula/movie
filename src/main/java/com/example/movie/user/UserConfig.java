package com.example.movie.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository repository) {
        return args -> {
            User miko = new User(
                    "mikos",
                    "mikos442@gmail.com",
                    "pupsko"
            );
            User kupa = new User(
                    "kupa",
                    "kupa@gmail.com",
                    "123pass"
            );
            repository.saveAll(List.of(miko, kupa));
        };
    }
}
