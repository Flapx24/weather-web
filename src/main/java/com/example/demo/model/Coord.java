package com.example.demo.model;

import java.io.Serializable;

public class Coord implements Serializable {
    private double lon;
    private double lat;
    
    public double getLon() {
        return lon;
    }
    
    public void setLon(double lon) {
        this.lon = lon;
    }
    
    public double getLat() {
        return lat;
    }
    
    public void setLat(double lat) {
        this.lat = lat;
    }
}