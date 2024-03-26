package com.youcode.servicema.repositories;
import com.youcode.servicema.domain.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT COUNT(r), r.service.id, r.service.title FROM Report r GROUP BY r.service.id, r.service.title")
    List<Object[]> getReportsCount();
}
