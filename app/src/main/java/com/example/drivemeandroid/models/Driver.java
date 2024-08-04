package com.example.drivemeandroid.models;

public class Driver {
    private String name;
    private String ratePerHour;
    private String imageUrl;

    public Driver(String name, String ratePerHour, String imageUrl) {
        this.name = name;
        this.ratePerHour = ratePerHour;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRatePerHour() { return ratePerHour; }
    public void setRatePerHour(String ratePerHour) { this.ratePerHour = ratePerHour; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
