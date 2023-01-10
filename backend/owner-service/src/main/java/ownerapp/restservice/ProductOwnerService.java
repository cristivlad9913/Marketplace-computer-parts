package ownerapp.restservice;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductOwnerService {

    private final ProductOwnerRepository productOwnerRepository;
    private final PostRepository postRepository;
    private final ItemRepository itemRepository;
    private final OfferRepository offerRepository;
    private final DealRepository dealRepository;

    public ProductOwnerService(ProductOwnerRepository productOwnerRepository,
                               PostRepository postRepository,
                               ItemRepository itemRepository,
                               OfferRepository offerRepository,
                               DealRepository dealRepository) {
        this.productOwnerRepository = productOwnerRepository;
        this.postRepository = postRepository;
        this.itemRepository = itemRepository;
        this.offerRepository = offerRepository;
        this.dealRepository = dealRepository;
    }

    public ProductOwner createProductOwner(ProductOwner productOwner) {
        return productOwnerRepository.save(productOwner);
    }

    public ProductOwner login(String username, String password) {
        return productOwnerRepository.findByUsernameAndPassword(username, password);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post post) {
        Post postFromDb = postRepository.findById(postId);
        postFromDb.setTitle(post.getTitle());
        postFromDb.setDescription(post.getDescription());
        postFromDb.setContact(post.getContact());
        postFromDb.setStatus(post.getStatus());
        return postRepository.save(postFromDb);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public Item addItemToPost(Long postId, Item item) {
        Post post = postRepository.findById(postId);
        item.setPost(post);
        return itemRepository.save(item);
    }

    public Item updateItem(Long postId, Long itemId, Item item) {
        Item itemFromDb = itemRepository.findByIdAndPostId(itemId, postId);
        itemFromDb.setName(item.getName());
        itemFromDb.setPrice(item.getPrice());
        itemFromDb.setDescription(item.getDescription());
        return itemRepository.save(itemFromDb);
    }

    public void deleteItem(Long postId, Long itemId) {
        itemRepository.deleteByIdAndPostId(itemId, postId);
    }
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer acceptOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId);
        offer.setStatus("ACCEPTED");
        return offerRepository.save(offer);
    }

    public Offer declineOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId);
        offer.setStatus("DECLINED");
        return offerRepository.save(offer);
    }

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public ProductOwner getProductOwnerProfile() {
        String username = "test";
        return productOwnerRepository.findByUsername(username);
    }

    public ProductOwner updateProductOwnerProfile(ProductOwner productOwner) {
        String username = "test";
        ProductOwner productOwnerFromDb = productOwnerRepository.findByUsername(username);
        productOwnerFromDb.setUsername(productOwner.getUsername());
        productOwnerFromDb.setPassword(productOwner.getPassword());
        productOwnerFromDb.setMail(productOwner.getMail());
        productOwnerFromDb.setPhone(productOwner.getPhone());
        return productOwnerRepository.save(productOwnerFromDb);
    }

    public void logout() {
       // TODO: add this
    }
}

