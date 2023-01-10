package fmi.cloudcomputing.buyerservice.user.service;

import fmi.cloudcomputing.buyerservice.user.jpa.User;
import fmi.cloudcomputing.buyerservice.user.jpa.UserRepository;
import fmi.cloudcomputing.buyerservice.user.presentation.CreateUserDto;
import fmi.cloudcomputing.buyerservice.user.presentation.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

//Always annotate the service implementation with @Service so that Spring knows how to autowire this
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

//    All components that you need in this class, make them private final ~  const fields
//    and add them to constructor
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto reqgisterUser(CreateUserDto dto) {
        User newUser = modelMapper.map(dto, User.class);
        newUser = userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }
}
