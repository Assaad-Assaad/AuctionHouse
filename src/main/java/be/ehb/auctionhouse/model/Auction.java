package be.ehb.auctionhouse.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "products")
    @NotBlank(message = "Product cannot be empty!")
    private String product;
    @Column(name = "start_price", nullable = false)
    @NotNull(message = "Price cannot be 0!")
    private double startPrice;

    @JoinColumn(name = "person_id")
    @ManyToOne
    private Person person;


    @NotNull(message = "End time is required.")
    @Future(message = "End time must be in the future.")
    @Column(name = "end_time", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endTime;

    public Auction() {}

    public Auction(@NotBlank String product, @NotNull double startPrice, Person person, @NotNull LocalDateTime endTime) {
        this.product = product;
        this.startPrice = startPrice;
        this.person = person;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return id == auction.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
