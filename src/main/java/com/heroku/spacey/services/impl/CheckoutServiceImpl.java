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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private static final long ONE_HOUR = 3_600_000;
    private static final long TEN_HOURS = 36_000_000;
    private static final long SEVENTEEN_HOURS = 61_200_000;

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
    public Timeslots getAvailableTimeslots(Timeslots timeslots) {
        Date date = timeslots.getDate();
        List<Timestamp> workingHours = getWorkingHours(date);
        List<Timestamp> availableTimeslots = new ArrayList<>();

        for (Timestamp timeSlot : workingHours) {
            Timestamp timestamp = new Timestamp(date.getTime() + timeSlot.getTime());
            Long activeCouriers = checkoutDao.countActiveCouriers();
            Long orders = checkoutDao.countOrdersForTimeSlot(timestamp);
            if (activeCouriers > orders) {
                availableTimeslots.add(timeSlot);
            }
        }
        timeslots.getTimeSlots().addAll(availableTimeslots);
        return timeslots;
    }

    private List<Timestamp> getWorkingHours(Date date) {
        List<Timestamp> workingHours = new ArrayList<>();

        for (long i = date.getTime() + TEN_HOURS; i <= date.getTime() + SEVENTEEN_HOURS; i += ONE_HOUR) {
            workingHours.add(new Timestamp(i));
        }

        return workingHours;
    }
}
