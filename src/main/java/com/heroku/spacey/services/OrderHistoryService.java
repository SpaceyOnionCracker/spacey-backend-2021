package com.heroku.spacey.services;

import com.heroku.spacey.dto.order.OrderHistoryDto;

import java.sql.Date;
import java.util.List;

public interface OrderHistoryService {
    List<OrderHistoryDto> getUserOrders(Date date);
}
