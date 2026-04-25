package com.backend.virality.features.comment.mapper;

import com.backend.virality.features.comment.dto.request.CommentRequest;
import com.backend.virality.features.comment.dto.response.CommentResponse;
import com.backend.virality.features.comment.model.Comment;
import com.backend.virality.features.post.model.Post;

public class CommentMapper {

    public static Comment toEntity(CommentRequest request, Post post, Comment parent, int depth) {
        if (request == null) return null;

        return Comment.builder()
                .post(post)
                .authorId(request.getAuthorId())
                .authorType(request.getAuthorType())
                .content(request.getContent())
                .depthLevel(depth)
                .parent(parent)
                .build();
    }

    public static CommentResponse toResponse(Comment comment) {
        if (comment == null) return null;

        return CommentResponse.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .authorId(comment.getAuthorId())
                .authorType(comment.getAuthorType())
                .content(comment.getContent())
                .depthLevel(comment.getDepthLevel())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .createdAt(comment.getCreatedAt())
                .build();
    }
}