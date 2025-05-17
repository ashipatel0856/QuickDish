package com.ashish.QuickDish.service;

import com.ashish.QuickDish.dto.DistanceRequestDto;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {

    @Value("${google.api.key}")
    private String apiKey;


    public DistanceRequestDto findDistance(DistanceRequestDto distanceRequestDto){
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +distanceRequestDto.getOrigin()+"&destinations=" +distanceRequestDto.getDestination()+"&key="+apiKey;

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(url, String.class);
        JsonObject jsonObject = new JsonObject();

        JSONArray rows = jsonObject.getJSONArray("rows");
        JSONObject elements = rows.getJSONObject(0).getJSONArray("elements").getJSONObject(0);
        String distance = elements.getJSONObject("distance").getString("text");
        String time = elements.getJSONObject("duration").getString("text");

        return new DistanceRequestDto(distance, time);



    }
}
