package fmi.cloudcomputing.owner.post.presentation;

import java.util.List;

public class CreatePostDto {
    private String title;
    private String description;
    private List<CreateItemDto> items;

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

    public List<CreateItemDto> getItems() {
        return items;
    }

    public void setItems(List<CreateItemDto> items) {
        this.items = items;
    }
}
