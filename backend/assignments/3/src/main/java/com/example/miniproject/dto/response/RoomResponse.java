package com.example.miniproject.dto.response;

import com.example.miniproject.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    String message;
    Room room;
}
