package com.projects.meetdeals.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.meetdeals.Config.AmazonConfig;
import com.projects.meetdeals.Model.Item;
import com.projects.meetdeals.Model.ItemResp;
import com.projects.meetdeals.Model.User;
import com.projects.meetdeals.Service.ItemService;
import com.projects.meetdeals.Service.S3BucketStorageService;
import com.projects.meetdeals.Service.UserNotFoundException;
import com.projects.meetdeals.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {
    private ItemService itemService;
    private UserService userService;

    @Autowired
    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @RequestMapping(value="/item", method = RequestMethod.GET)
    public void getItem(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "name", required = false) String itemName,
                        @RequestParam(value = "seller_trans", required = false) String sellerTrans,
                        @RequestParam(value = "seller_list", required = false) String sellerList,
                        @RequestParam(value = "buyer", required = false) String buyer,
                        HttpServletResponse response) throws IOException {
        System.out.println("get a request");
        response.setContentType(("application/json; charset=UTF-8"));

        ItemResp item = null;
        List<ItemResp> items = new ArrayList<>();
        // default
        if(id == null && itemName == null && sellerTrans == null && sellerList == null && buyer == null) {
            items = itemService.findAll();
            // listing detail
        } else if(id != null && itemName == null && sellerTrans == null && sellerList == null && buyer == null) {
            item = itemService.findItemById(id);
            // search item by name
        } else if (id == null && itemName != null && sellerTrans == null && sellerList == null && buyer == null) {
            items = itemService.findListingsByName(itemName);
            // my listings (seller)
        } else if (id == null && itemName == null && sellerTrans == null && sellerList != null && buyer == null) {
            items = itemService.findListingsBySeller(sellerList);
            //transaction history (seller)
        } else if (id == null && itemName == null && sellerTrans != null && sellerList == null && buyer == null) {
            items = itemService.findTransactionsBySeller(sellerTrans);
            // transaction history (buyer)
        } else {
            items = itemService.findTransactionsByBuyer(buyer);
        }

        if(item != null) {
            response.getWriter().print(new ObjectMapper().writeValueAsString(item));
        } else {
            response.getWriter().print(new ObjectMapper().writeValueAsString(items));
        }
    }

    @RequestMapping(value = "/item/upload", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void uploadItem(@RequestParam("seller_email") String sellerEmail,
                           @RequestParam("item_name") String name,// not sure if front end has this field
                           @RequestParam("zipcode") String zipcode,
                           @RequestParam("category") int category,
                           @RequestParam("price") int price,
                           @RequestParam("condition") int condition,
                           @RequestParam("description") String description,
                           @RequestParam("image") MultipartFile image) throws UserNotFoundException {
        //logic
        User seller = userService.findUserByEmail(sellerEmail); // completed by cara
        if (seller == null) {
            throw new UserNotFoundException("Invalid user.");
        }
        AmazonConfig amazonConfig = new AmazonConfig();
        S3BucketStorageService s3 = new S3BucketStorageService();
        s3.amazonS3Client = amazonConfig.s3();
        String url = s3.uploadFile(name, image); // get the url of item image that will store in amazon s3
        Item.Builder builder = new Item.Builder();
        builder.setSeller(seller)
                .setName(name)
                .setZipcode(zipcode)
                .setCategory(category)
                .setPrice(price)
                .setQuality(condition)
                .setDescription(description)
                .setImage(url); // because item repo will store this url in string format
        Item item = builder.build();

        itemService.uploadItem(item);
    }


    @RequestMapping(value = "/item/edit", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void editItem(@RequestParam("item_id") int itemId, @RequestParam("price") int price) {
        //logic
        itemService.editItem(itemId, price);
    }
}

