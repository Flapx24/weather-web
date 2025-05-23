package com.example.demo.model;

import java.io.Serializable;

public class Wind implements Serializable {
    private double speed;
    private int deg;
    private double gust;
    
    public double getSpeed() {
        return speed;
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public int getDeg() {
        return deg;
    }
    
    public void setDeg(int deg) {
        this.deg = deg;
    }
    
    public double getGust() {
        return gust;
    }
    
    public void setGust(double gust) {
        this.gust = gust;
    }
}