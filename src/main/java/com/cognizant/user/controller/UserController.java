package com.cognizant.user.controller;

import com.cognizant.user.model.Login;
import com.cognizant.user.model.User;
import com.cognizant.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity validateUser(@RequestBody Login login) {
        ObjectMapper mapper = new ObjectMapper();
        User user = userService.validateUser(login);

        if(user == null) {
            return new ResponseEntity<>("User doesn't Exist!", HttpStatus.UNPROCESSABLE_ENTITY);
        }


        String json = new String();
        try{
            json = mapper.writeValueAsString(user);
        }catch (Exception  e) {
            e.printStackTrace();
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createNewUser(@RequestBody User user) {

        User newUser = userService.createUser(user);

        if(newUser == null) {
            return new ResponseEntity<>("User Already Exist!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("User Created!", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public String useerService(){
        return "user service";
    }

}
