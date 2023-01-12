package ownerapp.restservice;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductOwnerImpl implements ProductOwnerService {
    @Override
    public ProductOwner createProductOwner(ProductOwner productOwner) {
        return ProductOwnerService.super.createProductOwner(productOwner);
    }

    @Override
    public ProductOwner login(String username, String password) {
        return ProductOwnerService.super.login(username, password);
    }

    @Override
    public List<Post> getAllPosts() {
        return ProductOwnerService.super.getAllPosts();
    }

    @Override
    public Post createPost(Post post) {
        return ProductOwnerService.super.createPost(post);
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        return ProductOwnerService.super.updatePost(postId, post);
    }

    @Override
    public void deletePost(Long postId) {
        ProductOwnerService.super.deletePost(postId);
    }

    @Override
    public Post getPostById(Long postId) {
        return ProductOwnerService.super.getPostById(postId);
    }

    @Override
    public Item addItemToPost(Long postId, Item item) {
        return ProductOwnerService.super.addItemToPost(postId, item);
    }

    @Override
    public Item updateItem(Long postId, Long itemId, Item item) {
        return ProductOwnerService.super.updateItem(postId, itemId, item);
    }

    @Override
    public void deleteItem(Long postId, Long itemId) {
        ProductOwnerService.super.deleteItem(postId, itemId);
    }

    @Override
    public List<Offer> getAllOffers() {
        return ProductOwnerService.super.getAllOffers();
    }

    @Override
    public Offer acceptOffer(Long offerId) {
        return ProductOwnerService.super.acceptOffer(offerId);
    }

    @Override
    public Offer declineOffer(Long offerId) {
        return ProductOwnerService.super.declineOffer(offerId);
    }

    @Override
    public List<Deal> getAllDeals() {
        return ProductOwnerService.super.getAllDeals();
    }

    @Override
    public ProductOwner getProductOwnerProfile() {
        return ProductOwnerService.super.getProductOwnerProfile();
    }

    @Override
    public ProductOwner updateProductOwnerProfile(ProductOwner productOwner) {
        return ProductOwnerService.super.updateProductOwnerProfile(productOwner);
    }

    @Override
    public boolean userCredentialsValid(ProductOwner productOwner) {
        return false;
    }

    @Override
    public void logout() {
        ProductOwnerService.super.logout();
    }

    @Override
    public ProductOwner getCurrentUser() {
        return null;
    }
}
