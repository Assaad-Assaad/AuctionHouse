package be.ehb.auctionhouse.repository;


import be.ehb.auctionhouse.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Integer> { }
