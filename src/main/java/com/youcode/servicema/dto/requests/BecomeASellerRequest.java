package com.youcode.servicema.dto.requests;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BecomeASellerRequest {
    String title;
    String description;
    String phone;
}
