package com.heroku.spacey.services.impl;

import com.heroku.spacey.dao.CheckoutDao;
import com.heroku.spacey.dto.order.CheckoutDto;
import com.heroku.spacey.dto.product.ProductCheckoutDto;
import com.heroku.spacey.entity.Timeslots;
import com.heroku.spacey.services.CheckoutService;
import com.heroku.spacey.utils.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutDao checkoutDao;
    private final SecurityUtils securityUtils;


    @Override
    public CheckoutDto getCheckout() {
        Long userId = securityUtils.getUserIdByToken();
        CheckoutDto checkoutDto = checkoutDao.getCheckoutInfoByUserId(userId);

        List<ProductCheckoutDto> products = checkoutDao.getProductsFromCartByUserId(userId);
        checkoutDto.getProducts().addAll(products);

        return checkoutDto;
    }

    @Override
    public CheckoutDto getAuctionCheckout(Long auctionId) {
        Long userId = securityUtils.getUserIdByToken();

        return checkoutDao.getAuctionCheckoutByAuctionId(auctionId, userId);
    }

    @Override
    public List<Timestamp> getAvailableTimeslots(Timeslots timeslots) {
        Date date = timeslots.getDate();
        List<Timestamp> timeSlotsList = new ArrayList<>();
        List<Timestamp> availableTimeslots = new ArrayList<>();

        for (long i = date.getTime() + 36_000_000; i <= date.getTime() + 64_800_000; i += 3_600_000) {
            timeSlotsList.add(new Timestamp(i));
        }

        for (Timestamp timeSlot : timeSlotsList) {
            Timestamp timestamp = new Timestamp(date.getTime() + timeSlot.getTime());
            Long activeCouriers = checkoutDao.countActiveCouriers();
            Long orders = checkoutDao.countOrdersForTimeSlot(timestamp);
            if (activeCouriers > orders) {
                availableTimeslots.add(timeSlot);
            }
        }
        return availableTimeslots;
    }
}
