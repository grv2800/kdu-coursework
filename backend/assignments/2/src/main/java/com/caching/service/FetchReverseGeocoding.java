package com.caching.service;

import com.caching.dto.ReverseGeocodingResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
public class FetchReverseGeocoding {
    @Value("${api-key}")
    String apiKey;
    public ReverseGeocodingResponse reverseGeocoding(String latitude,String longitude){
        String url= "http://api.positionstack.com/v1/reverse?access_key=" + apiKey + "&query=" + latitude +","+longitude;
        RestTemplate restTemplate=new RestTemplate();

        JsonNode jsonNode=restTemplate.getForObject(url,JsonNode.class);
        String label=jsonNode.get("data").get(0).get("label").asText();

        ReverseGeocodingResponse reverseGeocodingResponse=new ReverseGeocodingResponse(label);

        log.info("Address: "+label);

        return reverseGeocodingResponse;
    }
}
