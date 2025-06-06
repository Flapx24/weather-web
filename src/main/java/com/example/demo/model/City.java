package com.example.demo.model;

import java.io.Serializable;

public class City implements Serializable {
    private long id;
    private String name;
    private Coord coord;
    private String country;
    private int timezone;
    private long sunrise;
    private long sunset;

    // Getters y setters

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Coord getCoord() { return coord; }
    public void setCoord(Coord coord) { this.coord = coord; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getTimezone() { return timezone; }
    public void setTimezone(int timezone) { this.timezone = timezone; }

    public long getSunrise() { return sunrise; }
    public void setSunrise(long sunrise) { this.sunrise = sunrise; }

    public long getSunset() { return sunset; }
    public void setSunset(long sunset) { this.sunset = sunset; }
}
