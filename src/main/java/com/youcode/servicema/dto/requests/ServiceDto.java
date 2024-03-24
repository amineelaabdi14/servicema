package com.youcode.servicema.dto.requests;

import com.youcode.servicema.domain.entities.Category;
import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Builder
@Getter
@Setter
public class ServiceDto {
    private String title;
    private String description;
    private Long categoryId;
    private Long startingPrice;
    private String image;
    @Nullable
    private Long userId;

    public static Service toService(ServiceDto serviceDto , User user, Category category) {
        return Service.builder()
                .title(serviceDto.getTitle())
                .category(category)
                .description(serviceDto.getDescription())
                .startingPrice(serviceDto.getStartingPrice())
                .image(serviceDto.getImage())
                .user(user)
                .build();
    }
}
