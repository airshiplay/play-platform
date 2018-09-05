package com.airlenet.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableConfigServer
public class PlayConfigCenterRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayConfigCenterRestApplication.class, args);
    }
}
