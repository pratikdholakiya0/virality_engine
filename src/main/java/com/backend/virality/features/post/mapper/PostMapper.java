package com.backend.virality.features.post.mapper;

import com.backend.virality.features.post.dto.request.PostRequest;
import com.backend.virality.features.post.dto.response.PostResponse;
import com.backend.virality.features.post.model.Post;

public class PostMapper {
    public static Post toEntity(PostRequest request) {
        return Post.builder()
                .authorId(request.getAuthorId())
                .authorType(request.getAuthorType())
                .content(request.getContent())
                .build();
    }

    public static PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .authorId(post.getAuthorId())
                .authorType(post.getAuthorType())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}