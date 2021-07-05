package com.heroku.spacey.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.heroku.spacey.dto.auction.AuctionDto;
import com.heroku.spacey.services.AuctionService;
import org.springframework.web.bind.annotation.*;
import com.heroku.spacey.dto.auction.AuctionBidDto;
import com.heroku.spacey.dto.auction.AllAuctionsDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auction")
public class AuctionController {
    private final AuctionService auctionService;

    @GetMapping("/all")
    public List<AllAuctionsDto> getAllAuctions() {
        return auctionService.getAll();
    }

    @GetMapping("/all_type")
    public List<AllAuctionsDto> getAllAuctionsByType(@RequestParam Boolean type) {
        return auctionService.getAllByType(type);
    }

    @GetMapping("/{id}")
    public AuctionDto getAuctionById(@PathVariable Long id) {
        return auctionService.getById(id);
    }

    @PostMapping("/add")
    @Secured("ROLE_PRODUCT_MANAGER")
    public HttpStatus addAuction(@RequestBody AuctionDto auctionDto) {
        auctionService.add(auctionDto);
        return HttpStatus.CREATED;
    }

    @PutMapping("/edit/{id}")
    @Secured("ROLE_PRODUCT_MANAGER")
    public HttpStatus editAuction(@RequestBody AuctionDto auctionDto,
                                  @PathVariable Long id) {
        AuctionDto auction = auctionService.getById(id);
        auctionService.update(auctionDto, auction.getAuctionId());
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_PRODUCT_MANAGER")
    public HttpStatus deleteAuction(@PathVariable Long id) {
        AuctionDto auction = auctionService.getById(id);
        auctionService.remove(auction.getAuctionId());
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/update-bid")
    @Secured("ROLE_USER")
    public HttpStatus updateAuctionBid(@RequestBody AuctionBidDto auctionBidDto) {
        AuctionDto auction = auctionService.getById(auctionBidDto.getAuctionId());
        auctionService.updateBid(auction, auctionBidDto.getBuyPrice());
        return HttpStatus.OK;
    }
}
