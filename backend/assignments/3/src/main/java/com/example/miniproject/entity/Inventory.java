package com.example.miniproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
        @Id
        private String kickstonId;
        private String deviceUsername;
        private String devicePassword;
        private LocalDateTime manufactureDateTime;
        private String manufactureFactoryPlace;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
        private LocalDateTime deletedDate;

}

