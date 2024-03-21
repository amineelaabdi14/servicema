package com.youcode.servicema.dto.requests;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReport {
    private Long serviceId;
    private String message;
}
