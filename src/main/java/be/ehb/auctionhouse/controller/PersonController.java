package be.ehb.auctionhouse.controller;

import be.ehb.auctionhouse.model.Person;
import be.ehb.auctionhouse.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> findAll(@RequestParam(value = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return personService.findByNameContaining(name);
        }
        return personService.findAll();
    }

    @GetMapping("/{auctionPersonNumber}")
    public Person findPersonByAuctionPersonNumber(@PathVariable String auctionPersonNumber) {
        return personService.findByAuctionPersonNumber(auctionPersonNumber);
    }


    @PostMapping
    public Person save(@Valid @RequestBody Person person) {
        return personService.save(person);
    }


}
