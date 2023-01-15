package user.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
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
import user.presentation.CreateProductOwner;
import user.presentation.LoginProductOwnerDto;
import user.presentation.ProductOwnerDto;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ProductOwnerDto registerUser(CreateProductOwner dto) {
        ProductOwner newUser = modelMapper.map(dto, ProductOwner.class);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser = userRepository.save(newUser);
        return modelMapper.map(newUser, ProductOwnerDto.class);
    }

    @Override
    public ProductOwnerDto getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof  ProductOwner){
            return modelMapper.map(((ProductOwner)auth.getPrincipal()), ProductOwnerDto.class);
        }else{
            return userRepository
                    .findByUsername(auth.getName())
                    .map(user -> modelMapper.map(user, ProductOwnerDto.class))
                    .orElseThrow(() -> new NoSuchElementException("Could not find user profile"));
        }
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
return null;    }

    @Override
    public void deletePost(Long postId) {

    }

    @Override
    public Post getPostById(Long postId) {
     return null;
    }

    @Override
    public Item addItemToPost(Long postId, Item item) {
       return null;
    }

    @Override
    public Item updateItem(Long postId, Long itemId, Item item) {
      return null;
    }

    @Override
    public void deleteItem(Long postId, Long itemId) {

    }

    @Override
    public List<Offer> getAllOffers() {
        return null;
    }

    @Override
    public Offer acceptOffer(Long offerId) {
        return null;
    }

    @Override
    public Offer declineOffer(Long offerId) {
        return null;
    }

    @Override
    public List<Deal> getAllDeals() {
        return null;
    }

    @Override
    public ProductOwner getProductOwnerProfile() {

        return null;
    }

    @Override
    public ProductOwner updateProductOwnerProfile(ProductOwner productOwner) {
        return null;
    }

    @Override
    public void logout() {
        ProductOwnerService.super.logout();
    }
    @Override
    public boolean userCredentialsValid(LoginProductOwnerDto loginUserDto) {
        return userRepository
                .findByUsername(loginUserDto.getUsername())
                .map(user -> passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword()))
                .orElse(false);
    }

}
