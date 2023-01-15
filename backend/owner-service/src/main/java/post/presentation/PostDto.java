package post.presentation;

import post.jpa.PostStatus;
import user.item.Item;
import user.jpa.ProductOwner;

import java.util.List;

public class PostDto {
    private Long id;
    private String postID;
    private String description;
    private double price;
    private String title;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String phoneNumber;

    private String email;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public void setProductOwner(ProductOwner productOwner) {
        this.productOwner = productOwner;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public String getPostID() {
        return postID;
    }

    public ProductOwner getProductOwner() {
        return productOwner;
    }

    public PostStatus getStatus() {
        return status;
    }

    private ProductOwner productOwner;
    private PostStatus status;
}
