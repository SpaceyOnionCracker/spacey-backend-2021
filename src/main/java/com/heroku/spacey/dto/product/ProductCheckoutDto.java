package com.heroku.spacey.dto.product;

import lombok.Data;

@Data
public class ProductCheckoutDto {
    private Long productId;
    private String color;
    private Long sizeId;
    private String photo;

    private int amount;
    private float sum;
}
