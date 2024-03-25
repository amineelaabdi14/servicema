package com.youcode.servicema.dto.responses;

import com.youcode.servicema.domain.entities.Category;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    Long id;
    String category;

    public static List<CategoryDto> fromCategoryList(List<Category> categories) {
        return categories.stream()
                .map(category -> CategoryDto.builder()
                        .category(category.getName())
                        .id(category.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
