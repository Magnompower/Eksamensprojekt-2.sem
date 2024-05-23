package com.example.eksamensprojektbilabonnement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EksamensprojektBilabonnementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EksamensprojektBilabonnementApplication.class, args);
    }

}
