package com.example.demo.model;

public class Coordinates {
    private double lat;
    private double lon;

    // Constructor
    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    // Getters y setters
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

