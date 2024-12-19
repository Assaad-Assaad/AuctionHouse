package be.ehb.auctionhouse.service;


import be.ehb.auctionhouse.model.Auction;
import be.ehb.auctionhouse.model.AuctionBid;
import be.ehb.auctionhouse.repository.AuctionBidRepository;
import be.ehb.auctionhouse.repository.AuctionRepository;
import be.ehb.auctionhouse.service.exeption.InvalidBidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final AuctionBidRepository auctionBidRepository;
    private final MailService mailService;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository, AuctionBidRepository AuctionBidRepository, AuctionBidRepository auctionBidRepository, MailService mailService) {
        this.auctionRepository = auctionRepository;
        this.auctionBidRepository = auctionBidRepository;
        this.mailService = mailService;
    }

    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    public Auction save(Auction auction) {
        if(auction.getEndTime().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("End time must be in the future");
        }
        return auctionRepository.save(auction);
    }

    public List<AuctionBid> findAuctionBidsByAuctionId(int auctionId) {
        return auctionBidRepository.findAllByAuction_Id(auctionId);
    }


    public void bidOnAuction(int auctionId, AuctionBid auctionBid) {
       Optional<Auction> byId = auctionRepository.findById(auctionId);
       Auction auction = byId.orElseThrow(() -> new InvalidBidException("No such auction"));
       auctionBid.setAuction(auction);

       LocalDateTime now = LocalDateTime.now();
       if(auction.getEndTime().isBefore(now)){
           throw new InvalidBidException("The auction has ended, No more bids allowed");
       }

        if (auctionBid.getPerson().equals(auction.getPerson())) {
            mailService.sendEmail("Fraud detected for user: " + auction.getPerson().getEmailAddress(), "fraud@auctionhouse.com");
            throw new InvalidBidException("You cannot bid on your own auction!");
        }


       List<AuctionBid> allBids = auctionBidRepository.findAllByAuction_Id(auctionId);

       if(allBids.isEmpty()){
           if(auctionBid.getPrice() < auction.getStartPrice()){
               throw new InvalidBidException("The price is lower than the start price");
           }
            auctionBidRepository.save(auctionBid);
             return;
       }

       Double highestBid = auctionBidRepository.findMaxBidPriceByAuction_Id(auctionId);
            if(highestBid == null || auctionBid.getPrice() > highestBid){
                auctionBidRepository.save(auctionBid);
            }else {
                throw new InvalidBidException("The highest bid price is greater than the current price");
            }



    }


}
