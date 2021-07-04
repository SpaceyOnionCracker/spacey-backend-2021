package com.heroku.spacey.services.impl;

import com.heroku.spacey.dao.OrderHistoryDao;
import com.heroku.spacey.dto.order.OrderHistoryDto;
import com.heroku.spacey.services.OrderHistoryService;
import com.heroku.spacey.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryDao orders;
    private final SecurityUtils securityUtils;


    public List<OrderHistoryDto> getUserOrders(Date date) {
        Long userId = securityUtils.getUserIdByToken();

        if (date == null) {
            return orders.getUserOrders(userId, new Date(Calendar.getInstance().getTime().getTime()));
        } else {
            return orders.getUserOrders(userId, date);
        }
    }
}
