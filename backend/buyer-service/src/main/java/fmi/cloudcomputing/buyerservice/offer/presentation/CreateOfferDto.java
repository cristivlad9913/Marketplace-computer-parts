package fmi.cloudcomputing.buyerservice.offer.presentation;

public class CreateOfferDto {
    private Long postId;
    private double offeredPrice;


    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }
}
