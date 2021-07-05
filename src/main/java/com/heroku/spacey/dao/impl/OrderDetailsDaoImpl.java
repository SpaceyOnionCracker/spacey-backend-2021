package com.heroku.spacey.dao.impl;

import com.heroku.spacey.dao.OrderDetailsDao;
import com.heroku.spacey.dto.order.OrderDetailsDto;
import com.heroku.spacey.dto.order.OrderStatusDto;
import com.heroku.spacey.dto.product.ProductOrderDto;
import com.heroku.spacey.mapper.order.OrderDetailsMapper;
import com.heroku.spacey.mapper.order.ProductsInOrderMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:sql/order_details_queries.properties")
public class OrderDetailsDaoImpl implements OrderDetailsDao {

    private final JdbcTemplate jdbcTemplate;
    private final ProductsInOrderMapper productsInOrderMapper;
    private final String deliveredStatus = "DELIVERED";
    private final String failStatus = "FAIL";
    private final String message = "The status was changed earlier";

    @Value("${select_details_by_order_id}")
    private String sqlGetOrderDetails;

    @Value("${products_in_order}")
    private String sqlProductInOrder;

    @Value("${change_order_status}")
    private String sqlChangeOrderStatus;

    @Value("${set_order_status}")
    private String sqlSetOrderStatus;

    @Value("${select_order_status}")
    private String sqlSelectOrderStatus;

    @Override
    public OrderDetailsDto getOrderDetails(Long orderId, Long userId) {
        OrderDetailsMapper mapper = new OrderDetailsMapper(getAllProductInOrder(orderId));
        return jdbcTemplate.query(sqlGetOrderDetails, mapper, orderId, userId);
    }

    public List<ProductOrderDto> getAllProductInOrder(Long orderId) {
        return jdbcTemplate.query(sqlProductInOrder, productsInOrderMapper, orderId);
    }

    @Override
    public void updateOrderStatus(OrderStatusDto orderStatusDto) {
        String orderStatus = jdbcTemplate.queryForObject(sqlSelectOrderStatus, String.class, orderStatusDto.getOrderId());
        if (orderStatus.equals(deliveredStatus) || orderStatus.equals(failStatus)) {
            throw new IllegalArgumentException(message);
        }
        Objects.requireNonNull(jdbcTemplate).update(
                sqlChangeOrderStatus,
                orderStatusDto.getOrderStatusId(),
                orderStatusDto.getOrderId()
        );
    }

    @Override
    public void setDeliveredStatus(Long orderId) {
        String orderStatus = jdbcTemplate.queryForObject(sqlSelectOrderStatus, String.class, orderId);
        log.info(orderId.toString());
        log.info(sqlSetOrderStatus);
        log.info(sqlSelectOrderStatus);
        log.info(orderStatus);
        if (orderStatus.equals(deliveredStatus) || orderStatus.equals(failStatus)) {
            throw new IllegalArgumentException(message);
        } else {
            jdbcTemplate.update(sqlSetOrderStatus, deliveredStatus, orderId);
        }

    }

    @Override
    public void setFailStatus(Long orderId) {
        String orderStatus = jdbcTemplate.queryForObject(sqlSelectOrderStatus, String.class, orderId);
        if (orderStatus.equals(deliveredStatus) || orderStatus.equals(failStatus)) {
            throw new IllegalArgumentException(message);
        } else {
            jdbcTemplate.update(sqlSetOrderStatus, failStatus, orderId);
        }

    }
}
