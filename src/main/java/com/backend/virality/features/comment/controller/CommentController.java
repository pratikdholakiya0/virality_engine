package com.backend.virality.features.comment.controller;

import com.backend.virality.features.comment.dto.request.CommentRequest;
import com.backend.virality.features.comment.dto.response.CommentResponse;
import com.backend.virality.features.comment.service.inter.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest request
    ) {
        CommentResponse response = commentService.addComment(postId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}