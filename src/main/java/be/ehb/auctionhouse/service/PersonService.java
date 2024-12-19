package be.ehb.auctionhouse.service;

import be.ehb.auctionhouse.model.Person;
import be.ehb.auctionhouse.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person findByAuctionPersonNumber(String auctionPersonNumber) {
        return personRepository.findByAuctionPersonNumber(auctionPersonNumber);
    }


    public List<Person> findByNameContaining(String name) {
        return personRepository.findByNameContainingIgnoreCase(name);
    }

}
