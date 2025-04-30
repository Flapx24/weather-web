package com.example.demo.controller;

import com.example.demo.model.Coordinates;
import com.example.demo.model.ForecastData;
import com.example.demo.service.WeatherService;
import com.example.demo.model.WeatherData;
import com.example.demo.model.MapLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // Endpoint find city
    @GetMapping("/buscar")
    public String searchWeather(@RequestParam(required = false) String city, Model model) {
        if (city != null && !city.isEmpty()) {
            try {
                WeatherData weatherData = weatherService.getWeatherByCity(city);
                model.addAttribute("weatherData", weatherData);
    
                // weather 5 days
                Coordinates coordinates = weatherService.getCityCoordinates(city);
                ForecastData forecastData = weatherService.getWeatherDaily(coordinates.getLat(), coordinates.getLon());
                model.addAttribute("forecastData", forecastData);
    
            } catch (Exception e) {
                model.addAttribute("error", "City not found: " + city);
            }
        }
        return "current-weather";
    }
    

    @GetMapping("/map")
    public String showWeatherMap(
            @RequestParam(required = false, defaultValue = "CLOUDS") String layer,
            Model model) {

        MapLayer mapLayer;
        try {
            mapLayer = MapLayer.valueOf(layer.toUpperCase());
        } catch (IllegalArgumentException e) {
            mapLayer = MapLayer.CLOUDS;
        }

        model.addAttribute("selectedLayer", mapLayer);

        List<Map<String, String>> availableLayers = Arrays.stream(MapLayer.values())
                .map(l -> {
                    Map<String, String> layerMap = new HashMap<>();
                    layerMap.put("name", l.name());
                    layerMap.put("value", l.getValue());
                    layerMap.put("displayName", formatLayerName(l.name()));
                    return layerMap;
                })
                .collect(Collectors.toList());

        model.addAttribute("availableLayers", availableLayers);

        model.addAttribute("tileUrlTemplate", "/weather/map/{layer}/{z}/{x}/{y}");

        model.addAttribute("defaultLat", 40.416775); // Madrid coordinates
        model.addAttribute("defaultLon", -3.703790);
        model.addAttribute("defaultZoom", 5);

        return "weather-map";
    }

    @GetMapping("/map/{layer}/{z}/{x}/{y}")
    public String getMapTile(
            @PathVariable String layer,
            @PathVariable int z,
            @PathVariable int x,
            @PathVariable int y) {

        MapLayer mapLayer;
        try {
            mapLayer = MapLayer.valueOf(layer.toUpperCase());
        } catch (IllegalArgumentException e) {
            mapLayer = MapLayer.CLOUDS;
        }

        String tileUrl = weatherService.getWeatherMapTileUrl(mapLayer, z, x, y);
        return "redirect:" + tileUrl;
    }

    private String formatLayerName(String layerName) {
        String[] words = layerName.toLowerCase().split("_");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                formattedName.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return formattedName.toString().trim();
    }
    @GetMapping("/weatherDaily")
    public String getWeatherDaily(@RequestParam("city") String city, Model model) {
        try {
            // get coordinates by city name
            Coordinates coordinates = weatherService.getCityCoordinates(city);
            ForecastData forecastData = weatherService.getWeatherDaily(coordinates.getLat(), coordinates.getLon());
    
            model.addAttribute("forecastData", forecastData);
            model.addAttribute("city", city);
            return "current-weather";
    
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo obtener el pronóstico para la ciudad: " + city);
            return "current-weather";
        }
    }
    
    
}
