package com.heroku.spacey.services;

import com.heroku.spacey.dto.order.CheckoutDto;
import com.heroku.spacey.entity.TimeSlotDate;

import java.sql.Timestamp;
import java.util.List;

public interface CheckoutService {

    CheckoutDto getCheckout();

    CheckoutDto getAuctionCheckout(Long auctionId);

    List<Timestamp> getAvailableTimeslots(TimeSlotDate timeSlotDate);
}
