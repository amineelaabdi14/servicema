package com.youcode.servicema.dto.responses;

import com.youcode.servicema.domain.entities.Comment;
import com.youcode.servicema.domain.entities.Service;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    Long id ;
    String title;
    String description;
    String category;
    String user;
    Long startingPrice;;
    String image;
    List<CommentDto> comments;

    public static List<ServiceResponse> fromServices(List<Service> services) {
        return services.stream().map(service -> {
            List<CommentDto> comments = service.getComments().stream().map(comment -> {
                return CommentDto.builder()
                        .user(comment.getUser().getUsername())
                        .comment(comment.getComment())
                        .build();
            }).collect(Collectors.toList());
            return ServiceResponse.builder()
                    .id(service.getId())
                    .title(service.getTitle())
                    .description(service.getDescription())
                    .category(service.getCategory().getName())
                    .startingPrice(service.getStartingPrice())
                    .image(service.getImage())
                    .comments(comments)
                    .build();
        }).collect(Collectors.toList());
    }

    public static ServiceResponse fromService(Service service) {
        List<CommentDto> comments = service.getComments().stream().map(comment -> {
            return CommentDto.builder()
                    .user(comment.getUser().getName())
                    .comment(comment.getComment())
                    .build();
        }).collect(Collectors.toList());
        return ServiceResponse.builder()
                .id(service.getId())
                .user(service.getUser().getName())
                .title(service.getTitle())
                .description(service.getDescription())
                .category(service.getCategory().getName())
                .startingPrice(service.getStartingPrice())
                .image(service.getImage())
                .comments(comments)
                .build();
    }
}
