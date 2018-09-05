package com.airlenet.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RequestMapping("/dashboard")
@RestController
public class DashboardController {


    @RequestMapping("/message")
    public Map<String, Object> dashboard() {
        return Collections.<String, Object> singletonMap("message", "Yay!");
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }


    @Controller
    public static class LoginErrors {

        @RequestMapping("/dashboard/login")
        public String dashboard() {
            return "redirect:/#/";
        }

    }
}
