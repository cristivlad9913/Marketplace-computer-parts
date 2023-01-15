package fmi.cloudcomputing.owner.post.jpa;

import fmi.cloudcomputing.owner.user.jpa.ProductOwner;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private ProductOwner owner;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(name = "total")
    private double total;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductOwner getOwner() {
        return owner;
    }

    public void setOwner(ProductOwner owner) {
        this.owner = owner;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
