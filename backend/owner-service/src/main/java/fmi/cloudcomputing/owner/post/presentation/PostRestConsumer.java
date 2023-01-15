package fmi.cloudcomputing.owner.post.presentation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "OfferRestConsumer", url = "http://localhost:8080/internal")
public interface PostRestConsumer {
    @GetMapping("/offers")
    public ResponseEntity<List<PostDto>> getAllActive();

}
