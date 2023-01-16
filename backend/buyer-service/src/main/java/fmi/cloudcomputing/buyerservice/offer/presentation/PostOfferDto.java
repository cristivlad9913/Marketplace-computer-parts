package fmi.cloudcomputing.buyerservice.offer.presentation;

import fmi.cloudcomputing.buyerservice.offer.jpa.OfferStatus;
import fmi.cloudcomputing.buyerservice.user.presentation.UserDto;

public class PostOfferDto {
    private long offerId;
    private UserDto buyer;
    private double offeredPrice;
    private OfferStatus status;

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public UserDto getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDto buyer) {
        this.buyer = buyer;
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
}
