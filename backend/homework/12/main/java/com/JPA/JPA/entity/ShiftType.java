package com.JPA.JPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="shift_type")
public class ShiftType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String uniqueName;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private String timeZone;
    private long tenantId;
}
