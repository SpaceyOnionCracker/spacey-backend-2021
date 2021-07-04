package com.heroku.spacey.controllers;

import com.heroku.spacey.dto.order.OrderHistoryDetailsDto;
import com.heroku.spacey.services.OrderHistoryDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-history")
public class OrderHistoryDetailsController {

    private final OrderHistoryDetailsService orderHistoryDetailsService;

    @Secured("ROLE_USER")
    @GetMapping("/{orderId}")
    public OrderHistoryDetailsDto getOrderDetailsById(@PathVariable Long orderId) throws SQLException {
        return orderHistoryDetailsService.getOrderDetails(orderId);
    }
}
