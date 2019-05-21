package com.cognizant.user.controller;

import com.cognizant.user.model.Login;
import com.cognizant.user.model.User;
import com.cognizant.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class UserController {

    private UserService userService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity validateUser(@RequestBody Login login) {

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

    @GetMapping("/allusers")
    public ResponseEntity userService(){
        List<User> users = userService.getAllUsers();

        String json = new String();
        try{
            json = mapper.writeValueAsString(users);
        }catch (Exception  e) {
            e.printStackTrace();
        }
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @PutMapping("/resetpassword")
    public ResponseEntity resetUserPassword(@RequestBody User user) {
        User returnedUser = userService.resetPassword(user);

        String json = new String();
        try{
            json = mapper.writeValueAsString(returnedUser);
        }catch (Exception  e) {
            e.printStackTrace();
        }

        return new ResponseEntity(json, HttpStatus.OK);
    }

}
