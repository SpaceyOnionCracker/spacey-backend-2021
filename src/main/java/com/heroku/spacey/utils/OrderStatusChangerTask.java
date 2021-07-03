package com.heroku.spacey.utils;

import com.heroku.spacey.dao.OrderStatusDao;
import com.heroku.spacey.dao.OrderDetailsDao;
import com.heroku.spacey.dto.order.OrderStatusDto;
import com.heroku.spacey.entity.OrderStatus;
import lombok.AllArgsConstructor;
import org.webjars.NotFoundException;

@AllArgsConstructor
public class OrderStatusChangerTask implements Runnable {
    private Long orderId;
    private OrderStatusDao orderStatusDao;
    private OrderDetailsDao orderDetailsDao;

    @Override
    public void run() {
        Long orderStatusId;

        try {
            orderStatusId = orderStatusDao.getByName("IN DELIVERY").getOrderStatusId();
        } catch (NotFoundException e) {
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setStatus("IN DELIVERY");
            orderStatusId = orderStatusDao.insert(orderStatus);
        }

        OrderStatusDto orderStatusDto = new OrderStatusDto();
        orderStatusDto.setOrderId(orderId);
        orderStatusDto.setOrderStatusId(orderStatusId);
        orderDetailsDao.updateOrderStatus(orderStatusDto);
    }
}
