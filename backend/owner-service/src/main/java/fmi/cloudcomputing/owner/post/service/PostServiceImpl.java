package fmi.cloudcomputing.owner.post.service;

import fmi.cloudcomputing.buyerservice.offer.presentation.OfferFilters;
import fmi.cloudcomputing.owner.post.jpa.*;
import fmi.cloudcomputing.owner.post.presentation.*;
import fmi.cloudcomputing.owner.user.jpa.ProductOwner;
import fmi.cloudcomputing.owner.user.presentation.ProductOwnerDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final ItemRepository itemRepository;
    private final PostOfferRepository postOfferRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,
                           PostRestConsumer postRestConsumer,
                           ItemRepository itemRepository,
                           PostOfferRepository postOfferRepository,
                           ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.itemRepository = itemRepository;
        this.postOfferRepository = postOfferRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostListingDto> getAllForCurrentUser() {
//        Authentication tine minte user-ul conectat, cel care a facut requestul
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProductOwner owner = (ProductOwner) auth.getPrincipal();

//        Trebuie sa definesti metoda asta in repository
//        E gen `findBy' + 'Numele Fieldului din @Entity' si ca argument dai valoarea dupa care sa se ia

        return postRepository.findAllByOwner_Id(owner.getId())
                .stream()
                .map(offer -> {
//                    Foloseste model mapper, ca e mai rapid decat sa pui de mana setChestie();
                    PostListingDto dto = modelMapper.map(offer, PostListingDto.class);
                    dto.setOwnerId(owner.getId());
                    dto.setOwnerUsername(owner.getUsername());
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
                    dto.setOwner(modelMapper.map(owner, ProductOwnerDto.class));
                    final List<ItemDto> items = itemRepository.findAllByPost(post)
                            .stream()
                            .map(item -> modelMapper.map(item, ItemDto.class))
                            .collect(Collectors.toList());
                    dto.setItems(items);
                    return dto;
                })
                .orElseThrow();
    }

    @Override
    public PostDto create(CreatePostDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProductOwner owner = (ProductOwner) auth.getPrincipal();

        Post post = modelMapper.map(dto, Post.class);
        post.setOwner(owner);
        post.setStatus(PostStatus.AVAILABLE);
        post.setTotal(dto.getItems().stream().map(CreateItemDto::getPrice).reduce((double) 0, Double::sum));
        final Post savedPost = postRepository.save(post);

        List<Item> items = dto
                .getItems()
                .stream()
                .map(createItemDto -> {
                    Item newItem = modelMapper.map(createItemDto, Item.class);
                    newItem.setPost(savedPost);
                    return newItem;
                })
                .collect(Collectors.toList());
        items = itemRepository.saveAll(items);

        PostDto result = modelMapper.map(post, PostDto.class);
        result.setOwner(modelMapper.map(owner, ProductOwnerDto.class));
        result.setItems(items.stream().map(item -> modelMapper.map(item, ItemDto.class)).collect(Collectors.toList()));

        return result;
    }


    @Override
    public PostDto update(long id, UpdatePostDto dto) {
        Post post = postRepository.findById(id).orElseThrow();
        modelMapper.map(dto, post);
        final Post updatedPost = postRepository.save(post);
        final List<Item> toDelete = itemRepository
                .findAllByPost(updatedPost)
                .stream()
                .filter(presentItem -> {
                    return (dto.getItems().stream().noneMatch(i -> i.getId() == presentItem.getId()));
                })
                .collect(Collectors.toList());

        List<Item> items = dto
                .getItems()
                .stream()
                .map(updateItemDto -> {

                    Item updatedItem = itemRepository.findById(updateItemDto.getId()).orElse(null);
                    if(updatedItem == null){
                        updatedItem = modelMapper.map(updateItemDto, Item.class);
                        updatedItem.setPost(updatedPost);
                    }else {
                        modelMapper.map(updateItemDto, updatedItem);
                    }
                    return updatedItem;
                })
                .collect(Collectors.toList());
        itemRepository.deleteAll(toDelete);
        items = itemRepository.saveAll(items);

        PostDto result = modelMapper.map(updatedPost, PostDto.class);
        result.setOwner(modelMapper.map(post.getOwner(), ProductOwnerDto.class));
        result.setItems(items.stream().map(item -> modelMapper.map(item, ItemDto.class)).collect(Collectors.toList()));

        return result;
    }

    @Override
    public void delete(Long id) {
        itemRepository.findAllByPost_Id(id)
                .forEach(itemRepository::delete);
        postRepository.findById(id)
                .ifPresentOrElse(
                        postRepository::delete,
                        () -> {
                            throw new NoSuchElementException(String.valueOf(id));
                        });
    }

    @Override
    public List<PostListingDto> getAll(OfferFilters filters) {
        //return postRepository.findAllByPostSummary_IdOrPostSummary_OwnerId(filters.getPostId(), filters.getOwnerId());
//                .stream()
//                .map(post ->{
////                    Foloseste model mapper, ca e mai rapid decat sa pui de mana setChestie();
//                    PostListingDto dto = modelMapper.map(post, PostListingDto.class);
//
////                    dto.setPostId(offer.getPostSummary().getId());
////                    dto.setPostTitle(offer.getPostSummary().getTitle());
////                    dto.setRequestedPrice(offer.getPostSummary().getRequestPrice());
////                    dto.setOwnerUsername(offer.getPostSummary().getOwnerUsername());
//                    return dto;
//                })
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public void addOfferById(Long id, PostOfferDto postOfferDto) {
        Post post = postRepository.findById(id).orElseThrow();
        PostOffer postOffer = modelMapper.map(postOfferDto, PostOffer.class);
        postOffer.setStatus("PENDING");
        postOffer.setPost(post);
        postOfferRepository.save(postOffer);

    }

    @Override
    public List<PostOfferDto> getAllOffersById(long id) {
        return postOfferRepository.findAllByPost_Id(id)
                .stream().map(po -> modelMapper.map(po, PostOfferDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostOfferDto updatePostOfferStatus(long id, long offerId, UpdatePostOfferDto updatePostOfferDto) {
        PostOffer postOffer = postOfferRepository.findById(offerId).orElseThrow();
        postOffer.setStatus(updatePostOfferDto.getStatus());
        postOffer = postOfferRepository.save(postOffer);
        return modelMapper.map(postOffer, PostOfferDto.class);
    }
}
