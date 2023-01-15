package fmi.cloudcomputing.owner.user.service;


import fmi.cloudcomputing.owner.user.jpa.ProductOwner;
import fmi.cloudcomputing.owner.user.presentation.CreateProductOwner;
import fmi.cloudcomputing.owner.user.presentation.LoginProductOwnerDto;
import fmi.cloudcomputing.owner.user.presentation.ProductOwnerDto;

public interface ProductOwnerService {

    ProductOwnerDto registerUser(CreateProductOwner dto) ;

    ProductOwner updateProductOwnerProfile(ProductOwner productOwner);

    boolean userCredentialsValid(LoginProductOwnerDto loginProductOwner);
    ProductOwnerDto getCurrentUser();
}

