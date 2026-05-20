package com.example.userservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.userservice.client.UserClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderUserService {

    private final UserClient userClient;

    public OrderUserService(UserClient userClient) {
        this.userClient = userClient;
    }

    @SentinelResource(
            value = "query-user",
            blockHandler = "queryUserBlockHandler",
            fallback = "queryUserFallback"
    )
    public Map<String, Object> queryUser(Long userId) {
        return userClient.getUserById(userId);
    }

    public Map<String, Object> queryUserBlockHandler(Long userId, BlockException e) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", userId);
        user.put("name", "限流默认用户");
        user.put("level", "普通用户");
        user.put("message", "请求过于频繁，已触发 Sentinel 限流");
        return user;
    }

    public Map<String, Object> queryUserFallback(Long userId, Throwable e) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", userId);
        user.put("name", "异常默认用户");
        user.put("level", "普通用户");
        user.put("message", "调用 user-service 异常，已触发 Sentinel 降级兜底");

        user.put("errorType", e.getClass().getName());
        user.put("errorMessage", e.getMessage());

        return user;
    }
}