package com.example.demo.model;

import java.util.List;

public class ForecastData {
    private List<ForecastItem> list;
    private City city;

    public List<ForecastItem> getList() {
        return list;
    }

    public void setList(List<ForecastItem> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
