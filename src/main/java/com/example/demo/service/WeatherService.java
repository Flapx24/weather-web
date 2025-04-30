package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.WeatherData;
import com.example.demo.model.MapLayer;

import java.util.List;

@Service("weatherService")
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // Method weather by city
    public WeatherData getWeatherByCity(String city) {
        String url = String.format(baseUrl + "/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric&lang=es");
        return restTemplate.getForObject(url, WeatherData.class);
    }

    // Method weather map
    public String getWeatherMapTileUrl(MapLayer layer, int z, int x, int y) {
        return String.format(baseUrl + "/map/%s/%d/%d/%d.png?appid=%s",
            layer.getValue(), z, x, y, apiKey
        );
    }

    // Method weather diary, 5 days
    public WeatherData getWeatherDaily(double lat, double lon, String date) {
        String url = String.format(baseUrl + "/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric&lang=es");
        WeatherData data = restTemplate.getForObject(url, WeatherData.class);
        return data;
    }

    public WeatherData getWeatherGeo(String city) {
        String url = String.format(baseUrl + "/geo/1.0/direct?q=" + city + "&appid=" + apiKey + "&units=metric&lang=es");
        WeatherData[] response = restTemplate.getForObject(url, WeatherData[].class);

        if (response != null && response.length > 0) {
            return response[0];
        } else {
            return null;
        }
    }
}
