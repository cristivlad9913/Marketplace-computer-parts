package fmi.cloudcomputing.owner.post.jpa;

import javax.persistence.Column;

public class BuyerSummary {
    @Column(name = "buyer_id")
    private Long id;

    @Column(name = "buyer_username")
    private String username;

    @Column(name = "buyer_email")
    private String email;

    @Column(name = "buyer_phone")
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
