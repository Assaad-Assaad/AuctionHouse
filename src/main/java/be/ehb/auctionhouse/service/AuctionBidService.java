package be.ehb.auctionhouse.service;


import be.ehb.auctionhouse.repository.AuctionBidRepository;
import org.springframework.stereotype.Service;

@Service
public class AuctionBidService {

    private final AuctionBidRepository auctionBidRepository;
    private final AuctionService auctionService;
    private final PersonService personService;


    public AuctionBidService(AuctionBidRepository auctionBidRepository, AuctionService auctionService, PersonService personService) {
        this.auctionBidRepository = auctionBidRepository;
        this.auctionService = auctionService;
        this.personService = personService;
    }


}
