package com.educ.journalApp.service;

import com.educ.journalApp.api.response.WeatherResponse;
import com.educ.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
  //  private static final String apiKey = "b09dcf9f8cd04b21b7421a73f7f76702";
   // private String AP I = "http://api.weatherstack.com/current?access_key=YOUR_ACCESS_KEY&query=CITY";

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.APP_CACHE.get("weather_app1");
        finalAPI = finalAPI.replace("<CITY>", city).replace("<YOUR_ACCESS_KEY>", apiKey);
        ResponseEntity<WeatherResponse> response =  restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;

    }


}
