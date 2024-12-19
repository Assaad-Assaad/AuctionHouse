package be.ehb.auctionhouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity(name = "bids")
public class AuctionBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "prices")
    @NotNull(message = "You cannot bid 0")
    @Positive(message = "Only positive values allowed")
    private double price;


    @ManyToOne
    @JoinColumn(name = "auction_id")
    @JsonIgnore
    private Auction auction;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public AuctionBid() {}

    public AuctionBid(@NotNull double price, Auction auction, @NotNull Person person) {
        this.price = price;
        this.auction = auction;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
