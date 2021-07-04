package com.heroku.spacey.dao.impl;

import com.heroku.spacey.dao.OrderHistoryDao;
import com.heroku.spacey.dto.order.OrderHistoryDto;
import com.heroku.spacey.mapper.order.OrderHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:sql/order_history_queries.properties")

public class OrderHistoryDaoImpl implements OrderHistoryDao {
    private final JdbcTemplate jdbcTemplate;
    private final OrderHistoryMapper orderHistoryMapper;

    @Value("${get_user_orders}")
    private String getUserOrders;

    @Override
    public List<OrderHistoryDto> getUserOrders(Long userId, Date date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);

        return jdbcTemplate.query(getUserOrders, orderHistoryMapper, userId, startOfDay, endOfDay);
    }
}

