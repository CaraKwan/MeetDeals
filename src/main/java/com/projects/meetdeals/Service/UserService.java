package com.projects.meetdeals.Service;

import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }
}

