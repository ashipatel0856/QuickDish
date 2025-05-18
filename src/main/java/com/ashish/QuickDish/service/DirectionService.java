package com.ashish.QuickDish.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class DirectionService {

    @Value("${GOOGLE_MAPS_API_KEY}")
    private String apiKey;

    public String getDirection(String origin, String destination) {

        try {
            String url = String.format(
                    "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=%s",
                    URLEncoder.encode(origin, StandardCharsets.UTF_8),
                    URLEncoder.encode(destination, StandardCharsets.UTF_8),
                    apiKey
            );

            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.getForObject(url, String.class);
            JSONObject object = new JSONObject(json);
            JSONArray routes = object.getJSONArray("routes");

            if (routes.length() > 0) {
                JSONObject route = routes.getJSONObject(0);
                JSONObject overviewPolyline = route.getJSONObject("overview_polyline");
                return overviewPolyline.getString("points");
            } else {
                throw new RuntimeException("No routes found");
            }

        } catch (Exception e) {
            throw new RuntimeException("Directions are not available", e);
        }
    }
}
