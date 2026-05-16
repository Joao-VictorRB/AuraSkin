package com.AuraSkin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.AuraSkin.entity.Client;
import com.AuraSkin.repository.ClientRepository;

@SpringBootApplication
public class AuraSkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuraSkinApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ClientRepository repository) {
        return args -> {

            /* Client client = new Client();
            Client client2 = new Client();

            client.setName("João Victor");
            client.setEmail("joaovictor@gmail.com");
            client.setPhone("11999999999");

            client2.setName("Marnes");
            client2.setEmail("marnes@gmail.com");
            client2.setPhone("11988888888");
            repository.save(client);
            repository.save(client2);

            System.out.println("Cliente salvo!"); */
        };
    }
}
