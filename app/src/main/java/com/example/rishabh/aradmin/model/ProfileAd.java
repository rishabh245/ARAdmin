package com.example.rishabh.aradmin.model;



public class ProfileAd {
        private String name;
        private String address;
        private String price;

    public ProfileAd(String name, String address, String price) {
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
