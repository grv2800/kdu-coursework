package com.example.miniproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
        private String kickstonId;
        private String deviceUsername;
        private String devicePassword;
        private String manufactureDateTime;
        private String manufactureFactoryPlace;
}
