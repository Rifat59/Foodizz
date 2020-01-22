package com.example.foodizz;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class AddRestaurent implements Serializable {
    private String Name, Location, url;
    private Double Latt, Long;

    public AddRestaurent(String Name, String Location, String url, Double Latt, Double Long) {
        this.Name = Name;
        this.Location = Location;
        this.url = url;
        this.Latt = Latt;
        this.Long = Long;
    }

    @PropertyName("Name")
    public String getName() {
        return Name;
    }

    @PropertyName("Name")
    public void setName(String Name) {
        this.Name = Name;
    }

    @PropertyName("Location")
    public String getLocation() {
        return Location;
    }

    @PropertyName("Location")
    public void setLocation(String Location) {
        this.Location = Location;
    }

    @PropertyName("url")
    public String getUrl() {
        return url;
    }

    @PropertyName("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @PropertyName("Latt")
    public Double getLatt() {
        return Latt;
    }

    @PropertyName("Latt")
    public void setLatt(Double Latt) {
        this.Latt = Latt;
    }

    @PropertyName("Long")
    public Double getLong() {
        return Long;
    }

    @PropertyName("Long")
    public void setLong(Double Long) {
        this.Long = Long;
    }
}
