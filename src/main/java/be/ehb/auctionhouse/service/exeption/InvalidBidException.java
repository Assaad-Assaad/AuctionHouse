package be.ehb.auctionhouse.service.exeption;

public class InvalidBidException extends RuntimeException {

    public InvalidBidException(String message) {
        super(message);
    }
}
