package com.heroku.spacey.mapper.order;

import com.heroku.spacey.dto.order.CheckoutDto;
import com.heroku.spacey.dto.product.ProductCheckoutDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuctionCheckoutMapper implements RowMapper<CheckoutDto> {

    @Override
    public CheckoutDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CheckoutDto checkoutDto = new CheckoutDto();
        ProductCheckoutDto product = new ProductCheckoutDto();

        product.setId(rs.getLong("productid"));
        product.setName(rs.getString("productname"));
        product.setColor(rs.getString("color"));
        product.setSizeId(rs.getLong("sizeid"));
        product.setSize(rs.getString("sizename"));
        product.setPhoto(rs.getString("photo"));
        product.setAmount(rs.getInt("amount"));
        product.setDiscount(rs.getFloat("discount"));
        product.setOverallPrice(rs.getFloat("finalprice"));

        checkoutDto.getProducts().add(product);

        checkoutDto.setFirstName(rs.getString("firstname"));
        checkoutDto.setLastName(rs.getString("lastname"));
        checkoutDto.setPhoneNumber(rs.getString("phonenumber"));
        checkoutDto.setEmail(rs.getString("email"));
        checkoutDto.setCity(rs.getString("city"));
        checkoutDto.setStreet(rs.getString("street"));
        checkoutDto.setHouse(rs.getString("house"));
        checkoutDto.setApartment(rs.getString("appartment"));
        checkoutDto.setOverallPrice(rs.getFloat("overallprice"));

        checkoutDto.setOverallPrice(rs.getFloat("finalprice"));

        return checkoutDto;
    }
}
