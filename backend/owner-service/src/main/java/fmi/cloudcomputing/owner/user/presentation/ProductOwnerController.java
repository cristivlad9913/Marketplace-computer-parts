package fmi.cloudcomputing.owner.user.presentation;

import fmi.cloudcomputing.owner.user.service.ProductOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductOwnerController {

    private final ProductOwnerService productOwnerService;

    public ProductOwnerController(ProductOwnerService productOwnerService) {
        this.productOwnerService = productOwnerService;

    }
    @PostMapping("/register")
    public ResponseEntity<ProductOwnerDto> registerUser(@RequestBody CreateProductOwner createUserDto){
        ProductOwnerDto out = productOwnerService.registerUser(createUserDto);
//        ResponseEntity is a wrapper that adds HTTP stuff to your response
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginProductOwnerDto loginProductOwner) {
        final boolean isSuccessfulLogin = productOwnerService.userCredentialsValid(loginProductOwner);
        return ResponseEntity.status(isSuccessfulLogin ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).build();
    }

//    @GetMapping("/offers")
//    public List<Offer> getAllOffers() {
//        return productOwnerService.getAllOffers();
//    }
//
//    @PostMapping("/offers/{offerId}/accept")
//    public Offer acceptOffer(@PathVariable Long offerId) {
//        return productOwnerService.acceptOffer(offerId);
//    }
//
//    @PostMapping("/offers/{offerId}/decline")
//    public Offer declineOffer(@PathVariable Long offerId) {
//        return productOwnerService.declineOffer(offerId);
//    }

    @GetMapping("/profile")
    public ResponseEntity<ProductOwnerDto> getProductOwnerProfile() {
        return ResponseEntity.ok(productOwnerService.getCurrentUser());
    }
}