package com.caching.controller;
import com.caching.Exception.ApiNullResponse;
import com.caching.dto.GeocodingResponse;
import com.caching.dto.ReverseGeocodingResponse;
import com.caching.service.FetchGeocoding;
import com.caching.service.FetchReverseGeocoding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Controllers {

    private final FetchGeocoding fetchGeocoding;
    private final FetchReverseGeocoding fetchReverseGeocoding;

    @Autowired
    public Controllers(FetchGeocoding fetchGeocoding, FetchReverseGeocoding fetchReverseGeocoding) {
        this.fetchGeocoding = fetchGeocoding;
        this.fetchReverseGeocoding = fetchReverseGeocoding;
    }

    @GetMapping("/geocoding")
    @Cacheable(value = "geocoding", key = "#address", condition = "#address != 'goa'")
    public ResponseEntity<GeocodingResponse> geocoding(@RequestParam("address") String address) {
        log.info("fetching forward api from position stack");

        try {
            if (address == null || address.isBlank()) {
                throw new ApiNullResponse("address is blank");
            }

            GeocodingResponse response = fetchGeocoding.geocoding(address);

            if (response == null) {
                throw new ApiNullResponse("null data fetched");
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred while processing geocoding request: " + e.getMessage());
            throw new ApiNullResponse("error found");
        }
    }



    @GetMapping("/reverse-geocoding")
    @Cacheable(value = "reverse-geocoding", key = "{#latitude,#longitude}")
    public String reverseGeocoding(@RequestParam("latitude") String latitude,
                                              @RequestParam("longitude") String longitude) {
        log.info("fetching reverse api from position stack");

        try {
            Double.parseDouble(latitude);
            Double.parseDouble(longitude);
            ReverseGeocodingResponse response = fetchReverseGeocoding.reverseGeocoding(latitude, longitude);
            return response.getLabel();

        } catch (NumberFormatException e) {
            throw new ApiNullResponse("null response");
        }
    }
}
