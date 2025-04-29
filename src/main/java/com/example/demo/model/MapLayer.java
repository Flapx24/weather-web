package com.example.demo.model;

public enum MapLayer {
    CLOUDS("clouds_new"),
    PRECIPITATION("precipitation_new"),
    PRESSURE("pressure_new"),
    WIND("wind_new"),
    TEMPERATURE("temp_new");

    private final String value;

    MapLayer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}