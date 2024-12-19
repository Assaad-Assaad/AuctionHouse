package be.ehb.auctionhouse.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuctionPersonNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuctionPersonNumber {
    String message() default "Invalid Auction Person Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
