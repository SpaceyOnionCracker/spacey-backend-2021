package com.heroku.spacey.controllers;

import com.heroku.spacey.dto.order.OrderHistoryDto;
import com.heroku.spacey.services.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-history")
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @Secured("ROLE_USER")
    @GetMapping("/orders")
    public List<OrderHistoryDto> getOrders(
            @RequestParam(required = false) Date date) {
        return orderHistoryService.getUserOrders(date);
    }
}
