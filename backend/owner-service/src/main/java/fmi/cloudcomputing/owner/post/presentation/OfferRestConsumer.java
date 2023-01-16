package fmi.cloudcomputing.owner.post.presentation;

import fmi.cloudcomputing.buyerservice.offer.presentation.OfferFilters;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@FeignClient(name = "OfferRestConsumer", url = "http://localhost:8080/internal")
public interface OfferRestConsumer {
    @GetMapping("/offers")
    ResponseEntity<List<PostOfferDto>> getAllByPost(@SpringQueryMap OfferFilters offerFilters);

    @PostMapping("/offers/{id}")
    ResponseEntity<PostOfferDto> updateStatus(@PathVariable("id") long id, @RequestBody UpdatePostOfferDto dto);
}
