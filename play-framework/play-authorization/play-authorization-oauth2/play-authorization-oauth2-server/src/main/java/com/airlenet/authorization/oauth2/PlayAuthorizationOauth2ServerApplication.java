package com.airlenet.authorization.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
public class PlayAuthorizationOauth2ServerApplication {
    public static void main(String args[]) {
        SpringApplication.run(PlayAuthorizationOauth2ServerApplication.class, args);
    }
}
