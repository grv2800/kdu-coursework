package com.example.miniproject.dto.response;

import com.example.miniproject.entity.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponse {
    String message;
    House house;
}
