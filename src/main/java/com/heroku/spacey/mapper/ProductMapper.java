package com.heroku.spacey.mapper;

import com.heroku.spacey.entity.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        var product = new Product();
        product.setId(resultSet.getInt("productid"));
        product.setCategoryId(resultSet.getInt("categoryid"));
        product.setName(resultSet.getString("productname"));
        product.setCreatedDate(resultSet.getDate("createddate"));
        product.setProductSex(resultSet.getString("productsex"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setPhoto(resultSet.getString("photo"));
        product.setDescription(resultSet.getString("description"));
        product.setDiscount(resultSet.getDouble("discount"));
        product.setIsAvailable(resultSet.getBoolean("isavailable"));
        product.setIsOnAuction(resultSet.getBoolean("isonauction"));

        var categoryMapper = new CategoryForProductMapper();
        var materialMapper = new MaterialForProductMapper();
        var productDetailsMapper = new ProductDetailsForProductMapper();

        var category = categoryMapper.mapRow(resultSet, i);
        var productDetails = productDetailsMapper.mapRow(resultSet, i);
        var materials = materialMapper.mapRow(resultSet, i);

        product.setMaterials(materials);
        product.setProductCategory(category);
        product.setProductDetail(productDetails);
        return product;
    }
}