package com.cognizant.user.service;

import com.cognizant.user.model.User;
import com.cognizant.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                return null;
            }
        }
        userRepository.save(user);
        return user;
    }
}
