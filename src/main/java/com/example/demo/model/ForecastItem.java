package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

public class ForecastItem implements Serializable {
    private long dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private double pop; // probabilidad de precipitación
    private Rain rain;
    private Sys sys;
    private String dt_txt;

    // Getters y setters

    public long getDt() { return dt; }
    public void setDt(long dt) { this.dt = dt; }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }

    public Clouds getClouds() { return clouds; }
    public void setClouds(Clouds clouds) { this.clouds = clouds; }

    public Wind getWind() { return wind; }
    public void setWind(Wind wind) { this.wind = wind; }

    public int getVisibility() { return visibility; }
    public void setVisibility(int visibility) { this.visibility = visibility; }

    public double getPop() { return pop; }
    public void setPop(double pop) { this.pop = pop; }

    public Rain getRain() { return rain; }
    public void setRain(Rain rain) { this.rain = rain; }

    public Sys getSys() { return sys; }
    public void setSys(Sys sys) { this.sys = sys; }

    public String getDt_txt() { return dt_txt; }
    public void setDt_txt(String dt_txt) { this.dt_txt = dt_txt; }
}
