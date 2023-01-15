package fmi.cloudcomputing.buyerservice.offer.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PostSummary {
    @Column(name = "post_id")
    private Long id;

    @Column(name = "request_price")
    private double requestPrice;

    @Column(name = "post_title")
    private String title;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "owner_username")
    private String ownerUsername;

    @Column(name = "owner_mail")
    private String ownerEmail;

    @Column(name = "owner_phone")
    private String ownerPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(double price) {
        this.requestPrice = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerMail) {
        this.ownerEmail = ownerMail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
