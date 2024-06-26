package com.youcode.servicema.dto.requests;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateComment {
    private Long serviceId;
    private String comment;
}
