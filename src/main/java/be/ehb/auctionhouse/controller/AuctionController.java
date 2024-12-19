package be.ehb.auctionhouse.controller;

import be.ehb.auctionhouse.model.Auction;
import be.ehb.auctionhouse.model.AuctionBid;
import be.ehb.auctionhouse.service.AuctionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping
    public List<Auction> findAll() {
        return auctionService.findAll();
    }

    @PostMapping
    public Auction save(@Valid @RequestBody Auction auction) {
        return auctionService.save(auction);
    }

    @PostMapping("/{id}/bids")
    public void bidOnAuction(@PathVariable("id") int auctionId, @Valid @RequestBody AuctionBid bid) {
        auctionService.bidOnAuction(auctionId, bid);
    }

    @GetMapping("/{id}/bids")
    public List<AuctionBid> findBidOnAuction(@PathVariable("id") int auctionId) {
        return auctionService.findAuctionBidsByAuctionId(auctionId);
    }


}
