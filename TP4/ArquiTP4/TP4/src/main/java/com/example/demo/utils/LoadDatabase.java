package com.example.demo.utils;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    private static final String log = null;

	@Bean
    CommandLineRunner initDatabase(@Qualifier("personRepository") ClientRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Client((long) 1234,"Seba", "Perez")));
            log.info("Preloading " + repository.save(new Client((long) 2345, "Juan", "Dominguez")));
        };
    }
}