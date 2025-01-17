package com.heroku.spacey.dao;

import com.heroku.spacey.dto.order.CreateOrderDto;
import com.heroku.spacey.dto.order.CourierOrdersDto;

import java.sql.Date;
import java.util.List;

public interface OrderDao {

    Long insert(CreateOrderDto order);

    void addProductToOrder(Long orderId, Long productId, Long sizeId, int amount, float sum);

    void addUserToOrders(Long orderId, Long userId);

    List<CourierOrdersDto> getCourierOrders(Long userId, Date date);
}
