package com.ra5.projecte3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing  //per a q es crei sola la data dataCreated
public class Projecte3Application {

    public static void main(String[] args) {
        SpringApplication.run(Projecte3Application.class, args);
    }

}
