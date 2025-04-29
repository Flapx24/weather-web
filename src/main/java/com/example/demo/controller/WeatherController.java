package com.example.demo.controller;

import com.example.demo.service.WeatherService;
import com.example.demo.model.WeatherData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    @Qualifier("weatherService")
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city, Model model) {
        WeatherData weatherData = weatherService.getWeatherByCity(city);
        model.addAttribute("weatherData", weatherData);
        
        return "current-weather";
    }
}
