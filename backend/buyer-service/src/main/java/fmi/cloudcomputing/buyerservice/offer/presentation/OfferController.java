package fmi.cloudcomputing.buyerservice.offer.presentation;

import fmi.cloudcomputing.buyerservice.offer.jpa.Offer;
import fmi.cloudcomputing.buyerservice.offer.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

//    CREATE
    @PostMapping("/offers")
    public ResponseEntity<OfferDto> createOffer(@RequestBody CreateOfferDto dto){
        return ResponseEntity.ok(offerService.create(dto));
    }

    @GetMapping("/my-offers")
    public ResponseEntity<List<OfferListingDto>> getAll(){
        return ResponseEntity.ok(offerService.getAllForCurrentUser());
    }

//    cu {} zici ca e o variabila in URL
//    Si daca pui un argument al metodei annotat cu @PathVariable, o sa ti-l deduca din URL
    @GetMapping("/offers/{id}")
    public ResponseEntity<OfferDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getById(id));
    }

//    UPDATE
    @PatchMapping("/offers/{id}")
    public ResponseEntity<OfferDto> updatePrice(@PathVariable long id,
                                                @RequestBody UpdateOfferDto dto){
        return ResponseEntity.ok(offerService.update(id, dto));
    }

//    DELETE
    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        try{
            offerService.delete(id);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //    Endpoint astea o sa fie apelate din owner-app
//    Don't mind these

    @GetMapping("/offers")
    public ResponseEntity<List<OfferListingDto>>getAll(OfferFilters filters){
        return ResponseEntity.ok(offerService.getAll(filters));
    }

    @PostMapping("/offers/{id}/status")
    public ResponseEntity<OfferDto> updateStatus(@PathVariable long id,
                                                 @RequestBody UpdateOfferDto dto){
        return ResponseEntity.ok(offerService.update(id, dto));
    }
}
