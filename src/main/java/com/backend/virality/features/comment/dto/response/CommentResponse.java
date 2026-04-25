package com.backend.virality.features.comment.dto.response;

import com.backend.virality.features.post.enums.AuthorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    private Long id;
    private Long postId;
    private Long authorId;
    private AuthorType authorType;
    private String content;
    private int depthLevel;
    private Long parentId;
    private LocalDateTime createdAt;
}