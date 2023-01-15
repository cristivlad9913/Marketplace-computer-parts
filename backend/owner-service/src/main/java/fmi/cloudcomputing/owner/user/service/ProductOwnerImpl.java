package fmi.cloudcomputing.owner.user.service;

import fmi.cloudcomputing.owner.user.jpa.ProductOwner;
import fmi.cloudcomputing.owner.user.jpa.ProductOwnerRepository;
import fmi.cloudcomputing.owner.user.presentation.CreateProductOwner;
import fmi.cloudcomputing.owner.user.presentation.LoginProductOwnerDto;
import fmi.cloudcomputing.owner.user.presentation.ProductOwnerDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductOwnerImpl implements ProductOwnerService {

    private final ProductOwnerRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ProductOwnerImpl(ProductOwnerRepository userRepository,
                            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ProductOwnerDto registerUser(CreateProductOwner dto) {
        ProductOwner newUser = modelMapper.map(dto, ProductOwner.class);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser = userRepository.save(newUser);
        return modelMapper.map(newUser, ProductOwnerDto.class);
    }

    @Override
    public ProductOwnerDto getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof  ProductOwner){
            return modelMapper.map(((ProductOwner)auth.getPrincipal()), ProductOwnerDto.class);
        }else{
            return userRepository
                    .findByUsername(auth.getName())
                    .map(user -> modelMapper.map(user, ProductOwnerDto.class))
                    .orElseThrow(() -> new NoSuchElementException("Could not find user profile"));
        }
    }

    @Override
    public ProductOwner updateProductOwnerProfile(ProductOwner productOwner) {
        return null;
    }

    @Override
    public boolean userCredentialsValid(LoginProductOwnerDto loginUserDto) {
        return userRepository
                .findByUsername(loginUserDto.getUsername())
                .map(user -> passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword()))
                .orElse(false);
    }

}
