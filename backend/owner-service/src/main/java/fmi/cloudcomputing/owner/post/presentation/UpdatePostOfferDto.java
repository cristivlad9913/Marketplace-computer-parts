package fmi.cloudcomputing.owner.post.presentation;

import fmi.cloudcomputing.buyerservice.offer.jpa.OfferStatus;

public class UpdatePostOfferDto {
    private OfferStatus status;

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }
}
