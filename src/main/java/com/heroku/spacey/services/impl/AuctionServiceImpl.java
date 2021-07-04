package com.heroku.spacey.services.impl;

import com.heroku.spacey.entity.User;
import lombok.RequiredArgsConstructor;
import org.webjars.NotFoundException;
import com.heroku.spacey.dao.AuctionDao;
import com.heroku.spacey.entity.Auction;
import org.springframework.stereotype.Service;
import com.heroku.spacey.dto.auction.AuctionDto;
import com.heroku.spacey.services.AuctionService;
import com.heroku.spacey.dto.auction.AllAuctionsDto;
import com.heroku.spacey.utils.convertors.CommonConvertor;
import com.heroku.spacey.utils.convertors.AuctionConvertor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final AuctionDao auctionDao;
    private final CommonConvertor commonConvertor;
    private final AuctionConvertor auctionConvertor;

    @Override
    public List<AllAuctionsDto> getAllByType(Boolean type) {
        List<Auction> auctions = auctionDao.getAllByTypeAuctions(type);
        return commonConvertor.mapList(auctions, AllAuctionsDto.class);
    }

    @Override
    public List<AllAuctionsDto> getAll() {
        List<Auction> auctions = auctionDao.getAllAuctions();
        return commonConvertor.mapList(auctions, AllAuctionsDto.class);
    }

    @Override
    public AuctionDto getById(Long id) {
        Auction auction = auctionDao.getById(id);
        if (auction == null) {
            throw new NotFoundException("Auction not found");
        }
        return auctionConvertor.adapt(auction);
    }

    @Override
    @Transactional
    public Long add(AuctionDto auctionDto) {
        Auction auction = auctionConvertor.adapt(auctionDto);

        Long productId = auctionDto.getAuctionProduct().getId();
        auction.setProductId(productId);

        Long sizeId = auctionDto.getProductSize().getId();
        auction.setSizeId(sizeId);

        return auctionDao.insert(auction);
    }

    @Override
    @Transactional
    public void update(AuctionDto auctionDto, Long id) {
        Auction auction = auctionConvertor.adapt(auctionDto);
        auction.setAuctionId(id);
        auctionDao.update(auction);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        boolean isFound = auctionDao.isExist(id);
        if (!isFound) {
            throw new NotFoundException("Auction not found");
        }
        auctionDao.delete(id);
    }

    @Override
    @Transactional
    public void updateBid(AuctionDto auctionDto, Double bid) {
        Auction auction = auctionConvertor.adapt(auctionDto);
        int i;
        if (auction.getAuctionType() == Boolean.TRUE) {
            i = 1;
        } else {
            i = -1;
        }
        boolean isPriceStepCorrect;
        if (auction.getBuyPrice() != 0) {
            isPriceStepCorrect = i * bid - i * auction.getBuyPrice() >= auction.getPriceStep()
                    && bid >= auction.getEndPrice();
        } else {
            isPriceStepCorrect = i * bid - i * auction.getStartPrice() >= auction.getPriceStep()
                    && bid >= auction.getEndPrice();
        }
        if (!isPriceStepCorrect) {
            throw new IllegalArgumentException("Incorrect bid");
        }
        Duration duration = Duration.between(auction.getEndTime().toLocalDateTime(), LocalDateTime.now());
        if (Math.abs(duration.toMinutes()) < 30) {
            auction.setEndTime(Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)));
        }
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = (User) principal;
        auction.setUserId(user.getUserId());
        auction.setBuyPrice(bid);
        auctionDao.updateBid(auction);
    }
}
