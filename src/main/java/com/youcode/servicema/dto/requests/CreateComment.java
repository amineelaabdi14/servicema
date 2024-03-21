package com.youcode.servicema.dto.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateComment {
    private Long serviceId;
    private String comment;
}
