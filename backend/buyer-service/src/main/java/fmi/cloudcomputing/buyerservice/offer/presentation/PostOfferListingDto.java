package fmi.cloudcomputing.buyerservice.offer.presentation;

import fmi.cloudcomputing.buyerservice.offer.jpa.OfferStatus;
import fmi.cloudcomputing.buyerservice.user.presentation.UserDto;

public class PostOfferListingDto {
    private Long id;
    private double offeredPrice;
    private OfferStatus status;
    private UserDto buyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public UserDto getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDto buyer) {
        this.buyer = buyer;
    }
}
