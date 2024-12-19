package be.ehb.auctionhouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "persons")
public class Person {

    @Id
    @Column(name = "auction_person_number", unique = true, nullable = false, updatable = false)
    @AuctionPersonNumber
    private String auctionPersonNumber;

   @Column(name = "name")
   @NotBlank(message = "Name cannot be empty!")
   private String name;

   @Column(name = "email_address", unique = true, nullable = false)
   @Email(message = "Email Address is not valid")
   private String emailAddress;

    public Person() {}

    public Person(String auctionPersonNumber, @NotBlank String name, @Email String emailAddress) {
        this.auctionPersonNumber = auctionPersonNumber;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public String getAuctionPersonNumber() {
        return auctionPersonNumber;
    }

    public void setAuctionPersonNumber(String auctionPersonNumber) {
        this.auctionPersonNumber = auctionPersonNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return auctionPersonNumber.equals(person.auctionPersonNumber);
    }

    @Override
    public int hashCode() {
       return auctionPersonNumber.hashCode();
    }


}
