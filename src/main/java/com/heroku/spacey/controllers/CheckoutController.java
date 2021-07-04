package com.heroku.spacey.controllers;

import com.heroku.spacey.dto.order.CheckoutDto;
import com.heroku.spacey.entity.Timeslots;
import com.heroku.spacey.services.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CheckoutController {

    private final CheckoutService checkoutService;


    @Secured("ROLE_USER")
    @GetMapping("/checkout")
    public CheckoutDto getCheckout() {
        return checkoutService.getCheckout();
    }

    @Secured("ROLE_USER")
    @GetMapping("/auction-checkout/{auctionId}")
    public CheckoutDto getAuctionCheckout(@PathVariable Long auctionId) {
        return checkoutService.getAuctionCheckout(auctionId);
    }

    @Secured("ROLE_USER")
    @GetMapping("/timeslots")
    public Timeslots getAvailableTimeSlots(@RequestBody Timeslots date) {
        return checkoutService.getAvailableTimeslots(date);
    }
}
