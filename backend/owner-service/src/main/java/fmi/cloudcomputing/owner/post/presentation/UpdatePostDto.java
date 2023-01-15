package fmi.cloudcomputing.owner.post.presentation;

import fmi.cloudcomputing.owner.post.jpa.PostStatus;

import java.util.List;

public class UpdatePostDto {
    private String title;
    private String description;
    private PostStatus status;
    private List<UpdateItemDto> items;

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

    public List<UpdateItemDto> getItems() {
        return items;
    }

    public void setItems(List<UpdateItemDto> items) {
        this.items = items;
    }
}
