package fmi.cloudcomputing.buyerservice.user.presentation;

import fmi.cloudcomputing.buyerservice.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//THis is a generic root path that this controller handles
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    //    All components that you need in this class, make them private final ~  const fields
    //    and add them to constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    Path will be localhost:8080/users/register
//    This tells me that the request is POST
//    @RequestBody tells me that I expect the JSON in the body of the request to look like the class
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody CreateUserDto createUserDto){
        UserDto out = userService.reqgisterUser(createUserDto);
//        ResponseEntity is a wrapper that adds HTTP stuff to your response
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

}
