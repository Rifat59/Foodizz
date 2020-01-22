package com.example.foodizz;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Restaurent implements Parcelable {

    public String Name;
    public String Location;
    public String url;
    public double Long;
    public double Latt;

    ArrayList<String> foodIds;

    public Restaurent ()
    {

    }
   // public Restaurent(String name, String location, Double latt, Double aLong, String url){
//
   // }

    /*public Restaurent(String name, String location, double longi, double latti) {
        this.name = name;
        this.location = location;
        this.longi = longi;
        this.latti = latti;
    }*/

    protected Restaurent(Parcel in) {
        Name = in.readString();
        Location = in.readString();
        url = in.readString();
        Long = in.readDouble();
        Latt = in.readDouble();
    }

    public static final Creator<Restaurent> CREATOR = new Creator<Restaurent>() {
        @Override
        public Restaurent createFromParcel(Parcel in) {
            return new Restaurent(in);
        }

        @Override
        public Restaurent[] newArray(int size) {
            return new Restaurent[size];
        }
    };

    public LatLng getLatLng() {
        return new LatLng(Latt, Long);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Location);
        dest.writeString(url);
        dest.writeDouble(Long);
        dest.writeDouble(Latt);
    }

    @Override
    public String toString() {
        return "Restaurent{" +
                "Name='" + Name + '\'' +
                ", Location='" + Location + '\'' +
                ", url='" + url + '\'' +
                ", Long=" + Long +
                ", Latt=" + Latt +
                '}';
    }
}
