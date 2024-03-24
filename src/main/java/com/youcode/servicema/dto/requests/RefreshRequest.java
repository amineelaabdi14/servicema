package com.youcode.servicema.dto.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshRequest {
    private String refreshToken;
}
