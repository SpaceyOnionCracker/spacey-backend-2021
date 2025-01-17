package com.heroku.spacey.dao;

import com.heroku.spacey.entity.Auction;

import java.util.List;

public interface AuctionDao {
    List<Auction> getAllByTypeAuctions(Boolean type);

    List<Auction> getAllAuctions();

    boolean isExist(Long id);

    Auction getById(Long id);

    Long insert(Auction auction);

    void update(Auction auction);

    void delete(Long id);

    void updateBid(Auction auction);
}
