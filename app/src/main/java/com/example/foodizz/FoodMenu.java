package com.example.foodizz;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodMenu implements Parcelable {

    public String Name;
    public String Rating;
    public String Price;
    public String url;

    public FoodMenu(){

    }

    protected FoodMenu(Parcel in) {
        Name = in.readString();
        Rating = in.readString();
        Price = in.readString();
        url = in.readString();
    }

    public static final Creator<FoodMenu> CREATOR = new Creator<FoodMenu>() {
        @Override
        public FoodMenu createFromParcel(Parcel in) {

            return new FoodMenu(in);
        }

        @Override
        public FoodMenu[] newArray(int size) {

            return new FoodMenu[size];
        }
    };

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Rating);
        dest.writeString(Price);
        dest.writeString(url);
    }

    @Override
    public String toString() {
        return "FoodMenu{" +
                "Name='" + Name + '\'' +
                ", Rating='" + Rating + '\'' +
                ", Price='" + Price + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
