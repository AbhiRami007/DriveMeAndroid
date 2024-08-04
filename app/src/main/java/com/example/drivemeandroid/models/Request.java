package com.example.drivemeandroid.models;

public class Request {

    private final String name;
    private final String email;
    private final String pickupLocation;
    private final String dropOffLocation;
    private final String price;

    public Request(String name, String email, String pickupLocation, String dropOffLocation, String price) {
        this.name = name;
        this.email = email;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public String getPrice() {
        return price;
    }
}
