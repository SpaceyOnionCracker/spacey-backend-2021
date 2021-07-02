package com.heroku.spacey.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.heroku.spacey.dto.order.CourierOrdersDto;
import com.heroku.spacey.services.CourierOrdersService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class CourierOrdersController {

    private final CourierOrdersService courierOrdersService;

    @Secured("ROLE_COURIER")
    @GetMapping("/orders")
    public List<CourierOrdersDto> getOrders(
            @RequestParam(required = false) Date date) {
        return courierOrdersService.getCourierOrders(date);
    }
}
