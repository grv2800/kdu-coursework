package com.example.miniproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRegistrationRequest {
    private String kickstoneId;
    private String deviceUsername;
    private String devicePassword;

}
