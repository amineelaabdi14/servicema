package com.youcode.servicema.controllers;

import com.youcode.servicema.domain.entities.Report;
import com.youcode.servicema.dto.requests.CreateReport;
import com.youcode.servicema.dto.responses.GetReportsCountPerServiceResponse;
import com.youcode.servicema.dto.responses.ReportResponse;
import com.youcode.servicema.services.ReportService;
import com.youcode.servicema.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        return ResponseEntity.ok(new HashMap<>(){
            {
                put("message", "Report added successfully");
            }
        });
    }
    @GetMapping("")
    public ResponseEntity getReports(){
        return ResponseEntity.ok(reportService.findAll());
    }

    @PreAuthorize("hasAuthority('GET_REPORTS')")
    @GetMapping("/grouped")
    public ResponseEntity getGroupedReports(){
        List<Object[]> hamid =reportService.getReports();
        List reports = new ArrayList();
        hamid.forEach((o) -> {
            Object[] obj = (Object[]) o;
            HashMap report = new HashMap();
            report.put("serviceTitle", obj[2]);
            report.put("count", obj[0]);
            report.put("serviceId", obj[1]);
            reports.add(report);
        });
       return ResponseEntity.ok(reports);
    }
}
