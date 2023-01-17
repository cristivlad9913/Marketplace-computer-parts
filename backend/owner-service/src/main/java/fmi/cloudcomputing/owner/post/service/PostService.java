package fmi.cloudcomputing.owner.post.service;

import fmi.cloudcomputing.buyerservice.offer.presentation.OfferFilters;
import fmi.cloudcomputing.owner.post.presentation.*;

import java.util.List;

public interface PostService {
    List<PostListingDto> getAllForCurrentUser();
    PostDto getById(Long offerID);
    PostDto create(CreatePostDto dto);

    void delete(Long id);

    PostDto update(long id, UpdatePostDto dto);


    List<PostListingDto> getAll();

}
