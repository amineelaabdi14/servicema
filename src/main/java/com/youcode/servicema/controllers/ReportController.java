package com.youcode.servicema.controllers;

import com.youcode.servicema.domain.entities.Report;
import com.youcode.servicema.dto.requests.CreateReport;
import com.youcode.servicema.dto.responses.ReportResponse;
import com.youcode.servicema.services.ReportService;
import com.youcode.servicema.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final ServiceService serviceService;

    @GetMapping("/{id}")
    public ResponseEntity getReportsByService(@PathVariable Long id){
        return ResponseEntity.ok(ReportResponse.fromReports( serviceService.findById(id).getReports()));
    }

    @PostMapping
    public ResponseEntity saveReport(@RequestBody CreateReport report){
        Report report1 = reportService.saveReport(report.getServiceId(), report.getMessage());
        return ResponseEntity.ok(report1);
    }
}
