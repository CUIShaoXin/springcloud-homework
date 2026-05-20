package com.example.userservice.controller;

import com.example.userservice.service.OrderUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderUserService orderUserService;

    public OrderController(OrderUserService orderUserService) {
        this.orderUserService = orderUserService;
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

        Map<String, Object> user = orderUserService.queryUser(1L);

        result.put("order", order);
        result.put("user", user);

        return result;
    }
}