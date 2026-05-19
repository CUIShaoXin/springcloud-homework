package com.example.userservice.controller;

import com.example.userservice.client.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    private final UserClient userClient;

    public OrderController(UserClient userClient) {
        this.userClient = userClient;
    }
    @GetMapping("/order/hello")
    public String hello() {
        return "order-service 启动成功";
    }
    @GetMapping("/order/{id}")
    public Map<String, Object> getOrderById(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> order = new HashMap<>();
        order.put("orderId", id);
        order.put("productName", "测试商品");
        order.put("price", 99.9);
        order.put("userId", 1);

        Map<String, Object> user;

        try {
            user = userClient.getUserById(1L);
        } catch (Exception e) {
            user = new HashMap<>();
            user.put("id", 1);
            user.put("name", "默认用户");
            user.put("level", "普通用户");
            user.put("message", "user-service 暂时不可用，已返回默认用户信息");
        }

        result.put("order", order);
        result.put("user", user);

        return result;
    }

}