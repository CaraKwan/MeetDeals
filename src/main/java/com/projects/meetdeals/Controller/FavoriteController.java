package com.projects.meetdeals.Controller;

import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class FavoriteController {

    private FavoriteService favoriteService;
    private UserService userService;
    private ItemStatusService itemStatusService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService, UserService userService, ItemStatusService itemStatusService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
        this.itemStatusService = itemStatusService;
    }

    //Add an item to favorite cart
    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void setFavoriteItem(@RequestParam("email") String userEmail, @RequestParam("item_id") int itemId) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("Invalid User.");
        }

        Item item = itemStatusService.findItemById(itemId);
        if (item == null) {
            throw new StatusChangeException("Item id is invalid.");
        }

        favoriteService.setFavoriteItem(user, item);
    }

    //Remove an favorite item
    @RequestMapping(value = "/favorite", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void unsetFavoriteItem(@RequestParam("email") String userEmail, @RequestParam("item_id") int itemId) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("Invalid User.");
        }

        Item item = itemStatusService.findItemById(itemId);
        if (item == null) {
            throw new StatusChangeException("Item id is invalid.");
        }

        favoriteService.unsetFavoriteItem(user, item);
    }

    //Get all favorite items of a user
    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    @ResponseBody
    public Set<Item> getFavoriteItem(@RequestParam("email") String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("Invalid User.");
        }
        return favoriteService.getFavoriteItems(user);
    }
}
