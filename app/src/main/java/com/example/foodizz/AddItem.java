package com.example.foodizz;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class AddItem implements Serializable {
    private String Name, Price, Rating, url;

    public AddItem(String name, String price, String rating, String url) {
        Name = name;
        Price = price;
        Rating = rating;
        this.url = url;
    }

    @PropertyName("Name")
    public String getName() {
        return Name;
    }

    @PropertyName("Name")
    public void setName(String name) {
        Name = name;
    }

    @PropertyName("Price")
    public String getPrice() {
        return Price;
    }

    @PropertyName("Price")
    public void setPrice(String price) {
        Price = price;
    }

    @PropertyName("Rating")
    public String getRating() {
        return Rating;
    }

    @PropertyName("Rating")
    public void setRating(String rating) {
        Rating = rating;
    }

    @PropertyName("url")
    public String getUrl() {
        return url;
    }

    @PropertyName("url")
    public void setUrl(String url) {
        this.url = url;
    }
}
