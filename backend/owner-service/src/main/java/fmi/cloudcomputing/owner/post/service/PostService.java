package fmi.cloudcomputing.owner.post.service;

import fmi.cloudcomputing.owner.post.presentation.CreatePostDto;
import fmi.cloudcomputing.owner.post.presentation.PostDto;
import fmi.cloudcomputing.owner.post.presentation.PostListingDto;
import fmi.cloudcomputing.owner.post.presentation.UpdatePostDto;

import java.util.List;

public interface PostService {
    List<PostListingDto> getAllForCurrentUser();
    PostDto getById(Long offerID);
    PostDto create(CreatePostDto dto);

    void delete(Long id);

    PostDto update(long id, UpdatePostDto dto);


    List<PostListingDto> getAll();

}
