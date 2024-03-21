package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Comment;

public interface CommentService {
    public Comment save(Long serviceId, String comment);
}
