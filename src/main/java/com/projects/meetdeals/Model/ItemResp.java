package com.projects.meetdeals.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemResp {
    @JsonProperty("item_id")
    private int id;

    @JsonProperty("seller_email")
    private String sellerEmail;

    @JsonProperty("seller_name")
    private String sellerName;

    @JsonProperty("item_name")
    private String name;

    private String zipcode;

    private int category;

    private int price;

    private int quality;

    private String description;

    private String image;

    @JsonProperty("seller_rating")
    private Integer sellerRating;

    @JsonProperty("status")
    private int status;


    @JsonProperty("buyer_email")
    private String buyerEmail;


    @JsonProperty("buyer_name")
    private String buyerName;

    @JsonProperty("upload_time")
    private String uploadTime;

    @JsonProperty("sold_time")
    private String soldTime;

    public ItemResp(Item item) {
        this.id = item.getId();
        this.sellerEmail = item.getSeller().getEmail();
        this.sellerName = item.getSeller().getUserName();
        this.sellerRating = item.getSeller().getRatings();
        this.name = item.getName();
        this.zipcode = item.getZipcode();
        this.category = item.getCategory();
        this.price = item.getPrice();
        this.quality = item.getQuality();
        this.description = item.getDescription();
        this.status = item.getStatus();
        this.image = item.getImage();
        this.uploadTime = item.getUploadTime();
        this.soldTime = item.getSoldTime();
        this.buyerEmail = item.getBuyerEmail();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(Integer sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getSoldTime() {
        return soldTime;
    }

    public void setSoldTime(String soldTime) {
        this.soldTime = soldTime;
    }
}
