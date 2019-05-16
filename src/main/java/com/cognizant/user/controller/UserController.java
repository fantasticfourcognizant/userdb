package com.cognizant.user.controller;

import com.cognizant.user.model.User;
import com.cognizant.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createNewUser(@RequestBody User user) {

        User newUser = userService.createUser(user);

        if(newUser == null) {
            return new ResponseEntity<>("User Already Exists!", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>("User Created!", HttpStatus.CREATED);
    }
}
