package jdbchandson.jdbchandson.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Users {
    private UUID id;
    private String username;
    private int loggedIn;
    private String timeZone;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private UUID tenantId;
}