package com.heroku.spacey.dto.auction;

import lombok.Data;

@Data
public class AuctionBidDto {
    private Long auctionId;
    private Double buyPrice;
}
