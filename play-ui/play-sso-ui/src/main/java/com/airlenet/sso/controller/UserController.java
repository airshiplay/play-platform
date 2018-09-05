package com.airlenet.sso.controller;

import com.airlenet.data.domain.RestResult;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("get_info")
    public RestResult getUserInfo(){
        return new UserInfo();
    }
    @PostMapping("login")
    public RestResult login(){

        return new UserInfo();
    }

    @Data
    public static class UserInfo extends  RestResult{
        private String token="ss";

        public UserInfo() {
        }
    }
}
