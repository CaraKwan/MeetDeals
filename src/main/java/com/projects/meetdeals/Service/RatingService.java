package com.projects.meetdeals.Service;

import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class RatingService {
    private UserRepository userRepository;

    @Autowired
    public RatingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private HashMap<String, Integer> rating_times= new HashMap<>();

    public boolean checkItemStatus(Item item){
        int status=item.getStatus();
        return status == 2;
    }

    public void setUserRating(User user, Item item, int new_rating) {
        String email=user.getEmail();
        rating_times.put(email,rating_times.getOrDefault(email,1)+1);
        if(checkItemStatus(item)){
            user.setRatings((user.getRatings()+new_rating)/rating_times.get(email));
            userRepository.save(user);
        }
    }


}
