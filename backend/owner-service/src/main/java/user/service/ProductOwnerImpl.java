package user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import user.jpa.ProductOwner;
import user.deal.Deal;
import user.item.Item;
import user.jpa.ProductOwnerRepository;
import user.offer.Offer;
import user.post.Post;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductOwnerImpl implements ProductOwnerService {

    private final ProductOwnerRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ProductOwnerImpl(ProductOwnerRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductOwner createProductOwner(ProductOwner productOwner) {
        return ProductOwnerService.super.createProductOwner(productOwner);
    }

    @Override
    public ProductOwner registerUser(ProductOwner dto) {
        ProductOwner newUser = modelMapper.map(dto, ProductOwner.class);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser = userRepository.save(newUser);
        return modelMapper.map(newUser, ProductOwner.class);
    }

    @Override
    public ProductOwner getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof  ProductOwner){
            return modelMapper.map(((ProductOwner)auth.getPrincipal()), ProductOwner.class);
        }else{
            return userRepository
                    .findByUsername(auth.getName())
                    .map(user -> modelMapper.map(user, ProductOwner.class))
                    .orElseThrow(() -> new NoSuchElementException("Could not find user profile"));
        }
    }

    @Override
    public Optional<ProductOwner> login(String username, String password) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Post createPost(Post post) {
        return null;
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
    public Optional<ProductOwner> getProductOwnerProfile() {
        return ProductOwnerService.super.getProductOwnerProfile();
    }

    @Override
    public Optional<ProductOwner> updateProductOwnerProfile(ProductOwner productOwner) {
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

}
