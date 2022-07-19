package com.projects.meetdeals.Service;

import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoriteService {

    private UserRepository userRepository;

    @Autowired
    public FavoriteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setFavoriteItem(User user, Item item) {
        user.getItemSet().add(item);
        userRepository.save(user);
    }

    public void unsetFavoriteItem(User user, Item item) {
        user.getItemSet().remove(item);
        userRepository.save(user);
    }

    public Set<Item> getFavoriteItems(User user) {
        return user.getItemSet();
    }
}