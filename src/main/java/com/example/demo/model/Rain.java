package com.example.demo.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain implements Serializable {
    @JsonProperty("1h")
    private double oneHour;
    
    public double getOneHour() {
        return oneHour;
    }
    
    public void setOneHour(double oneHour) {
        this.oneHour = oneHour;
    }
}