package com.JPA.JPA.repository;

import com.JPA.JPA.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.util.UUID;
@Repository
public interface ShiftRepo extends JpaRepository<Shift, UUID> {
    @Query("SELECT s FROM Shift s WHERE s.startDate = :startDate AND s.endDate = :endDate ORDER BY s.name")
    List<Shift> findTop3ByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
}
