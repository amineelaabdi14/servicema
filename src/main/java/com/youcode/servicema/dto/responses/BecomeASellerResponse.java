package com.youcode.servicema.dto.responses;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BecomeASellerResponse {
    String title;
    String description;
    String phone;
}
