package post.service;

import fmi.cloudcomputing.buyerservice.offer.presentation.OfferFilters;
import post.presentation.CreatePostDto;
import post.presentation.PostDto;
import post.presentation.PostListingDto;
import post.presentation.UpdatePostDto;

import java.util.List;

public interface PostService {
    List<PostListingDto> getAllForCurrentUser();
    PostDto getById(Long offerID);
    PostDto create(CreatePostDto dto);

    void delete(Long id);

    PostDto update(long id, UpdatePostDto dto);


    List<PostListingDto> getAll(OfferFilters filters);
}
