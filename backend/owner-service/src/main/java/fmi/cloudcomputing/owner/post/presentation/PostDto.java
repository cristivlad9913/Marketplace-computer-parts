package fmi.cloudcomputing.owner.post.presentation;

import fmi.cloudcomputing.owner.post.jpa.PostStatus;
import fmi.cloudcomputing.owner.user.presentation.ProductOwnerDto;

import java.util.List;

public class PostDto {
    private Long id;
    private String title;
    private String description;
    private double total;
    private PostStatus status;
    private List<ItemDto> items;
    private ProductOwnerDto owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public ProductOwnerDto getOwner() {
        return owner;
    }

    public void setOwner(ProductOwnerDto owner) {
        this.owner = owner;
    }
}
