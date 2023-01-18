package fmi.cloudcomputing.owner.post.presentation;

import fmi.cloudcomputing.owner.post.jpa.BuyerSummary;
import fmi.cloudcomputing.owner.post.jpa.OfferStatus;

public class PostOfferDto {
    private long offerId;
    private BuyerSummary buyer;
    private double offeredPrice;
    private OfferStatus status;

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public BuyerSummary getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerSummary buyer) {
        this.buyer = buyer;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }
}
