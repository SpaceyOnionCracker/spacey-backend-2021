package com.heroku.spacey.dao;

import com.heroku.spacey.dto.order.OrderHistoryDto;

import java.sql.Date;
import java.util.List;

public interface OrderHistoryDao {
    List<OrderHistoryDto> getUserOrders(Long userId, Date date);
}
