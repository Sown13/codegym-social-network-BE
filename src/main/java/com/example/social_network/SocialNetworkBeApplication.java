package com.example.social_network;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Transactional
public class SocialNetworkBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkBeApplication.class, args);
    }

}
