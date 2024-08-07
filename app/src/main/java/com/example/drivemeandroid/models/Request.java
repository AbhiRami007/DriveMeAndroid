package com.example.drivemeandroid.models;

public class Request {

    private String name;
    private String email;
    private String pickupLocation;
    private String dropOffLocation;
    private String price;

    private int rideId;

    private int bookingStatus;
    public Request(String name, String email, String pickupLocation, String dropOffLocation, String price, int rideId, int bookingStatus) {
        this.name = name;
        this.email = email;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.price = price;
        this.rideId = rideId;
        this.bookingStatus = bookingStatus;
    }


    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRideId() {
        return rideId;
    }
}
