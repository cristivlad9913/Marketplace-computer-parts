package ownerapp.restservice;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    public ItemRepository() {
    }

    public Item findByIdAndPostId(Long itemID, Long postID){
    return null;
    }

    public Item save(Item itemFromDb) {
        return null;
    }

    public void deleteByIdAndPostId(Long itemId, Long postId) {
    }
}
