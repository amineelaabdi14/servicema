package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Report;
import com.youcode.servicema.domain.entities.Service;

import java.util.List;

public interface ReportService {
    Report saveReport(Long id , String message);
    List<Object[]> getReports();
    List<Report> findAll();
}
