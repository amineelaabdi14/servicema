package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Report;
import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.ReportRepository;
import com.youcode.servicema.services.ReportService;
import com.youcode.servicema.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ServiceService serviceService;

    @Override
    public Report saveReport(Long id, String message) {
        Service service = serviceService.findById(id);
        if (service == null) {
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Report report = Report.builder().service(service).user(user).message(message).build();
        reportRepository.save(report);
        return report;
    }

    @Override
    public List<Object[]> getReports() {
        return reportRepository.getReportsCount();
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}