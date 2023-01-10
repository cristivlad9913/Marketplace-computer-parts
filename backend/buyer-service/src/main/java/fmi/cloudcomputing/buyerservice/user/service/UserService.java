package fmi.cloudcomputing.buyerservice.user.service;

import fmi.cloudcomputing.buyerservice.user.presentation.CreateUserDto;
import fmi.cloudcomputing.buyerservice.user.presentation.UserDto;

//Always create an interface for the service, befor implementing it
public interface UserService {
    UserDto reqgisterUser(CreateUserDto dto);
}
