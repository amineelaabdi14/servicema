package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Comment;
import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.CommentRepository;
import com.youcode.servicema.services.CommentService;
import com.youcode.servicema.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final ServiceService serviceService;
    private final CommentRepository commentRepository;
    @Override
    public Comment save(Long serviceId, String comment) {
        Optional<com.youcode.servicema.domain.entities.Service> service = serviceService.getServiceById(serviceId);
        if (!service.isPresent()){
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment savedComment = Comment.builder().comment(comment).service(service.get()).user(currentUser).build();
        Comment savedComment1 = commentRepository.save(savedComment);
        return savedComment1;
    }
}
