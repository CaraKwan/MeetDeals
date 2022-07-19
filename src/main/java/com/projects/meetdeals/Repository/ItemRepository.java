package com.projects.meetdeals.Repository;

import com.projects.meetdeals.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// 0- open; 1- pending; 2- sold

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT i FROM Item i WHERE i.status = 0 ORDER BY i.uploadTime DESC") //i.id,i.name,i.price,i.zipcode,i.image
    List<Item> findListingsDefault();

    @Query(value = "SELECT i FROM Item i WHERE i.id = ?1") //i.id,i.name,i.description,i.status,i.price,i.zipcode,i.category,i.quality,i.image
    Item findItemById(int id);

    @Query(value = "SELECT i FROM Item i WHERE i.name LIKE %?1% And i.status = 0 ORDER BY i.uploadTime DESC") //i.id,i.name,i.price,i.zipcode,i.image
    List<Item> findListingsByName(String itemName);

    @Query(value = "SELECT i FROM Item i WHERE i.seller.email =?1 AND i.status <= 1 ORDER BY i.uploadTime DESC") //i.id,i.name,i.price,i.status,i.buyer.email,i.buyer.userName i.uploadTime
    List<Item> findListingsBySeller(String sellerEmail);

    @Query(value = "SELECT i FROM Item i WHERE i.seller.email =?1 AND i.status = 2 ORDER BY i.soldTime DESC") //i.id,i.name,i.price,i.status,i.soldTime,i.seller.email,i.seller.userName
    List<Item> findTransactionsBySeller(String sellerEmail);

    @Query(value = "SELECT i FROM Item i WHERE i.buyerEmail =?1 AND i.status = 2 ORDER BY i.soldTime DESC") //i.id,i.name,i.price,i.soldTime,i.buyer.email,i.buyer.userName
    List<Item> findTransactionsByBuyer(String buyerEmail);

    List<Item> findByStatus(int status);

    @Modifying
    @Query(value = "UPDATE Item it SET it.buyerEmail = ?2, it.status = ?3, it.soldTime = ?4 WHERE it.id = ?1")
    void changeStatus(int itemId, String buyerEmail, int statusToChange, String soldTime);

    @Modifying
    @Query(value = "UPDATE Item it SET it.price = ?2 WHERE it.id = ?1")
    void editItem(int itemId, int price);
}
