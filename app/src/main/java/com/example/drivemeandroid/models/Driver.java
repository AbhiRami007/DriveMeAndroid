package com.example.drivemeandroid.models;

public class Driver {
    private int id;
    private String name;
    private double ratePerHour;
    private String imageUrl;

    public Driver(int id, String name, double ratePerHour, String imageUrl) {
        this.id = id;
        this.name = name;
        this.ratePerHour = ratePerHour;
        this.imageUrl = imageUrl;
    }
    public int getId() { return id; }
    public void SetId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getRatePerHour() { return (int) ratePerHour; }
    public void setRatePerHour(int ratePerHour) { this.ratePerHour = ratePerHour; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
