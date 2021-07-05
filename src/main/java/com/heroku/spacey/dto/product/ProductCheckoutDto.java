package com.heroku.spacey.dto.product;

import lombok.Data;

@Data
public class ProductCheckoutDto {
    private Long id;
    private String name;
    private String color;
    private Long sizeId;
    private String size;
    private String photo;

    private int amount;
    private float discount;
    private float overallPrice;
}
