package com.projects.meetdeals.Service;

import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import java.util.List;

@Service
public class ItemStatusService {
    private ItemRepository itemRepository;
    private UserService userService;

    @Autowired
    public ItemStatusService(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    public List<Item> getItemsByStatus(int status) {
        return itemRepository.findByStatus(status);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void changeStatus(int itemId, String buyerEmail, int statusToChange, String soldTime) throws StatusChangeException {
        Item item = findItemById(itemId);
        if (item == null) {
            throw new StatusChangeException("Item id is invalid.");
        }

        int originStatus = item.getStatus();

        if (statusToChange == 0) {
            if (originStatus == 2) {
                throw new StatusChangeException("Item is already sold.");
            }
            buyerEmail = null;
            soldTime = null;
        } else if (statusToChange == 1) {
            if (originStatus != 0) {
                throw new StatusChangeException("Item is not available.");
            }
            User buyer = userService.findUserByEmail(buyerEmail);
            if (buyer == null) {
                throw new UserNotFoundException("Invalid User.");
            }
            soldTime = null;
        } else if (statusToChange == 2) {
            if (originStatus != 1) {
                throw new StatusChangeException("No pending buyer provided.");
            }
            if (soldTime == null || soldTime.isEmpty()) {
                soldTime = LocalDate.now().toString();
            }
            buyerEmail = item.getBuyerEmail();
        } else {
            throw new StatusChangeException("Status code to change is invalid.");
        }

        itemRepository.changeStatus(itemId, buyerEmail, statusToChange, soldTime);
    }

    public Item findItemById (Integer id) {
        return itemRepository.findItemById(id);
    }
}
