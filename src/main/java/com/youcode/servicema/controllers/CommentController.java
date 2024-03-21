package com.youcode.servicema.controllers;

import com.youcode.servicema.dto.requests.CreateComment;
import com.youcode.servicema.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public ResponseEntity addComment(@RequestBody CreateComment comment) {
        return ResponseEntity.ok(commentService.save(comment.getServiceId(), comment.getComment()));
    }
}
