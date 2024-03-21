package com.youcode.servicema.dto.responses;

import com.youcode.servicema.domain.entities.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class ReportResponse {
    private String message;
    private String serviceTitle;
    private String userName;

    public static ReportResponse fromReport(Report report) {
        return ReportResponse.builder()
                .message(report.getMessage())
                .serviceTitle(report.getService().getTitle())
                .userName(report.getUser().getUsername())
                .build();
    }
    public static List<ReportResponse> fromReports(List<Report> reports){
        return reports.stream().map(ReportResponse::fromReport).collect(Collectors.toList());
    }
}
