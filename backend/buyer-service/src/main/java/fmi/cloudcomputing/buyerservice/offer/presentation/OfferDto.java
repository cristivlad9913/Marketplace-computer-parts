package fmi.cloudcomputing.buyerservice.offer.presentation;

import fmi.cloudcomputing.buyerservice.offer.jpa.OfferStatus;
import fmi.cloudcomputing.buyerservice.offer.jpa.PostSummary;
import fmi.cloudcomputing.buyerservice.user.presentation.UserDto;

//Aici vrem toate detaliile despre oferta, fiindca va fi o pagina intreaga doar despre oferta
public class OfferDto {
    private Long id;
    private String postId;
    private UserDto buyer;
    private double offeredPrice;
    private OfferStatus status;
    private PostSummary postSummary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public PostSummary getPostSummary() {
        return postSummary;
    }

    public void setPostSummary(PostSummary postSummary) {
        this.postSummary = postSummary;
    }
}
