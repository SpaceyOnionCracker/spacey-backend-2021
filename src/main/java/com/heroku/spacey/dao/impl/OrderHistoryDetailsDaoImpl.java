package com.heroku.spacey.dao.impl;

import com.heroku.spacey.dao.OrderHistoryDetailsDao;
import com.heroku.spacey.dto.order.OrderHistoryDetailsDto;
import com.heroku.spacey.dto.product.ProductOrderDto;
import com.heroku.spacey.mapper.order.OrderHistoryDetailsMapper;
import com.heroku.spacey.mapper.order.ProductsInOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:sql/order_history_details_queries.properties")

public class OrderHistoryDetailsDaoImpl implements OrderHistoryDetailsDao {

    private final JdbcTemplate jdbcTemplate;
    private final ProductsInOrderMapper productsInOrderMapper;

    @Value("${select_details_by_order_id}")
    private String sqlGetOrderDetails;

    @Value("${products_in_order}")
    private String sqlProductInOrder;

    @Override
    public OrderHistoryDetailsDto getOrderDetails(Long orderId, Long userId) {
        OrderHistoryDetailsMapper mapper = new OrderHistoryDetailsMapper(getAllProductInOrder(orderId));
        return jdbcTemplate.query(sqlGetOrderDetails, mapper, orderId, userId);
    }

    public List<ProductOrderDto> getAllProductInOrder(Long orderId) {
        return jdbcTemplate.query(sqlProductInOrder, productsInOrderMapper, orderId);
    }
}
