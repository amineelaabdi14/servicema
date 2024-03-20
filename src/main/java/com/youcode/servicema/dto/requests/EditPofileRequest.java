package com.youcode.servicema.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPofileRequest {
    private String name;
    private String phone;
    private String city;
    private String description;
}
