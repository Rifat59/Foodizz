package com.example.foodizz;

public class CustomerInfo {

    private String name, address, phoneNo, resname, itemname;
    private int quantity;

    public CustomerInfo(String name, String address, String phoneNo, String resname, String itemname, int quantity) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.resname = resname;
        this.itemname = itemname;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
