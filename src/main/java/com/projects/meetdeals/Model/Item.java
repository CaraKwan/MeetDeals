package com.projects.meetdeals.Model;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.util.HashSet;
        import java.util.Set;

@Entity
@Table(name = "item")
@JsonDeserialize(builder = Item.Builder.class)
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "seller_email")
    private User seller;

    private String name;
    private String zipcode;
    private int category;
    private int price;
    private int quality;
    private String description;
    private String image;
    private Integer rating;
    private int status;

    private String buyerEmail;

    private String uploadTime;
    private String soldTime;

    @JsonIgnore
    @ManyToMany(mappedBy = "itemSet")
    private Set<User> users = new HashSet<>();

    public Item() {}

    private Item(Builder builder) {
        this.id = builder.id;
        this.seller = builder.seller;
        this.name = builder.name;
        this.zipcode = builder.zipcode;
        this.category = builder.category;
        this.price = builder.price;
        this.quality = builder.quality;
        this.description = builder.description;
        this.image = builder.image;
        this.rating = builder.rating;
        this.status = builder.status;
        this.buyerEmail = builder.buyerEmail;
        this.uploadTime = builder.uploadTime;
        this.soldTime = builder.soldTime;
        this.users = builder.users;
    }

    public int getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public String getBuyerEmail() { return buyerEmail; }

    public String getName() {
        return name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public int getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuality() {
        return quality;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Integer getRating() {
        return rating;
    }

    public int getStatus() {
        return status;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getSoldTime() {
        return soldTime;
    }

    public Set<User> getUsers() {
        return users;
    }

    public static class Builder {
        @JsonProperty("id")
        private int id;

        @JsonProperty("seller")
        private User seller;

        @JsonProperty("name")
        private String name;

        @JsonProperty("zipcode")
        private String zipcode;

        @JsonProperty("category")
        private int category;

        @JsonProperty("price")
        private int price;

        @JsonProperty("quality")
        private int quality;

        @JsonProperty("description")
        private String description;

        @JsonProperty("image")
        private String image;

        @JsonProperty("rating")
        private Integer rating;

        @JsonProperty("status")
        private int status;

        @JsonProperty("buyer_email")
        private String buyerEmail;

        @JsonProperty("upload_time")
        private String uploadTime;

        @JsonProperty("sold_time")
        private String soldTime;

        private Set<User> users;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setSeller(User seller) {
            this.seller = seller;
            return this;
        }
        public Builder setBuyer(String buyerEmail) {
            this.buyerEmail = buyerEmail;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setZipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public Builder setCategory(int category) {
            this.category = category;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setQuality(int quality) {
            this.quality = quality;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setUploadTime(String uploadTime) {
            this.uploadTime = uploadTime;
            return this;
        }

        public Builder setSoldTime(String soldTime) {
            this.soldTime = soldTime;
            return this;
        }

        public Builder setUsers(Set<User> users) {
            this.users = users;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}