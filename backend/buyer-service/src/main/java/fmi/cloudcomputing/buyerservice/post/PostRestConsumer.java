package fmi.cloudcomputing.buyerservice.post;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PostRestConsumer", url = "http://localhost:8081")
public interface PostRestConsumer {
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllActive();

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") String id);
}
