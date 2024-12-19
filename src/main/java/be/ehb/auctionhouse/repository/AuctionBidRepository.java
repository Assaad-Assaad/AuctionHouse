package be.ehb.auctionhouse.repository;


import be.ehb.auctionhouse.model.AuctionBid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionBidRepository extends JpaRepository<AuctionBid, Integer> {

     List<AuctionBid> findAllByAuction_Id(int auction_id);
     Double findMaxBidPriceByAuction_Id(int auction_id);

}
