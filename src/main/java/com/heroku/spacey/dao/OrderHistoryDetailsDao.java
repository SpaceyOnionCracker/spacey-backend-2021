package com.heroku.spacey.dao;

import com.heroku.spacey.dto.order.OrderHistoryDetailsDto;

import java.sql.SQLException;

public interface OrderHistoryDetailsDao {

    OrderHistoryDetailsDto getOrderDetails(Long orderId, Long userId) throws SQLException;
}
