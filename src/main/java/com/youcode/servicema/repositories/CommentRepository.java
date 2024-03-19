package com.youcode.servicema.repositories;

import com.youcode.servicema.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
