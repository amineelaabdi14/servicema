package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Report;
import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.repositories.ReportRepository;
import com.youcode.servicema.services.ReportService;
import com.youcode.servicema.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ServiceService serviceService;
    @Override
    public Report saveReport(Long id , String message) {
        Service service = serviceService.findById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Report report = Report.builder().service(service).user(user).message(message).build();
        reportRepository.save(report);
        return report;
    }

}
