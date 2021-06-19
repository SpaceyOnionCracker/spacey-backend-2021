package com.heroku.spacey.dao.impl;

import com.heroku.spacey.dao.AuctionDao;
import com.heroku.spacey.entity.Auction;
import com.heroku.spacey.mapper.auction.AuctionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@PropertySource("classpath:sql/auction_queries.properties")
public class AuctionDaoImpl implements AuctionDao {
    private AuctionMapper mapper = new AuctionMapper();
    private final JdbcTemplate jdbcTemplate;

    @Value("${get_all_decrease_auctions}")
    private String getAllDecreaseAuctions;
    @Value("${get_all_increase_auctions}")
    private String getAllIncreaseAuctions;
    @Value("${add_product_to_auction}")
    private String productToAuction;
    @Value("${auction_get_by_id}")
    private String getAuctionById;
    @Value("${insert_auction}")
    private String editAuction;
    @Value("${update_auction}")
    private String updateAuction;
    @Value("${delete_auction}")
    private String deleteAuction;

    public AuctionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Auction> getAllDecreaseAuctions() {
        List<Auction> auctions = Objects.requireNonNull(jdbcTemplate).query(getAllDecreaseAuctions, mapper);
        if (auctions.isEmpty()) {
            return new ArrayList<>();
        }
        return auctions;
    }

    @Override
    public List<Auction> getAllIncreaseAuctions() {
        List<Auction> auctions = Objects.requireNonNull(jdbcTemplate).query(getAllIncreaseAuctions, mapper);
        if (auctions.isEmpty()) {
            return new ArrayList<>();
        }
        return auctions;
    }

    @Override
    public Auction getById(Long id) {
        List<Auction> auctions = Objects.requireNonNull(jdbcTemplate).query(getAuctionById, mapper);
        if (auctions.isEmpty()) {
            return null;
        }
        return auctions.get(0);
    }

    @Override
    public Long insert(Auction auction) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(editAuction, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, auction.getAuctionName());
            return ps;
        }, holder);
        return (Long) Objects.requireNonNull(holder.getKeys()).get("auctionId");
    }

    @Override
    public void update(Auction auction) {
        Object[] params = new Object[]{
                auction.getAuctionName(), auction.getAuctionType(), auction.getStartPrice(),
                auction.getEndPrice(), auction.getPriceStep(), auction.getStartTime(),
                auction.getEndTime(), auction.getStatus(), auction.getAuctionId()};
        Objects.requireNonNull(jdbcTemplate).update(updateAuction, params);
    }

    @Override
    public void addProductToAuction(Long auctionId, Long productId, Long sizeId, Integer amount) {
        Objects.requireNonNull(jdbcTemplate).update(productToAuction, auctionId, productId, sizeId, amount);
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(jdbcTemplate).update(deleteAuction, id);
    }
}
