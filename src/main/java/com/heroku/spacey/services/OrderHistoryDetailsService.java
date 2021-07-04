package com.heroku.spacey.services;

import com.heroku.spacey.dto.order.OrderHistoryDetailsDto;

import java.sql.SQLException;

public interface OrderHistoryDetailsService {
    OrderHistoryDetailsDto getOrderDetails(Long orderId) throws SQLException;
}
