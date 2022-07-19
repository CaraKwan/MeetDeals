package com.projects.meetdeals.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonDeserialize(builder = User.Builder.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String email;

    @NotNull
    private String userName;

    @NotNull
    private String realName;

    @NotNull
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String matchingPassword;

    private int ratings;

    @JsonIgnore
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "favorites", joinColumns = { @JoinColumn(name = "user_email")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    //columns: email and item_id
    private Set<Item> itemSet = new HashSet<>();

    public User() {}

    private User(Builder builder) {
        this.email = builder.email;
        this.userName = builder.userName;
        this.realName = builder.realName;
        this.password = builder.password;
        this.matchingPassword = builder.matchingPassword;
        this.ratings = builder.ratings;
        this.enabled = builder.enabled;
        this.itemSet = builder.itemSet;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public int getRatings() {
        return ratings;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public static class Builder {
        @JsonProperty("email")
        private String email;

        @JsonProperty("username")
        private String userName;

        @JsonProperty("real_name")
        private String realName;

        @JsonProperty("password")
        private String password;

        @JsonProperty("matching_password")
        private String matchingPassword;

        @JsonProperty("ratings")
        private int ratings;

        @JsonProperty("enabled")
        private boolean enabled;

        private Set<Item> itemSet;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setRealName(String realName) {
            this.realName = realName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setMatchingPassword(String matchingPassword) {
            this.matchingPassword = matchingPassword;
            return this;
        }

        public Builder setRatings(int ratings) {
            this.ratings = ratings;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setItemSet(Set<Item> itemSet) {
            this.itemSet = itemSet;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
