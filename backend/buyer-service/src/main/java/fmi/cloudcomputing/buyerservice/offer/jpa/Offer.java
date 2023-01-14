package fmi.cloudcomputing.buyerservice.offer.jpa;

import fmi.cloudcomputing.buyerservice.user.jpa.User;

import javax.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @Column(name = "offered_price")
    private double offeredPrice;

    @Column(name = "accepted")
    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @Embedded
    private PostSummary postSummary;

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double price) {
        this.offeredPrice = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User owner) {
        this.buyer = owner;
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
