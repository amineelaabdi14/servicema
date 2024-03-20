package com.youcode.servicema.dto.requests;

import com.youcode.servicema.domain.entities.Service;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ServiceDto {
    private String title;
    private String description;
    private Long categoryId;
    private Long startingPrice;
    private String image;
    private Long userId;

    public static Service toService(ServiceDto serviceDto) {
        return Service.builder()
                .title(serviceDto.getTitle())
                .description(serviceDto.getDescription())
                .startingPrice(serviceDto.getStartingPrice())
                .image(serviceDto.getImage())
                .build();
    }
}
