package com.aplicatie.booking.restcontroller;

import com.aplicatie.booking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aplicatie.booking.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginData) {
        return userService.authenticateUser(loginData.getEmail(), loginData.getPassword());
    }
}


