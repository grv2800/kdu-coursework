package jdbchandson.jdbchandson.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;
@Data
@RequiredArgsConstructor
public class ShiftTypeDto {
    private UUID id;
    private String uqName;
    private String description;
    private boolean active;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String timeZone;
    private UUID tenantId;

}