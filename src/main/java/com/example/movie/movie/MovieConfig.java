package com.example.movie.movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JUNE;
import static java.time.Month.MAY;

@Configuration
public class MovieConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository repository) {
        return args -> {
            Movie pulp = new Movie(
                    "Pulp Fiction",
                    "Quentin Tarantino",
                    LocalDate.of(1995, MAY, 19)
            );

            Movie full = new Movie(
                    "Full Metal Jacket",
                    "Stanley Kubrick",
                    LocalDate.of(1987, JUNE, 26)
            );

            pulp.setForeignKey(2L);
            full.setForeignKey(2L);

            repository.saveAll(
                    List.of(pulp, full)
            );
        };
    }
}
