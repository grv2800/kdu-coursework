package com.example.miniproject.dto.response;

import com.example.miniproject.entity.House;
import lombok.Data;

import java.util.List;

@Data
public class HouseListResponse {
    String message;
    List<House> houses;
}
