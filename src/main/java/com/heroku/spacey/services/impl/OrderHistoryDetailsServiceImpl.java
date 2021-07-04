package com.heroku.spacey.services.impl;

import com.heroku.spacey.dao.OrderHistoryDetailsDao;
import com.heroku.spacey.dto.order.OrderHistoryDetailsDto;
import com.heroku.spacey.services.OrderHistoryDetailsService;
import com.heroku.spacey.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class OrderHistoryDetailsServiceImpl implements OrderHistoryDetailsService {

    private final OrderHistoryDetailsDao orderHistoryDetailsDao;
    private final SecurityUtils securityUtils;

    @Override
    public OrderHistoryDetailsDto getOrderDetails(Long orderId) throws SQLException {
        return orderHistoryDetailsDao.getOrderDetails(orderId, securityUtils.getUserIdByToken());
    }
}
