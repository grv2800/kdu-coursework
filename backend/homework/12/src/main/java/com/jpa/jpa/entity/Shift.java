package com.JPA.JPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shift_type_id")
    private ShiftType shiftType;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String timeZone;
    private UUID tenantId;
}
