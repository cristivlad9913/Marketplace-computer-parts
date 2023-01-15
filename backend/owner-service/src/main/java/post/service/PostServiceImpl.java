package post.service;

import fmi.cloudcomputing.buyerservice.offer.presentation.OfferFilters;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import post.jpa.Post;
import post.jpa.PostRepository;
import post.jpa.PostStatus;
import post.presentation.*;
import user.jpa.ProductOwner;
import user.presentation.ProductOwnerDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final PostRestConsumer postRestConsumer;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, PostRestConsumer postRestConsumer, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.postRestConsumer = postRestConsumer;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostListingDto> getAllForCurrentUser() {
//        Authentication tine minte user-ul conectat, cel care a facut requestul
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProductOwner owner = (ProductOwner) auth.getPrincipal();

//        Trebuie sa definesti metoda asta in repository
//        E gen `findBy' + 'Numele Fieldului din @Entity' si ca argument dai valoarea dupa care sa se ia

        return postRepository.findByowner_id(owner.getId())
                .stream()
                .map(offer -> {
//                    Foloseste model mapper, ca e mai rapid decat sa pui de mana setChestie();
                    PostListingDto dto = modelMapper.map(offer, PostListingDto.class);

//                    dto.setPostId(offer.getPostSummary().getId());
//                    dto.setPostTitle(offer.getPostSummary().getTitle());
//                    dto.setRequestedPrice(offer.getPostSummary().getRequestPrice());
//                    dto.setOwnerUsername(offer.getPostSummary().getOwnerUsername());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public PostDto getById(Long offerId) {
        return postRepository.findById(offerId)
                .map(post ->{
                    PostDto dto = modelMapper.map(post, PostDto.class);
                    ProductOwner owner = post.getOwner();
                    dto.setProductOwner(modelMapper.map(owner, ProductOwner.class));
                    return dto;
                })
                .orElseThrow();
    }

    @Override
    public PostDto create(CreatePostDto dto) {
//        Asta nu functioneaza momentan, fiindca trebuie facut get by id in owner app;
//        PostDto postDto;
//        try {
//            postDto = postRestConsumer.getById(dto.getPostId()).getBody();
//            if (postDto == null) {
//                throw new NoSuchElementException(dto.getPostId());
//            }
//        } catch (Exception e) {
//            throw new NoSuchElementException(dto.getPostId());
//        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProductOwner owner = (ProductOwner) auth.getPrincipal();

        Post post = modelMapper.map(dto, Post.class);
        post.setOwner(owner);
        post.setStatus(PostStatus.AVAILABLE);
        post = PostRepository.save(post);
//        offer.setPostSummary(modelMapper.map(postDto, PostSummary.class));

        PostDto result = modelMapper.map(post, PostDto.class);
        result.setProductOwner(modelMapper.map(post, ProductOwner.class));

        return result;
    }

    @Override
    public PostDto update(Long id, UpdatePostDto dto) {
        return null;
    }



    @Override
    public PostDto update(long id, UpdatePostDto dto) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setPrice(dto.getPrice());
        if(dto.getStatus() != null) {
            post.setStatus(dto.getStatus());
        }
        post = postRepository.save(post);

        ProductOwner owner = Hibernate.unproxy(post.getOwner(), ProductOwner.class);
        ProductOwnerDto result = modelMapper.map(post, ProductOwnerDto.class);
        result.setPostdto(modelMapper.map(owner, PostDto.class));
        return result;
    }

    @Override
    public void delete(Long id) {
        postRepository.findById(id)
                .ifPresentOrElse(
                        postRepository::delete,
                        () -> {
                            throw new NoSuchElementException(String.valueOf(id));
                        });
    }

    @Override
    public List<PostListingDto> getAll(OfferFilters filters) {
        return postRepository.findAllByPostSummary_IdOrPostSummary_OwnerId(filters.getPostId(), filters.getOwnerId())
                .stream()
                .map(post ->{
//                    Foloseste model mapper, ca e mai rapid decat sa pui de mana setChestie();
                    PostListingDto dto = modelMapper.map(post, PostListingDto.class);

//                    dto.setPostId(offer.getPostSummary().getId());
//                    dto.setPostTitle(offer.getPostSummary().getTitle());
//                    dto.setRequestedPrice(offer.getPostSummary().getRequestPrice());
//                    dto.setOwnerUsername(offer.getPostSummary().getOwnerUsername());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
