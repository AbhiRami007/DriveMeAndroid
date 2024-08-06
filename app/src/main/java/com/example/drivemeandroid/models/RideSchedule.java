package com.example.drivemeandroid.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import java.util.concurrent.atomic.AtomicInteger;

@Entity(tableName = "ride_schedule")
public class RideSchedule {
    @PrimaryKey(autoGenerate = true)
    private int rideId;

    @ColumnInfo(name = "booking_status")
    private int bookingStatus;

    private String date;

    @ColumnInfo(name = "driver_id")
    private int driverId;

    @ColumnInfo(name = "drop_off_location")
    private String dropOffLocation;

    private String instructions;

    @ColumnInfo(name = "pick_up_location")
    private String pickUpLocation;

    @ColumnInfo(name = "passenger_id")
    private int passengerId;

    @ColumnInfo(name = "vehicle_id")
    private int vehicleId;

    private String time;

    // No-argument constructor
    public RideSchedule() {
    }

    // Constructor with parameters matching field names
    public RideSchedule(int rideId, int bookingStatus, String date, int driverId, String dropOffLocation,
                        String instructions, String pickUpLocation, int passengerId, int vehicleId, String time) {
        this.rideId = rideId;
        this.bookingStatus = bookingStatus;
        this.date = date;
        this.driverId = driverId;
        this.dropOffLocation = dropOffLocation;
        this.instructions = instructions;
        this.pickUpLocation = pickUpLocation;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.time = time;
    }

    // Getters and setters
    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
