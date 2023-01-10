package ownerapp.restservice;

import org.springframework.stereotype.Repository;

@Repository
public class ProductOwnerRepository {
    public ProductOwnerRepository() {
    }

    public ProductOwner save(ProductOwner productOwner) {
        return productOwner;
    }

    public ProductOwner findByUsernameAndPassword(String username, String password) {
        return null;
    }

    public ProductOwner findByUsername(String username) {
        return null;
    }
}
