package fmi.cloudcomputing.owner.post.presentation;

public class UpdateItemDto extends CreateItemDto{
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
