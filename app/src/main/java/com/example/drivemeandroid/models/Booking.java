package com.example.drivemeandroid.models;

public class Booking {
    private String driverName;
    private String profileImageUrl;
    private int bookingStatus;

    public Booking(String driverName,  int bookingStatus) {
        this.driverName = driverName;
        this.bookingStatus = bookingStatus;
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
}

