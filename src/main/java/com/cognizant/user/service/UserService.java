package com.cognizant.user.service;

import com.cognizant.user.model.Login;
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

    public User validateUser(Login login) {
        List<User> users = userRepository.findAll();

        User user = users.stream()
                .filter(u -> u.getEmail().equals(login.getEmail()) && u.getPassword().equals(login.getPassword()))
                .findFirst().orElse(null);
        if(user == null) return null;
        else return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User resetPassword(User user) {
        User user1 = userRepository.findByEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        System.out.println("I am at reset password service");
        return user1;
    }
}
