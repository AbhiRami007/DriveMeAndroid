package com.example.drivemeandroid.models;

public class Booking {
    private String driverName;
    private String profileImageUrl;
    private int bookingStatus;
    private int id;

    public Booking(String driverName,  int bookingStatus, int id) {
        this.driverName = driverName;
        this.bookingStatus = bookingStatus;
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }
    public int getId() {
        return id;
    }
}

