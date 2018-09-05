package com.airlenet.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
public class PlaySsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaySsoApplication.class, args);
    }
}
