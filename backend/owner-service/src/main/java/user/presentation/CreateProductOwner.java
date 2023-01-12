package user.presentation;

import user.jpa.ProductOwner;

public interface CreateProductOwner {
    ProductOwner registerUser(CreateProductOwner productOwner);

    ProductOwner getCurrentUser();

    boolean userCredentialsValid(ProductOwner productOwner);
}
