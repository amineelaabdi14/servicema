package com.youcode.servicema.dto.requests;

import com.youcode.servicema.domain.entities.Category;
import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Builder
@Getter
@Setter
public class ServiceDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Long categoryId;
    @NotBlank
    private Long startingPrice;
    @NotBlank
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
