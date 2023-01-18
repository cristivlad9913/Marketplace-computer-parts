package fmi.cloudcomputing.owner.post.presentation;


import fmi.cloudcomputing.owner.post.jpa.OfferStatus;

public class UpdatePostOfferDto {
    private OfferStatus status;

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }
}
