package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Report;

import java.util.List;

public interface ReportService {
    Report saveReport(Long id , String message);
}
