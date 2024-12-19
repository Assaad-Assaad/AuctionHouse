package be.ehb.auctionhouse.repository;


import be.ehb.auctionhouse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByAuctionPersonNumber(String auctionPersonNumber);

    List<Person> findByNameContainingIgnoreCase(String name);
}
