package com.youcode.servicema.dto.responses;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    String user;
    String comment;
}
