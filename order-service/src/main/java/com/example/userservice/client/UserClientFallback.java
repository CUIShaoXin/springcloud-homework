package com.example.userservice.client;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public Map<String, Object> getUserById(Long id) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "默认用户");
        user.put("level", "普通用户");
        user.put("phone", "暂无");
        user.put("message", "user-service 不可用，已返回 Sentinel 兜底结果");
        return user;
    }
}