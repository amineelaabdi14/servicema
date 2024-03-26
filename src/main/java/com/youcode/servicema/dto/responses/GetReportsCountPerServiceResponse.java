package com.youcode.servicema.dto.responses;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class GetReportsCountPerServiceResponse {
    Long reportsCount;
    String serviceTitle;
    Long serviceId;


}
