package ownerapp.restservice;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productowner")
public class ProductOwnerController {

    private final ProductOwnerService productOwnerService;

    public ProductOwnerController(ProductOwnerService productOwnerService) {
        this.productOwnerService = productOwnerService;
    }

    @PostMapping("/signup")
    public ProductOwner signup(@RequestBody ProductOwner productOwner) {
        return productOwnerService.createProductOwner(productOwner);
    }

    @PostMapping("/login")
    public ProductOwner login(@RequestBody ProductOwner productOwner) {
        return productOwnerService.login(productOwner.getUsername(), productOwner.getPassword());
    }

    @GetMapping("/feed")
    public List<Post> getAllPosts() {
        return productOwnerService.getAllPosts();
    }

    @PostMapping("/feed")
    public Post createPost(@RequestBody Post post) {
        return productOwnerService.createPost(post);
    }

    @PutMapping("/feed/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
        return productOwnerService.updatePost(postId, post);
    }

    @DeleteMapping("/feed/{postId}")
    public void deletePost(@PathVariable Long postId) {
        productOwnerService.deletePost(postId);
    }

    @GetMapping("/post/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return productOwnerService.getPostById(postId);
    }

    @PostMapping("/post/{postId}/item")
    public Item addItemToPost(@PathVariable Long postId, @RequestBody Item item) {
        return productOwnerService.addItemToPost(postId, item);
    }

    @PutMapping("/post/{postId}/item/{itemId}")
    public Item updateItem(@PathVariable Long postId, @PathVariable Long itemId, @RequestBody Item item) {
        return productOwnerService.updateItem(postId, itemId, item);
    }

    @DeleteMapping("/post/{postId}/item/{itemId}")
    public void deleteItem(@PathVariable Long postId, @PathVariable Long itemId) {
        productOwnerService.deleteItem(postId, itemId);
    }

    @GetMapping("/offers")
    public List<Offer> getAllOffers() {
        return productOwnerService.getAllOffers();
    }

    @PostMapping("/offers/{offerId}/accept")
    public Offer acceptOffer(@PathVariable Long offerId) {
        return productOwnerService.acceptOffer(offerId);
    }

    @PostMapping("/offers/{offerId}/decline")
    public Offer declineOffer(@PathVariable Long offerId) {
        return productOwnerService.declineOffer(offerId);
    }
    @GetMapping("/deals")
    public List<Deal> getAllDeals() {
        return productOwnerService.getAllDeals();
    }

    @GetMapping("/profile")
    public ProductOwner getProductOwnerProfile() {
        return productOwnerService.getProductOwnerProfile();
    }

    @PutMapping("/profile")
    public ProductOwner updateProductOwnerProfile(@RequestBody ProductOwner productOwner) {
        return productOwnerService.updateProductOwnerProfile(productOwner);
    }

    @PostMapping("/logout")
    public void logout() {
        productOwnerService.logout();
    }
}