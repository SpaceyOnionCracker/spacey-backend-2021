package com.heroku.spacey.mapper.order;

import com.heroku.spacey.dto.order.OrderHistoryDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderHistoryMapper implements RowMapper<OrderHistoryDto> {

    @Override
    public OrderHistoryDto mapRow(ResultSet resultSet, int i) throws SQLException {

        OrderHistoryDto orderHistoryDto = new OrderHistoryDto();

        orderHistoryDto.setOrderId(resultSet.getLong("orderid"));
        orderHistoryDto.setOrdenerName(resultSet.getString("ordenername"));
        orderHistoryDto.setOrdenerSurname(resultSet.getString("ordenersurname"));
        orderHistoryDto.setPhoneNumber(resultSet.getString("phonenumber"));
        orderHistoryDto.setStatus(resultSet.getString("status"));
        orderHistoryDto.setDateDelivery(resultSet.getDate("datedelivery"));
        orderHistoryDto.setCity(resultSet.getString("city"));
        orderHistoryDto.setStreet(resultSet.getString("street"));
        orderHistoryDto.setHouse(resultSet.getString("house"));
        orderHistoryDto.setApartment(resultSet.getString("appartment"));

        return orderHistoryDto;
    }
}
