package com.example.rishabh.aradmin.model;



public class AdDetails {
    private String Latitude;
    private String Longitude;
    private String adId;
    private String admin;
    private String customerAddress;
    private String customerName;
    private String price;


    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AdDetails(){

    }

    public AdDetails(String latitude, String longitude, String adId, String admin, String customerAddress, String customerName, String price) {
        Latitude = latitude;
        Longitude = longitude;
        this.adId = adId;
        this.admin = admin;
        this.customerAddress = customerAddress;
        this.customerName = customerName;
        this.price = price;
    }
}
