package fmi.cloudcomputing.buyerservice.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostRestConsumer postRestConsumer;

    public PostController(PostRestConsumer postRestConsumer) {
        this.postRestConsumer = postRestConsumer;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostListingDto>> getPosts(){
        return postRestConsumer.getAllActive();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return postRestConsumer.getById(id);
    }
}
