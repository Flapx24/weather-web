package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.WeatherData;

@Service("weatherService")
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherData getWeatherByCity(String city) {
        String url = String.format(baseUrl + "/weather?q=" + city + "&appId=" + apiKey + "&units=metric&lang=es");
        return restTemplate.getForObject(url, WeatherData.class);
    }
}
