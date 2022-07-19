package com.projects.meetdeals.Service;

import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.ItemResp;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Repository.ItemRepository;
import com.projects.meetdeals.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, UserService userService, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<ItemResp> findAll() {
        List<ItemResp> resps = new ArrayList<>();
        List<Item> items = itemRepository.findListingsDefault();
        for (Item item : items) {
            resps.add(new ItemResp(item));
        }
        return resps;
    }

    public ItemResp findItemById (Integer id) {

        Item item = itemRepository.findItemById(id);
        ItemResp resp = new ItemResp(item);
        if(resp.getBuyerEmail() != null) {
            User buyer = userRepository.findByEmail(resp.getBuyerEmail());//findById(resp.getBuyerEmail());
            System.out.println(buyer.getUserName());
            resp.setBuyerName(buyer.getUserName());
        }
        return resp;
    }

    public List<ItemResp> findListingsByName (String itemName) {
        List<ItemResp> resps = new ArrayList<>();
        List<Item> items = itemRepository.findListingsByName(itemName);
        for (Item item : items) {
            resps.add(new ItemResp(item));
        }
        return resps;

    }

    public List<ItemResp> findListingsBySeller (String sellerEmail) {
        List<ItemResp> resps = new ArrayList<>();
        List<Item> items = itemRepository.findListingsBySeller(sellerEmail);
        for (Item item : items) {
            ItemResp resp = new ItemResp(item);
            if (resp.getBuyerEmail() != null) {
                User buyer = userRepository.findByEmail(resp.getBuyerEmail());//findById(resp.getBuyerEmail());
                System.out.println(buyer.getUserName());
                resp.setBuyerName(buyer.getUserName());
                resps.add(resp);
            }

        }
        return resps;

    }

    public List<ItemResp> findTransactionsBySeller (String sellerEmail) {
        List<ItemResp> resps = new ArrayList<>();
        List<Item> items = itemRepository.findTransactionsBySeller(sellerEmail);
        for (Item item : items) {
            ItemResp resp = new ItemResp(item);
            User buyer = userRepository.findByEmail(resp.getBuyerEmail());//findById(resp.getBuyerEmail());
            System.out.println(buyer.getUserName());
            resp.setBuyerName(buyer.getUserName());
            resps.add(resp);
        }
        return resps;

    }

    public List<ItemResp> findTransactionsByBuyer (String buyerEmail) {
        List<ItemResp> resps = new ArrayList<>();
        List<Item> items = itemRepository.findTransactionsByBuyer(buyerEmail);
        for (Item item : items) {
            resps.add(new ItemResp(item));
        }
        return resps;
    }

    public void uploadItem(Item item) {
        //logic
        itemRepository.save(item);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void editItem(int itemId, int price) throws ItemNotExitException {
        Item item = itemRepository.findItemById(itemId);
        if (item == null) {
            throw new ItemNotExitException("Item is invalid.");
        }
        itemRepository.editItem(itemId, price);

    }
}
