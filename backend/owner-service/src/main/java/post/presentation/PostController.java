package post.presentation;

import fmi.cloudcomputing.buyerservice.offer.presentation.*;
import fmi.cloudcomputing.buyerservice.offer.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import post.service.PostService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
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

    //    Endpoint astea o sa fie apelate din owner-app
//    Don't mind these

//    @GetMapping("/posts")
//    public ResponseEntity<List<OfferListingDto>>getAll(OfferFilters filters){
//        return ResponseEntity.ok(postService.getAll(filters));
//    }
//
//    @PostMapping("/posts/{id}/status")
//    public ResponseEntity<OfferDto> updateStatus(@PathVariable long id,
//                                                 @RequestBody UpdateOfferDto dto){
//        return ResponseEntity.ok(offerService.update(id, dto));
//    }

}
