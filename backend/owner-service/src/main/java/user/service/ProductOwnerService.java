package user.service;


import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import user.jpa.ProductOwner;
import user.jpa.ProductOwnerRepository;
import user.deal.Deal;
import user.deal.DealRepository;
import user.item.Item;
import user.item.ItemRepository;
import user.offer.Offer;
import user.offer.OfferRepository;
import user.post.Post;
import user.post.PostRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOwnerService {

    ProductOwner registerUser(ProductOwner productOwner) ;

    ProductOwner getCurrentUser(String username, String password);

    List<Post> getAllPosts();

    Post createPost(Post post);

    Post updatePost(Long postId, Post post);

    void deletePost(Long postId);

    Post getPostById(Long postId);

    Item addItemToPost(Long postId, Item item);

    Item updateItem(Long postId, Long itemId, Item item);

    void deleteItem(Long postId, Long itemId);
     List<Offer> getAllOffers();

     Offer acceptOffer(Long offerId);

      Offer declineOffer(Long offerId);

      List<Deal> getAllDeals();

      ProductOwner getProductOwnerProfile();

      ProductOwner updateProductOwnerProfile(ProductOwner productOwner);

    boolean userCredentialsValid(ProductOwner productOwner);
    public default void logout() {
       // TODO: add this
    }

    ProductOwner getCurrentUser();
}

