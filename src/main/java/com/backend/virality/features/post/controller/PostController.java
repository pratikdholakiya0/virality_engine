package com.backend.virality.features.post.controller;

import com.backend.virality.features.post.dto.request.PostRequest;
import com.backend.virality.features.post.dto.response.PostResponse;
import com.backend.virality.features.post.service.inter.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @Valid @RequestBody PostRequest request
    ) {
        PostResponse response = postService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(
            @PathVariable Long postId
    ) {
        postService.like(postId);
        return ResponseEntity.ok("Post liked successfully");
    }
}