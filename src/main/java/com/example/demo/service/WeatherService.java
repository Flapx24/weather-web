package com.example.demo.service;

import com.example.demo.model.Coordinates;
import com.example.demo.model.ForecastData;
import com.example.demo.model.Geo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.WeatherData;
import com.example.demo.model.MapLayer;

@Service("weatherService")
public class WeatherService {

    @Value("openweathermap.api.key")
    private String apiKey;

    @Value("openweathermap.api.url")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // Method weather by city
    public WeatherData getWeatherByCity(String city) {
        String url = String
                .format(baseUrl + "/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric&lang=es");
        return restTemplate.getForObject(url, WeatherData.class);
    }

    // Method weather map
    public String getWeatherMapTileUrl(MapLayer layer, int z, int x, int y) {
        return String.format(baseUrl + "/map/%s/%d/%d/%d.png?appid=%s",
                layer.getValue(), z, x, y, apiKey);
    }

    // Method weather daily
    public ForecastData getWeatherDaily(double lat, double lon) {
        String url = String.format(baseUrl + "/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric&lang=es");
        return restTemplate.getForObject(url, ForecastData.class);
    }

    // Method geolocation
    public WeatherData getWeatherGeo(String city) {
        String url = String
                .format(baseUrl + "/geo/1.0/direct?q=" + city + "&appid=" + apiKey + "&units=metric&lang=es");
        WeatherData[] response = restTemplate.getForObject(url, WeatherData[].class);

        if (response != null && response.length > 0) {
            return response[0];
        } else {
            return null;
        }
    }

    // Method get coordinates
    public Coordinates getCityCoordinates(String city) throws Exception {
        String url = String.format(baseUrl + "/geo/1.0/direct?q=%s&appid=%s", city, apiKey);
        // Get coordinates
        Geo[] cities = restTemplate.getForObject(url, Geo[].class);
        // check if city exists and get coordinates
        if (cities != null && cities.length > 0) {
            Geo cityGeo = cities[0];
            return new Coordinates(cityGeo.getLat(), cityGeo.getLon());
        } else {
            throw new Exception("City not found: " + city);
        }
    }

}
