package com.projects.meetdeals.Controller;

import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Service.ItemStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemStatusController {
    private ItemStatusService itemStatusService;

    @Autowired
    public ItemStatusController(ItemStatusService itemStatusService) {
        this.itemStatusService = itemStatusService;
    }

    //Return a list of items based on status
    @RequestMapping(value = "/item/status/{status}", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItemsByStatus(@PathVariable int status) {
        return itemStatusService.getItemsByStatus(status);
    }

    //Change item status to available, pending or sold, and update corresponding information
    @RequestMapping(value = "/item/status", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeStatus(@RequestParam("item") int itemId,
                          @RequestParam("buyer") String buyerEmail,
                          @RequestParam("status") int statusToChange,
                          @RequestParam("time") String soldTime) {
        itemStatusService.changeStatus(itemId, buyerEmail, statusToChange, soldTime);
    }
}
