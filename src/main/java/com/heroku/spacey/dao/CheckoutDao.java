package com.heroku.spacey.dao;

import com.heroku.spacey.dto.order.CheckoutDto;
import com.heroku.spacey.dto.product.ProductCheckoutDto;

import java.util.List;
import java.sql.Timestamp;

public interface CheckoutDao {

    List<ProductCheckoutDto> getProductsFromCartByUserId(Long userId);

    CheckoutDto getCheckoutInfoByUserId(Long userId);

    CheckoutDto getAuctionCheckoutByAuctionId(Long auctionId, Long userId);

    Long countActiveCouriers();

    Long countOrdersForTimeSlot(Timestamp timeSlot);
}
