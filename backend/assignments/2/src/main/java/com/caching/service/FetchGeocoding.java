package com.caching.service;

import com.caching.dto.GeocodingResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
public class FetchGeocoding {
    @Value("${api-key}")
    String apiKey;
    public GeocodingResponse geocoding(String address){
        String url= "http://api.positionstack.com/v1/forward?access_key=" + apiKey + "&query=" + address;
        RestTemplate restTemplate=new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);

        assert jsonNode != null;
        double lat = jsonNode.get("data").get(0).get("latitude").asDouble();
        double lon = jsonNode.get("data").get(0).get("longitude").asDouble();

        GeocodingResponse geocodingResponse= new GeocodingResponse(lat,lon);

        log.info("latitude: "+lat+" longitude: "+lon);

        return geocodingResponse;
    }
}
