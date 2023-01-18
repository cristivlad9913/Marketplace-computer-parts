package fmi.cloudcomputing.owner.post.presentation;

import fmi.cloudcomputing.owner.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PostController {
    private final PostService postService;
    private final OfferRestConsumer offerRestConsumer;
    public PostController(PostService postService, OfferRestConsumer offerRestConsumer) {
        this.postService = postService;
        this.offerRestConsumer = offerRestConsumer;
    }

    //    CREATE
    @PostMapping("/posts")
    public ResponseEntity<PostDto> createOffer(@RequestBody CreatePostDto dto){
        return ResponseEntity.ok(postService.create(dto));
    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<PostListingDto>> getAll(){
        return ResponseEntity.ok(postService.getAllForCurrentUser());
    }

    //    cu {} zici ca e o variabila in URL
//    Si daca pui un argument al metodei annotat cu @PathVariable, o sa ti-l deduca din URL
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }


    //    UPDATE
    @PatchMapping("/posts/{id}")
    public ResponseEntity<PostDto> updatePrice(@PathVariable long id,
                                                @RequestBody UpdatePostDto dto){
        return ResponseEntity.ok(postService.update(id, dto));
    }


    //    DELETE
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        try{
            postService.delete(id);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @GetMapping("/posts/{id}/offers")
    public ResponseEntity<List<PostOfferDto>> getOffers(@PathVariable long id){
        OfferFilters filters = new OfferFilters();
        filters.setPostId(id);
        return offerRestConsumer.getAllByPost(filters);
    }


    @PatchMapping("/posts/{id}/offers/{offerId}")
    public ResponseEntity<PostOfferDto> updateStatus(@PathVariable long id,
                                                     @PathVariable("offerId") long offerId,
                                                     @RequestBody UpdatePostOfferDto updatePostOfferDto){
        return offerRestConsumer.updateStatus(offerId, updatePostOfferDto);
    }

}
