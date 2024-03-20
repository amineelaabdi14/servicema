package com.youcode.servicema.dto.responses;

import com.youcode.servicema.domain.entities.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EditPofileResponse {
    private String name;
    private String phone;
    private City city;
    private String description;
}
