package com.projects.meetdeals.Controller;

import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class RatingController {
    private RatingService ratingService;
    private UserService userService;
    private ItemStatusService itemStatusService;

    @Autowired
    public RatingController(RatingService ratingService,UserService userService,ItemStatusService itemStatusService) {
        this.ratingService=ratingService;
        this.userService = userService;
        this.itemStatusService = itemStatusService;
    }


    @RequestMapping(value = "/user/rating", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void setUserRating(@RequestParam("email") String userEmail, @RequestParam("item_id") int itemId,@RequestParam("ratings") int ratings) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("Invalid User.");
        }

        Item item = itemStatusService.findItemById(itemId);
        if (item == null) {
            throw new StatusChangeException("Item id is invalid.");
        }

        ratingService.setUserRating(user, item,ratings);
    }

}
