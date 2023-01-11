package fmi.cloudcomputing.buyerservice.user.service;

import fmi.cloudcomputing.buyerservice.user.jpa.User;
import fmi.cloudcomputing.buyerservice.user.jpa.UserRepository;
import fmi.cloudcomputing.buyerservice.user.presentation.CreateUserDto;
import fmi.cloudcomputing.buyerservice.user.presentation.LoginUserDto;
import fmi.cloudcomputing.buyerservice.user.presentation.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

//Always annotate the service implementation with @Service so that Spring knows how to autowire this
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    All components that you need in this class, make them private final ~  const fields
//    and add them to constructor
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto registerUser(CreateUserDto dto) {
        User newUser = modelMapper.map(dto, User.class);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser = userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() instanceof  User){
                return modelMapper.map(((User)auth.getPrincipal()), UserDto.class);
        }else{
            return userRepository
                    .findByUsername(auth.getName())
                    .map(user -> modelMapper.map(user, UserDto.class))
                    .orElseThrow(() -> new NoSuchElementException("Could not find user profile"));
        }
    }

    @Override
    public boolean userCredentialsValid(LoginUserDto loginUserDto) {
        return userRepository
                .findByUsername(loginUserDto.getUsername())
                .map(user -> passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword()))
                .orElse(false);
    }
}
