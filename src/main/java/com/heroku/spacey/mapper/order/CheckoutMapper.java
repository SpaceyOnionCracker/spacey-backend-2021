package com.heroku.spacey.mapper.order;

import com.heroku.spacey.dto.order.CheckoutDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CheckoutMapper implements RowMapper<CheckoutDto> {

    @Override
    public CheckoutDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CheckoutDto checkoutDto = new CheckoutDto();

        checkoutDto.setOverallPrice(rs.getFloat("overallprice"));

        checkoutDto.setFirstName(rs.getString("firstname"));
        checkoutDto.setLastName(rs.getString("lastname"));
        checkoutDto.setPhoneNumber(rs.getString("phonenumber"));
        checkoutDto.setEmail(rs.getString("email"));
        checkoutDto.setCity(rs.getString("city"));
        checkoutDto.setStreet(rs.getString("street"));
        checkoutDto.setHouse(rs.getString("house"));
        checkoutDto.setApartment(rs.getString("appartment"));

        return checkoutDto;
    }
}
