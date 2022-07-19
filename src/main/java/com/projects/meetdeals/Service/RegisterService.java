package com.projects.meetdeals.Service;

import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projects.meetdeals.Repository.UserRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
     }

     @Transactional(isolation = Isolation.SERIALIZABLE)
     public void add(User user) throws UserAlreadyExistException {
        if(userRepository.existsById(user.getEmail())){
            throw new UserAlreadyExistException("User already exists");
        }
        userRepository.save(user);
     }
}
