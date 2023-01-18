package fmi.cloudcomputing.owner.post.presentation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "OfferRestConsumer", url = "http://${internal.buyerUrl}:8080/internal")
public interface OfferRestConsumer {
    @GetMapping("/offers")
    ResponseEntity<List<PostOfferDto>> getAllByPost(@SpringQueryMap OfferFilters offerFilters);

    @PostMapping("/offers/{id}")
    ResponseEntity<PostOfferDto> updateStatus(@PathVariable("id") long id, @RequestBody UpdatePostOfferDto dto);
}
