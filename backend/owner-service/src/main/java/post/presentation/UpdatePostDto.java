package post.presentation;

import post.jpa.PostStatus;
import user.offer.Offer;

public class UpdatePostDto {
    private PostStatus status;
    private Long price;

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

}
