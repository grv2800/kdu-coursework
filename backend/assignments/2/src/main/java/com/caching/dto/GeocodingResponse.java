package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeocodingResponse {
    double latitude;
    double longitude;
}
