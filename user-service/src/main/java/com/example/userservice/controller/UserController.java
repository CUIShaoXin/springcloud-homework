package com.example.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class UserController {

    @Value("${user.name:未配置}")
    private String userName;

    @Value("${user.level:未配置}")
    private String userLevel;

    @GetMapping("/user/config")
    public Map<String, String> getConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("user.name", userName);
        map.put("user.level", userLevel);
        return map;
    }

    @GetMapping("/user/hello")
    public String hello() {
        return "user-service 启动成功";
    }

    @GetMapping("/user/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "张三");
        user.put("level", "VIP1");
        user.put("phone", "13800000000");
        return user;
    }
}