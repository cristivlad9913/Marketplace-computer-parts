package ownerapp.restservice;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public interface ProductOwnerService {

     ProductOwnerRepository productOwnerRepository=new ProductOwnerRepository();
      PostRepository postRepository=new PostRepository();
      ItemRepository itemRepository=new ItemRepository();
      OfferRepository offerRepository = new OfferRepository();
      DealRepository dealRepository = new DealRepository();

    public default ProductOwner createProductOwner(ProductOwner productOwner) {
        return productOwnerRepository.save(productOwner);
    }

    public default ProductOwner login(String username, String password) {
        return productOwnerRepository.findByUsernameAndPassword(username, password);
    }

    public default List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public default Post createPost(Post post) {
        return postRepository.save(post);
    }

    public default Post updatePost(Long postId, Post post) {
        Post postFromDb = postRepository.findById(postId);
        postFromDb.setTitle(post.getTitle());
        postFromDb.setDescription(post.getDescription());
        postFromDb.setContact(post.getContact());
        postFromDb.setStatus(post.getStatus());
        return postRepository.save(postFromDb);
    }

    public default void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public default Post getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public default Item addItemToPost(Long postId, Item item) {
        Post post = postRepository.findById(postId);
        item.setPost(post);
        return itemRepository.save(item);
    }

    public default Item updateItem(Long postId, Long itemId, Item item) {
        Item itemFromDb = itemRepository.findByIdAndPostId(itemId, postId);
        itemFromDb.setName(item.getName());
        itemFromDb.setPrice(item.getPrice());
        itemFromDb.setDescription(item.getDescription());
        return itemRepository.save(itemFromDb);
    }

    public default void deleteItem(Long postId, Long itemId) {
        itemRepository.deleteByIdAndPostId(itemId, postId);
    }
    public default List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public default Offer acceptOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId);
        offer.setStatus("ACCEPTED");
        return offerRepository.save(offer);
    }

    public default Offer declineOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId);
        offer.setStatus("DECLINED");
        return offerRepository.save(offer);
    }

    public default List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public default ProductOwner getProductOwnerProfile() {
        String username = "test";
        return productOwnerRepository.findByUsername(username);
    }

    public default ProductOwner updateProductOwnerProfile(ProductOwner productOwner) {
        String username = "test";
        ProductOwner productOwnerFromDb = productOwnerRepository.findByUsername(username);
        productOwnerFromDb.setUsername(productOwner.getUsername());
        productOwnerFromDb.setPassword(productOwner.getPassword());
        productOwnerFromDb.setMail(productOwner.getMail());
        productOwnerFromDb.setPhone(productOwner.getPhone());
        return productOwnerRepository.save(productOwnerFromDb);
    }
    boolean userCredentialsValid(ProductOwner productOwner);
    public default void logout() {
       // TODO: add this
    }

    ProductOwner getCurrentUser();
}

